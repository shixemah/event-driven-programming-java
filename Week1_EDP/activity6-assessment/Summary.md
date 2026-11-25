# Week 1 Summary: Event-Driven Programming

**Emmanuel Shikuku - 25ZAD110806**

## Key definitions
- **Event:** something that happens (click, timer, sensor reading).
- **Event source:** object that generates it (`JButton`, `Timer`).
- **Event listener:** object that waits for the event (`ActionListener`).
- **Event handler:** code that runs in response (`actionPerformed` body or lambda).
- **Event queue:** FIFO line of pending events. **Event loop:** takes events from the queue and dispatches them, forever.
- **EDT:** the Swing thread that runs the event loop and all GUI updates.

## Important diagram
```
Sources -> Event Queue (FIFO) -> Event Loop (EDT) -> Handler -> GUI update -> (back to waiting)
```

## Paradigms
| | Procedural | Event-driven |
|---|---|---|
| Control | Program | User |
| Flow | Linear, fixed | Reactive, any order |
| Example | Console calculator | Swing calculator |
(Functional programming: pure functions, immutable data, no side effects, e.g. stream operations.)

## Code examples I found helpful
```java
btn.addActionListener(e -> display.setText("0"));      // register a handler
SwingUtilities.invokeLater(() -> label.setText("Done")); // update the GUI on the EDT
new Thread(() -> { /* slow work */ }).start();          // keep slow work off the EDT
```

## The four bugs
1. Blocking the EDT -> use a background thread. 2. Missing listener -> register an `ActionListener`. 3. GUI updated from the wrong thread -> `invokeLater`. 4. Memory leak -> clear the list when removing components.

## Questions I still have
- When should `SwingWorker` be used instead of a raw `Thread` plus `invokeLater`?
- How does the Observer pattern (Week 2) relate to Swing's listener mechanism?
- How are events handled in non-Swing systems such as web browsers and servers?
