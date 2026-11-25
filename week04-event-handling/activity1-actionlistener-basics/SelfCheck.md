# Activity 1: ActionListener Basics - Self-Check

Screenshots: `../screenshots/act1_*.png` (window, popup after the click, and after pressing OK).

**Q1. Event source?** The `JButton` named `clickButton`. It generates the `ActionEvent` when clicked.
**Q2. Event listener?** The lambda `e -> {...}`, which implements the `ActionListener` interface.
**Q3. Event handler?** The body of the lambda (the `JOptionPane.showMessageDialog(...)` call).
**Q4. Where does `e` come from?** Swing creates the `ActionEvent` and passes it to `actionPerformed`; it carries information about the click (source, command, time).

Test result: clicking "Click Me!" opens the "You clicked the button!" popup; OK closes it and the window stays.
