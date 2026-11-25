# Activity 4: The EDT Rule - What I Observed

Both programs were run on a virtual display and the button was really clicked. Console output is in `FreezeDemo_output.txt` and `FixedDemo_output.txt`; screenshots are in `../screenshots/act4_*`.

## FreezeDemo (bad practice)
- Console: `Handler running on: AWT-EventQueue-0`. The 5-second `Thread.sleep()` ran **on the EDT**.
- During the 5 seconds the window did not repaint: the label still read "Ready" even though the code had already called `setText("Working...")`, and the button stayed in its pressed look. The change only appears after the handler returns, so the user also cannot move, resize or click.
- After 5 seconds the label finally changed to "Done!".

## FixedDemo (good practice)
- Console: the handler ran on `AWT-EventQueue-0`, the long work ran on `Thread-0`, and the final GUI update ran on `AWT-EventQueue-0` again.
- Right after the click the label showed "Working..." and the button was greyed out, so the EDT was free to repaint. The window stayed responsive for the whole 5 seconds.
- After 5 seconds the label changed to "Done!" and the button was re-enabled.

Side-by-side during the 5 seconds (left = FreezeDemo, right = FixedDemo): `../screenshots/act4_compare_during_click.png`.

## The two golden rules
1. All GUI creation and updates must happen on the EDT (`SwingUtilities.invokeLater`).
2. Never perform long operations on the EDT; run them in a background thread and post the result back to the EDT.

## Self-Check
**Q1. Why does Thread.sleep() on the EDT freeze the UI?** The EDT runs the event loop. While it is sleeping it cannot process events, so the UI cannot repaint, respond to clicks or resize.
**Q2. Safe way to update a label from a background thread?** `SwingUtilities.invokeLater(() -> label.setText("..."))`.
**Q3. What if you violate the rule?** Best case the UI freezes; worst case it glitches, shows stale data or crashes, because Swing is not thread-safe.
