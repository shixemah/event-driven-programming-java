# Bug Fix Documentation

## Bug 1: Blocking the EDT
- **Symptom:** UI freezes for 5 seconds when "Process" is clicked; the window cannot be moved and the label does not repaint.
- **Cause:** `Thread.sleep(5000)` ran inside `actionPerformed` on the Event Dispatch Thread, so the event loop could not process other events.
- **Fix:** Run the operation in a background thread, update the UI on the EDT.
- **Code Change:** Wrapped the sleep in `new Thread(() -> {...}).start()`. The handler now sets the label to "Processing..." and disables the button, and when the thread finishes it calls `SwingUtilities.invokeLater(...)` to set "Done!" and re-enable the button.

## Bug 2: Missing Listener
- **Symptom:** Clicking "Increment" does nothing; the count stays at 0.
- **Cause:** No ActionListener registered, so the button's click event had no handler.
- **Fix:** Added ActionListener to increment button.
- **Code Change:** Added `incrementButton.addActionListener(e -> { count++; countLabel.setText("Count: " + count); });`.

## Bug 3: Wrong Thread
- **Symptom:** Erratic behavior and potential crashes/stale display, because Swing components are not thread-safe.
- **Cause:** The background clock thread called `timeLabel.setText(...)` directly instead of on the EDT.
- **Fix:** Used `SwingUtilities.invokeLater()` for GUI updates.
- **Code Change:** The background thread builds the time string, then calls `SwingUtilities.invokeLater(() -> timeLabel.setText(now))`. I also made `running` `volatile` and ignored repeated clicks so only one clock thread runs.

## Bug 4: Memory Leak
- **Symptom:** Memory usage grows over time as buttons are added and removed.
- **Cause:** "Remove All" cleared the panel but `createdButtons` still held references to every removed button, so they could never be garbage collected.
- **Fix:** Clear the list when removing buttons.
- **Code Change:** Added `createdButtons.clear();` in the "Remove All" handler (right after `buttonPanel.removeAll();`). A side benefit: new buttons are numbered from 0 again.
