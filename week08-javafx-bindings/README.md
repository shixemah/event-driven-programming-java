# Week 8: Properties and Bindings in JavaFX

A Maven project (same structure as Weeks 6 and 7). Run each activity from this folder:

| Activity | Class | Run |
|---|---|---|
| 1. Observable properties (the model) | `Person` (no `main`; used by Activity 2) | - |
| 2. ChangeListener vs InvalidationListener | `ListenerDemo` (prints to the console, no window) | `mvn clean javafx:run` |
| 3. Unidirectional binding | `UnidirectionalDemo` | `mvn clean javafx:run@unidirectional` |
| 4. Bidirectional binding | `BidirectionalDemo` | `mvn clean javafx:run@bidirectional` |
| 5. Capstone: reactive registration form | `ReactiveFormApp` | `mvn clean javafx:run@form` |

Requirements: JDK 17 or newer, Maven, and internet the first time (Maven downloads JavaFX 21).
`screenshots/` holds pictures and console output from real test runs; answers are in `SelfCheck.md`.
