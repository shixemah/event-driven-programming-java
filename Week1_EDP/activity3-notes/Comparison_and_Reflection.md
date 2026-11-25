# Activity 3: Procedural vs. Event-Driven

## Procedural calculator: questions

1. **Exact order of steps:** (1) print title, (2) read first number, (3) read operator, (4) read second number, (5) calculate with a `switch`, (6) display the result, (7) program ends.
2. **Wrong input at step 2:** an invalid operator falls to the `default` case, prints "Invalid operator" and the program ends. If a non-number is typed where a number is expected, `nextDouble()` throws an `InputMismatchException` and the program crashes. Either way the user cannot correct the entry.
3. **Calculate again without restarting?** No. After the result is printed, `main()` finishes and the program exits.
4. **Who controls the flow?** The program. It decides what is asked and in which order; the user can only follow the script.

## Event-driven calculator: questions

1. **Buttons in any order?** Yes. Each button has its own handler, so the user can press digits, operators, `=` or `C` whenever they like (though nonsense orders give odd results).
2. **Mistake?** Press `C` (Clear) to reset the display, first operand and operator, then continue. No restart is needed.
3. **Calculate again without restarting?** Yes. After `=`, `startNewNumber` is true, so typing a digit starts a fresh calculation, and the window stays open.
4. **Who controls the flow?** The user. The program waits for events and reacts to whatever the user does.
5. **How many event handlers?** There are 4 handler definitions (number, operator, equals, clear), written as lambdas inside the four factory methods. At run time they are registered on 16 buttons (10 number + 4 operator + 1 equals + 1 clear), so 16 handler instances; the `invokeLater` Runnable is a fifth lambda for starting the GUI.

## Comparison table

| Aspect | Procedural | Event-Driven |
|---|---|---|
| Who controls the flow? | The program | The user |
| Can user make mistakes and recover easily? | No - the program exits or crashes; user must restart | Yes - click "C" and continue |
| Can user interact in any order? | No - fixed sequence: number, operator, number | Yes - any button at any time |
| How many possible execution paths? | One main path (plus error exits) | Many, depending on the sequence of events |
| Code organization | `main()` does everything in sequence | Factory methods creating buttons + separate handlers |
| User experience | Console-based, rigid | GUI, flexible and interactive |

## Reflection

The procedural calculator runs from the first line to the last in a fixed order controlled by the program, so the user can only answer its prompts, and a single wrong input ends the program. The event-driven calculator instead waits for events and runs small handlers in response, so the user decides what happens next and can recover from a mistake with the Clear button. For interactive applications I prefer the event-driven approach because it matches how people actually use software: they click what they want, when they want. Event-driven programming is better for GUIs because a window has many controls that can be used in any order, and handlers let each control react independently without the programmer predicting every sequence. It also keeps the code organized, since each handler does one job. Procedural code is still simpler for short, linear tasks such as batch scripts, but it does not suit interactive interfaces.
