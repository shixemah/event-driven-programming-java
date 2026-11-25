# Week 4 Final Self-Assessment

(Ratings 1-5 are yours to give.)

## Outcome 1: Implement ActionListener using multiple techniques
Rating: ___
Three ways: 1. a named class implementing `ActionListener`; 2. an anonymous inner class; 3. a lambda expression (also a method reference).

## Outcome 2: Handle state changes in checkboxes, radio buttons and text fields
Rating: ___
- Listener for checkbox state changes: **ItemListener** (`itemStateChanged`)
- Listener for text field changes: **DocumentListener** (on `textField.getDocument()`)

## Outcome 3: Differentiate between listener interfaces
Rating: ___

| Component / event | Listener |
|---|---|
| JButton | ActionListener |
| JCheckBox | ItemListener (also ActionListener) |
| JTextField | DocumentListener (ActionListener for Enter) |
| Mouse hover | MouseListener (`mouseEntered` / `mouseExited`) |
| Key press | KeyListener (`keyPressed`) |

## Outcome 4: Build an interactive Swing application
Rating: ___
Did the Interactive Registration Form work? **Yes** - validation errors, success dialog, Clear and Exit all work (see `activity5-interactive-form/SelfCheck.md`).
