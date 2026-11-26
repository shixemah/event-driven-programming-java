# Week 9 Self-Check and Test Notes

## How this was tested
Each application was run on a virtual display. Buttons were clicked from code (not with a physical mouse), and state dumps, console output and snapshots were saved in `screenshots/`. For the freeze/fix comparison I measured how long the JavaFX Application Thread takes to answer a trivial request after the button is clicked - a number, which is better evidence than "I tried to drag the window".

**Not tested here:** `mvn` itself (Maven's servers are not reachable from my test machine) and JavaFX 21; I compiled and ran against JavaFX 11. Please run `mvn clean javafx:run` once on your PC to confirm the pom works, and try dragging the window yourself in Activities 1 and 2.

## Two problems in the guide (fixed)
1. **`startButton.setEnabled(false)` does not compile (Activities 2 and 3).** JavaFX buttons have no `setEnabled()` - that is a Swing method (the compiler says "cannot find symbol: method setEnabled(boolean)"). The JavaFX method is `setDisable(true)` / `setDisable(false)`, which the guide itself uses correctly in Activity 4.
2. **The guide's answer to the Activity 2 question is wrong.** It says `setOnSucceeded` runs on the background thread. It does not: `setOnSucceeded`, `setOnFailed` and `setOnCancelled` handlers run on the **JavaFX Application Thread**; only `call()` runs in the background. I added print statements to prove it (see below). So `Platform.runLater()` inside `setOnSucceeded` is harmless but unnecessary; it *is* required if you touch the UI from inside `call()`.

## Activity 1: The Freeze (`activity1_console_output.txt`, `act1_during_freeze.png`)
Console: `Handler running on: JavaFX Application Thread`. About 1.5 s after the click I asked the UI thread to do a trivial job and it **answered only after 2682 ms**, i.e. when the handler finally finished. The screenshot taken during the freeze still shows "Ready": the label had already been set to "Processing..." in code, but the window could not repaint. After the 5 seconds the label showed "Done!".

**Q1. Why did the window freeze for 5 seconds?** `Thread.sleep(5000)` ran on the JavaFX Application Thread, which also processes events and repaints the screen. While it slept, no events (moving, resizing, repainting) could be handled.

## Activity 2: The Fix (`activity2_console_output.txt`, `act2_*.png`)
```
call() running on: Thread-3
[UI_thread_during_TaskDemo] FX thread answered after 0 ms
setOnSucceeded running on: JavaFX Application Thread
```
During the task the button was disabled and the label said "Processing..."; the UI thread answered instantly (0 ms, compared with 2682 ms in Activity 1). After 5 seconds the label said "Task Complete!" and the button was enabled again.

**Q1. Why `Platform.runLater()` inside `setOnSucceeded`?** The guide says it is because the handler runs on the background thread, but as shown above it already runs on the JavaFX thread, so `runLater` is not needed there. The rule it illustrates is still right: UI changes must happen on the JavaFX Application Thread, so code running inside `call()` has to use `Platform.runLater()` (or `updateProgress` / `updateMessage`) to touch the UI.

## Activity 3: Live Progress (`activity3_console_output.txt`, `act3_*.png`)
The bar and label updated live: 38% at about 1.5 s, 84% at about 3 s, then "Download Complete!" with the bar at 100% and the button enabled again. Clicking Download again started a second run (bar and label rebound to the new task).

**Q1. `updateProgress()` vs `Platform.runLater()`?** `updateProgress()` / `updateMessage()` are built-in `Task` methods that can be called from `call()` and safely update the task's progress and message properties on the UI thread (they are batched so the UI is not flooded). `Platform.runLater()` runs any block of code on the UI thread; use it for things the Task properties do not cover.
**Q2. Why `unbind()` at the end?** A bound property cannot be set manually; without `unbind()`, `statusLabel.setText("Download Complete!")` would throw a `RuntimeException` ("A bound value cannot be set").

## Activity 4: The Service (`activity4_console_output.txt`, `act4_*.png`)
| Step | Sync button | Cancel button | Spinner | Label |
|---|---|---|---|---|
| Start | enabled | disabled | hidden | Ready |
| After Sync Data | disabled | enabled | visible | Syncing... |
| After Cancel | enabled | disabled | hidden | Sync Cancelled. |
| After Sync Data again (restart) | disabled | enabled | visible | Syncing... |
| After 5 seconds | enabled | disabled | hidden | Data Synced Successfully! |

**Q1. Task vs Service?** A `Task` can run only once. A `Service` creates a new `Task` each time it is started, so it can be started, cancelled and restarted repeatedly (e.g. a "Refresh data" button).
**Q2. How do you detect Cancel inside `call()`?** Call `isCancelled()` in the loop and `break` so the background work stops.
