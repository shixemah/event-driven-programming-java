# Activity 1: Self-Check

**Q1. Difference between JFrame and the content pane?**
`JFrame` is the top-level window (title bar, minimize/maximize/close buttons). The content pane is the invisible container inside it where components are added. `frame.add(component)` adds to the content pane automatically.

**Q2. What does EXIT_ON_CLOSE do?**
It makes the JVM terminate completely when the user clicks the X button. Other options: `DO_NOTHING_ON_CLOSE`, `HIDE_ON_CLOSE`, `DISPOSE_ON_CLOSE`.

**Q3. Why `SwingUtilities.invokeLater()`?**
Swing is not thread-safe, so GUI creation and updates must happen on the Event Dispatch Thread. `invokeLater()` schedules the code on the EDT.

**Proof of EDT:** the terminal prints `Window created on thread: AWT-EventQueue-0` (see `terminal_output.txt` and `../screenshots/act1_first_window.png`).
