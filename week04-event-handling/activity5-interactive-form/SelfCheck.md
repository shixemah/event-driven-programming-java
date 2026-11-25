# Activity 5: Interactive Registration Form

**Important:** the guide builds this form by dragging components in NetBeans. I wrote the same form by hand in `RegistrationForm.java` using `GridBagLayout`, with the same variable names (`nameField`, `emailField`, `passwordField`, `maleRadio`, `genderGroup`, `sportsCheck`, `countryBox`, `commentsArea`, `submitButton`, `clearButton`, `exitButton`, `statusLabel`) and the same three handlers. It runs with plain `javac` and `java`, so NetBeans is not required. If your lecturer specifically requires the NetBeans-generated version, recreate the layout visually and paste the three handler methods into it.

## What I tested (screenshots in `../screenshots/act5_form_*.png`)
| Step | Result |
|---|---|
| Submit with empty name | Red "Error: Please enter your name" |
| Invalid email (no @) | Red "Error: Please enter a valid email" |
| Password shorter than 6 characters | Red "Error: Password must be at least 6 characters" |
| Everything valid | Dialog listing name, email, gender, interests, country and comments; status goes green |
| Clear | All fields empty, status "Status: Form cleared" |

(The Exit button shows a Yes/No confirmation before `System.exit(0)`.)

## Self-Check
**Q1. Why `.trim()`?** It removes leading and trailing spaces, so a field holding only spaces fails the empty check.
**Q2. What does `passwordField.getPassword()` return?** A `char[]`, not a `String`. It is safer because a `String` is immutable and can linger in memory, while a char array can be overwritten after use.
**Q3. Why `else if` for the gender buttons?** The `ButtonGroup` allows only one selected radio button, so once the selected one is found the others need not be checked.
