# Week 7 Self-Check and Test Notes

## How this was tested
I ran each application on a virtual display and used **real mouse input** (clicks, moves and a press-drag-release) for Activities 1 to 3. The arrow-key presses in Activity 2 were sent straight to the window's root node as key events (the effect is the same as typing, but they did not go through a physical keyboard), and the Login button in Activity 4 was fired programmatically. Console output and snapshots are in `screenshots/`.

**Not tested here:** `mvn` itself (Maven's servers are not reachable from my test machine) and JavaFX 21. I compiled and ran against JavaFX 11. Please run `mvn clean javafx:run` once on your PC to confirm the pom works.

## Three differences from the guide (and why)
1. **`root.setFocusable(true)` does not exist in JavaFX.** (`setFocusable` is a Swing/AWT method.) The code would not compile. The JavaFX equivalent is `root.setFocusTraversable(true)`, which is what `MouseKeyDemo` uses.
2. **The Button swallows the click in Activity 1.** With a real click on the `Button`, I got only lines 1 and 2; line 3 ("Root saw the click bubbling up") did not print, because the Button's built-in behaviour consumes the mouse event after our handler runs (a probe of PRESSED, RELEASED and CLICKED confirmed none of them reached the root handler). Clicking plain space does reach the root. I kept the guide's `EventPropagationDemo` exactly as written, and added `EventPropagationLabelDemo`, which uses a styled Label so the full order prints. On JavaFX 21 your Button version may behave differently; if you see only two lines, this is why.
3. **Run commands.** I added named executions to the pom (`@label`, `@mousekey`, `@dragdrop`, `@customevent`) so each activity runs without editing the main class.

## Activity 1: Event Filters vs Handlers
`EventPropagationLabelDemo`, real click (`screenshots/activity1_label_console_output.txt`):
```
1. [FILTER] Root intercepted the click!
2. [HANDLER] Target was clicked!
3. [HANDLER] Root saw the click bubbling up!
```
With `e.consume()` uncommented in the filter, only line 1 printed: the event never reached the target (`screenshots/activity1_consume_console_output.txt`).

**Q1. Which phase does an Event Filter belong to?** The capturing phase: the event travels from the root down to the target.
**Q2. What does `e.consume()` do?** Stops the event propagating further; in a filter, the target and all handlers never see it (confirmed above).
**Q3. How do you register a handler?** `addEventHandler(...)` or convenience methods such as `setOnMouseClicked()` and `setOnAction()`.

## Activity 2: Mouse and Key Events
Moving the mouse onto the circle updated the text to `Mouse Coordinates: X=250.0, Y=200.0`. Pressing Right twice and Down three times moved the circle from (250, 200) to (280, 245) (`screenshots/activity2_console_output.txt`). The circle is kept inside the window by `Math.max`/`Math.min`.

**Q1. Which method tracks mouse position without clicking?** `setOnMouseMoved()`.
**Q2. Why might a KeyEvent not fire?** The node does not have keyboard focus; it must be focus-traversable and have requested focus (`root.requestFocus()`).
**Q3. How do you tell which key was pressed?** `e.getCode()`, compared with `KeyCode.UP` and so on.

## Activity 3: Drag and Drop
A real press-drag-release from "Drag Me" to the grey rectangle printed `Drag detected`, `Drag entered the drop zone (zone turns green)` and `Dropped: Drag Me` (`screenshots/activity3_console_output.txt`). The zone turned green while hovering, the label under it changed to "Dropped: Drag Me", and the item became disabled (faded).

**Q1. Which handler starts a drag?** `setOnDragDetected()`.
**Q2. Why `acceptTransferModes(TransferMode.MOVE)` in `setOnDragOver`?** It tells JavaFX the target accepts the data; without it the drop is rejected.
**Q3. How do you read the dropped data?** `event.getDragboard()` and then `db.getString()` (after checking `db.hasString()`).

## Activity 4: Custom Events
With an empty username the status said "Username cannot be empty."; with "Alice" the console printed `Custom Event Received! User logged in: Alice`, the status became "Welcome back, Alice!" and the field cleared. The button only fires the event; the label update lives in a separate handler.

**Q1. Why a custom event instead of calling a method?** Loose coupling: the code that fires the event does not know who listens or how many listeners there are.
**Q2. Which class must a custom event extend?** `javafx.event.Event`.
**Q3. How do you define its type?** A static `EventType` field, e.g. `new EventType<>(Event.ANY, "LOGIN_SUCCESS")`.
