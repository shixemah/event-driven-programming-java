# Event-Driven Programming in Java

A ten-week practical course project that builds up event-driven programming in Java, starting from the
core ideas (events, sources, listeners, handlers, the event loop) and ending with a database-backed
JavaFX application.

**Author:** Emmanuel Shikuku

## What this project covers

| Week | Folder | Topic |
|---|---|---|
| 1 | `Week1_EDP` | Foundations: event, source, listener, handler; the event loop and queue; procedural vs event-driven calculators; debugging common event-handler mistakes |
| 2 | `week02-observer-pattern` | The Observer pattern: a weather station, the deprecated `java.util.Observable`, refactoring tight coupling, and a comparison of notification mechanisms |
| 3 | `week03-swing-gui` | Building Swing GUIs: windows, components, layout managers, and the Event Dispatch Thread rule |
| 4 | `week04-event-handling` | Swing event handling: ActionListener (four styles), ItemListener, DocumentListener, a counter app, a registration form, mouse and key listeners |
| 5 | `week05-advanced-swing` | Advanced Swing: JList, JTable, JTree, tabs, menus, dialogs and a File Manager capstone |
| 6 | `week06-javafx` | Introduction to JavaFX: stages and scenes, layouts, FXML and controllers, a dashboard |
| 7 | `week07-javafx-events` | JavaFX events: filters vs handlers, mouse and key events, drag and drop, custom events |
| 8 | `week08-javafx-bindings` | Properties and bindings: listeners, unidirectional and bidirectional binding, a reactive form |
| 9 | `week09-async-javafx` | Asynchronous programming: why the UI freezes, `Task`, progress bars, `Service` |
| 10 | `week10-university-system` | Final project: a University Management System with MySQL, background Tasks and a scheduled heartbeat |

## Technologies
Java 17+, Swing, JavaFX 21, FXML, Maven, JDBC, MySQL.

## Key ideas demonstrated
- The event loop and event queue, and why the UI thread must never be blocked
- Loose coupling with the Observer pattern and custom events
- Keeping the UI responsive with background threads (`Task`, `Service`, `ScheduledExecutorService`)
- Declarative UI updates with JavaFX properties and bindings
- Separating the UI from the data layer in a small event-driven architecture

## How to run
- **Weeks 1 to 5 (plain Java / Swing):** open a terminal in the activity folder, then
  `javac *.java` followed by `java <ClassName>`. Each week's `README.md` lists the class names.
- **Weeks 6 to 10 (JavaFX / Maven):** requires JDK 17+ and Maven. From the week's folder run
  `mvn clean javafx:run` (the week's README lists the extra `javafx:run@name` commands for the other activities).
- **Week 10 needs MySQL:** run `database/schema.sql`, then set your password in an environment variable
  (`set DB_PASSWORD=...` in Command Prompt) before running. The password is never stored in the code.

## Repository notes
Each week's folder has its own `README.md`, a `SelfCheck.md` with answers to the activity questions,
and (where applicable) a `screenshots/` folder with pictures and console output from test runs.
Where the original activity guide contained a mistake (for example code that did not compile),
the fix is described in that week's `SelfCheck.md`.
