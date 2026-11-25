# Activity 2: Build the Event Loop (Paper Simulation)

## Round summaries

- **Round 1 (simple processing):** Cards were moved one at a time from the sources area to the queue and on to the loop. Each card was read aloud and given a handler, e.g. "Button A Clicked" -> Button A's ActionListener -> display "Button A clicked"; "Timer Fired" -> timer handler; "Window Resized" -> layout handler; "Key Pressed: 'A'" -> KeyListener appends "A"; "Mouse Moved" -> MouseMotionListener updates the cursor position.
- **Round 2 (multiple events):** Five cards were queued and processed strictly left to right (FIFO). Even with many events waiting, only one is handled at a time.
- **Round 3 (blocked loop):** While one card was held for the "30-second" handler, the other four cards stayed waiting in the queue and nothing else was processed.

## Questions

**1. What happened to the other events while you were "stuck" on the slow handler?**
They stayed in the queue, unprocessed. The loop can only take the next event after the current handler finishes, so every later event was delayed.

**2. What would a user see if this happened in a real application?**
A frozen window: buttons do not respond, the window will not repaint, move or resize properly, and the OS may mark it "Not Responding". Clicks made during the freeze are processed all at once afterwards.

**3. How could this problem be fixed?**
Never run long tasks inside a handler on the event loop. Move them to a background thread (or `SwingWorker`), and when they finish post the result back to the event thread (`SwingUtilities.invokeLater`) to update the GUI.

## Reflection

The event queue is a first-in-first-out line that stores events (clicks, key presses, timer ticks) in the order they arrive from the event sources, so none are lost while the program is busy. The event loop is a continuous cycle that checks the queue, takes the next event, dispatches it to the right handler, and then repeats; in Java Swing this loop runs on the Event Dispatch Thread. Because events are processed one at a time, any handler that takes a long time holds up everything behind it. That is why the event loop should never be blocked: the GUI cannot repaint or respond while the loop is stuck, and the user perceives the application as frozen. Slow work must therefore go to a background thread, with only short, quick updates left on the event loop. Doing the simulation made it clear why responsive interfaces depend on short handlers.
