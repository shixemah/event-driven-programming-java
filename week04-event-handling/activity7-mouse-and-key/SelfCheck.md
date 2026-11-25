# Activity 6 (Bonus): Mouse and Key Listeners

The guide calls this Activity 6 but its folder list names it `activity7-mouse-and-key`, so I kept that folder name. Screenshots: `../screenshots/act7_*.png`.

## What I tested
- **DrawingApp:** a real mouse press-and-drag across the canvas drew a trail of dots (`act7_drawing_after_drag.png`). A new press clears the old drawing.
- **KeyboardMover:** three Right and two Down key events moved the ball from (250, 200) to (280, 220); `KeyboardMover_output.txt` shows each position. (Keys are sent straight to the panel's KeyListener in my test. On your PC, click the white area first so it has keyboard focus.)

## Self-Check
**Q1. MouseListener vs MouseMotionListener?** `MouseListener` handles clicks, presses, releases and entering/exiting; `MouseMotionListener` handles moving and dragging.
**Q2. Why MouseAdapter?** `MouseListener` has five methods; `MouseAdapter` supplies empty defaults, so you override only what you need.
**Q3. Why might a KeyListener not fire?** The component must have keyboard focus: `setFocusable(true)` and `requestFocusInWindow()`.
**Q4. What does `e.getKeyCode()` return?** An integer code for the key, e.g. `KeyEvent.VK_UP`, `VK_A`, `VK_SPACE`.
