# Activity 4: Counter Application

Screenshots: `../screenshots/act4_counter_*.png`.

## What I tested
- `+` three times -> counter shows 3 and the status says "Incremented to 3".
- `-` four times from 3 -> it stops at 0 and the status shows "Minimum reached!" with the number in orange.
- Keyboard shortcuts (Up = increment, Down = decrement, R = reset) are bound through the Input Map / Action Map. I tested them by firing each bound action: the counter went to 2 after two "increment" actions and back to 0 on "reset".

## Note on a change to the guide's code
In the guide's `decrementButton` handler the display is only refreshed when the count actually changes, so the label would not turn orange when `-` is pressed at 0 even though the guide says it should. I added one line (`updateDisplay()` in the `else` branch) so the behaviour matches the expected output. (The original requirements list also says red at 10, but the guide's code uses 20, so the code uses `MAX_VALUE = 20`.)

## Self-Check
**Q1. Why is `count` an instance variable?** Handlers run at different times; a local variable inside `createAndShow()` would be out of scope in the lambdas (lambdas can only capture effectively-final locals). Instance variables persist between events.
**Q2. What does `doClick()` do?** It programmatically simulates a click, firing the button's `ActionListener`, the same handler as a real click.
**Q3. Why check `count < MAX_VALUE`?** To enforce the business rule that the counter cannot exceed its maximum.
