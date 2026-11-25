# Activity 3: ItemListener and DocumentListener

Screenshots: `../screenshots/act3_*.png`. Console output: `ItemListenerDemo_output.txt`, `LiveSearchDemo_output.txt`.

## What I observed
- **Pizza (ItemListener):** choosing Large, then Pepperoni, then Extra Cheese printed `$11.00`, `$13.00`, `$14.50`; unchecking Pepperoni gave `$12.50`. The first line printed `Total: $0.00`: when you switch a radio button, the old one is deselected first (one event, nothing selected) and the new one selected second (another event), so the listener fires twice. This is the "fires twice" note in the guide's troubleshooting table - normal behaviour.
- **Character counter (DocumentListener):** the label updates as characters are inserted; at 215 characters the counter showed `Characters: 215 / 200` in red.
- **Live search:** typing `gra` filtered to Grape and Grapefruit (2 matches); `pea` gave Peach and Pear (2 matches). The log shows one line per keystroke.

## Self-Check
**Q1. What event does a JCheckBox fire when toggled?** An `ItemEvent`, handled by an `ItemListener` (`itemStateChanged()`).
**Q2. ItemListener vs ActionListener?** `ItemListener` fires when a selection state changes (checkbox, radio, combo); `ActionListener` fires on an action (click, Enter). Checkboxes and radios fire both.
**Q3. Why must all three DocumentListener methods be implemented?** It is not a functional interface: it has three abstract methods, and lambdas only work with exactly one.
**Q4. When does `insertUpdate` fire?** Whenever text is inserted into the document, e.g. typing or pasting.
