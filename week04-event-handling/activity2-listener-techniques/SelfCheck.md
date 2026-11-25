# Activity 2: Three Ways to Implement a Listener

Console output (real run, one click per button) is in `ListenerDemo_output.txt`; `EventSourceDemo_output.txt` shows `getActionCommand()`; screenshots are in `../screenshots/act2_*.png`.

| Style | When to use | Verbosity |
|---|---|---|
| Named class | Same listener reused across components, or the listener needs its own state | Medium |
| Anonymous inner class | Legacy (pre-Java 8) code; when you need an anonymous subclass | Most verbose |
| Lambda | Modern code, short handlers (default choice) | Concise |
| Method reference | The handler is already a method | Concise |

**Q1. Best style for a simple button action?** A lambda expression: concise, modern and readable.
**Q2. When a named class instead of a lambda?** When the same logic is used by several components, or the listener must keep its own state.
**Q3. What does `e.getSource()` return?** A reference to the component that fired the event (the `JButton` that was clicked).
**Q4. What does `e.getActionCommand()` return?** A String for the command; for buttons it defaults to the button's text (`Add`, `Subtract`, `Reset` in the output).

`EventSourceDemo` uses one shared listener on three buttons and `getSource()` to tell them apart.
