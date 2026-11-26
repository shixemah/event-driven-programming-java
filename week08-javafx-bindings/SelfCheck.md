# Week 8 Self-Check and Test Notes

## How this was tested
Each application was run on a virtual display with scripted input and state dumps (`screenshots/*_console_output.txt`) and snapshots (`screenshots/*.png`). Text was entered by setting the text fields' values from code (and the slider value likewise), which triggers exactly the same bindings as typing, but it was not typed on a physical keyboard.

**Not tested here:** `mvn` itself (Maven's servers are not reachable from my test machine) and JavaFX 21; I compiled and ran against JavaFX 11. Please run `mvn clean javafx:run` once on your PC to confirm the pom works.

## Fixes to the guide (three real problems)
1. **The Activity 5 validation line does not compile.** `emailField.textProperty().contains("@")` - a `StringProperty` has no `contains()` method (I compiled the guide's line on its own: "cannot find symbol: method contains(String)"). I replaced it with the standard `Bindings.createBooleanBinding(() -> !emailField.getText().contains("@"), emailField.textProperty())`, which does the same job and is still a live binding.
2. **`ListenerDemo` never stops running.** It extends `Application` but never shows a window, so JavaFX keeps waiting forever (I ran the guide's version: still running after 10 seconds, so `mvn javafx:run` would hang until you press Ctrl+C). I added `Platform.exit();` at the end of `start()`, and now it prints the output and ends by itself.
3. **The form's labels are squeezed to "..." (cosmetic).** With the guide's code the form is wider than its 500 px window, so JavaFX shrinks the Name/Email/Bio labels to "...". I narrowed the bio box, made the preview line wrap, and told the label column never to shrink (all commented in the code).

## Activity 1: Observable Properties (`Person.java`)
A model class using `StringProperty` and `IntegerProperty`, with normal getters/setters and `nameProperty()` / `ageProperty()` for bindings.

**Q1. Why StringProperty instead of String?** A plain `String` cannot notify anyone when it changes; a `StringProperty` wraps the value so the UI can listen to it or bind to it for automatic updates.
**Q2. Purpose of `nameProperty()`?** It returns the property object itself, not its value, so other components can bind to it or attach listeners (e.g. `label.textProperty().bind(person.nameProperty())`).

## Activity 2: ChangeListener vs InvalidationListener (`activity2_console_output.txt`)
The output matches the guide exactly:
```
--- Setting name to Alice ---
1. [InvalidationListener] Name was changed!
2. [ChangeListener] Name changed from '' to 'Alice'

--- Setting name to Bob ---
1. [InvalidationListener] Name was changed!
2. [ChangeListener] Name changed from 'Alice' to 'Bob'

--- Setting name to Bob (same value) ---
```
(nothing printed the third time).

**Q1. Main difference?** An `InvalidationListener` only says the property is no longer valid; a `ChangeListener` also gives the old and new values.
**Q2. Same value set again - do listeners fire?** No: the new value equals the old one, so JavaFX does not trigger them (confirmed above).

## Activity 3: Unidirectional binding (`activity3_console_output.txt`, `act3_*.png`)
The label shows `, welcome!` as soon as the binding is created (so "Hello, Guest" disappears immediately, because the empty text field is the source). Setting the field to "Emmanuel" made the label read `Emmanuel, welcome!`. Calling `setText("New Text")` on the bound label threw: `java.lang.RuntimeException: Label.text : A bound value cannot be set.`

**Q1. What does "unidirectional" mean?** Data flows one way: changes to the source (TextField) update the target (Label), but not the other way round.
**Q2. What if you call `setText()` on a bound label?** A `RuntimeException` ("A bound value cannot be set"); you must call `unbind()` first.

## Activity 4: Bidirectional binding (`activity4_console_output.txt`, `act4_*.png`)
Slider set to 80 -> the text field showed `80`. Text field set to `20` -> the slider moved to 20.0. (It updates as you type; you do not have to press Enter.)

**Q1. `bind()` vs `bindBidirectional()`?** `bind()` updates the target only when the source changes; `bindBidirectional()` keeps both properties in step whichever one changes.
**Q2. Why `NumberStringConverter`?** The slider's value is a double and the text field's text is a String; the converter turns numbers into text and back.

## Activity 5: Reactive registration form (`activity5_console_output.txt`, `act5_*.png`)
| Step | Submit button |
|---|---|
| Start (empty form) | disabled |
| Name only | disabled |
| Email without "@" | disabled |
| Name + valid email + bio (25 characters) | **enabled**; counter "25 / 150 characters"; preview "Hi, I am Emmanuel. I study computer science." |
| Bio of 151 characters | disabled; counter "151 / 150 characters" |
| Bio shortened again | enabled |
Pressing Submit opens the "Registration Successful / Welcome, Emmanuel!" dialog (`act5_success_alert.png`).

**Q1. Why bind `disableProperty()` instead of an if inside a key listener?** Bindings are declarative and automatic: no code checks every keystroke, the result is cleaner, and the state can never get out of sync.
**Q2. What does `.not()` do?** It inverts a boolean: `contains("@")` is true when the email has an @, and `.not()` makes the button disabled when it does not.
