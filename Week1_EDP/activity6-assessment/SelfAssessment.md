# Activity 6: Final Self-Assessment

## Learning Outcome 1: Define EDP Components

**1. What is an event? Give two examples.** Something that happens in the system that the program may need to react to. Examples: a user clicking a button; a timer expiring.

**2. What is an event source? Give two examples.** The object that generates the event. Examples: a `JButton` (click event); a `Timer` (timeout event).

**3. What is an event listener? Give two examples.** The object that waits for and receives events from a source. Examples: `ActionListener`; `MouseListener`.

**4. What is an event handler? Give two examples.** The code that runs when the event occurs. Examples: the body of `actionPerformed()`; the code inside a lambda such as `e -> display.setText("0")`.

**5. Diagram:**

```
 Event Source  --generates-->  Event  --delivered to-->  Event Listener  --calls-->  Event Handler
 (JButton)                     (click)                   (ActionListener)           (actionPerformed code)
      ^                                                                                  |
      +------------------------ GUI / system state updated <-----------------------------+
```

## Learning Outcome 2: Compare Paradigms

**6. How does procedural programming differ from event-driven programming?** Procedural code runs step by step in a fixed order controlled by the program (`main()` does everything). Event-driven code waits for events and runs handlers in response, so the user controls the order and the flow is non-linear.

**7. Main advantage of event-driven programming for GUIs?** The interface stays interactive: users can use any control in any order and recover from mistakes, because every control has its own handler.

**8. When would you choose procedural?** For simple, linear tasks with no interaction, such as a batch script, a data-processing job, or a one-off console calculation.

## Learning Outcome 3: Event Loop and Queue

**9. What is the event loop? What does it do?** A continuous loop (`while (true)`) that takes the next event from the queue and dispatches it to the right handler, then repeats.

**10. What is the event queue? How does it work?** A FIFO (first in, first out) line where events from sources wait until the loop processes them one at a time.

**11. Why should the event loop never be blocked?** While a handler is running the loop cannot process anything else, so the GUI cannot repaint or respond and appears frozen.

**12. What is the EDT in Java Swing?** The Event Dispatch Thread: the single thread that runs the Swing event loop, handles all GUI events and must be the only thread that updates Swing components.

## Learning Outcome 4: Real-World Examples

**13. Five real-world event-driven systems:** ATM, microwave oven, traffic lights, elevator, video game (also: smart home, online shop, social media).

**14. One system, all four components (ATM):** Source = keypad button; Event = button pressed; Listener = ATM software; Handler = verify PIN.

**15. Why are most modern applications event-driven?** Modern apps are interactive and must react to unpredictable user actions, sensor input, network messages and timers at any moment; event-driven design handles all of these without wasting time waiting in a fixed sequence.

## Score

I answered all 15 questions after completing the activities: **15/15** (Excellent - mastered Week 1 concepts).
