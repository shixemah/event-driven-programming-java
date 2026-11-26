# Week 7: Event Handling in JavaFX

A Maven project (same structure as Week 6). Run each activity from this folder:

| Activity | Class | Run |
|---|---|---|
| 1. Event filters vs handlers | `EventPropagationDemo` (Button, as in the guide) | `mvn clean javafx:run` |
| 1 (variant). Same, with a Label target so all 3 phases print | `EventPropagationLabelDemo` | `mvn clean javafx:run@label` |
| 2. Mouse and key events | `MouseKeyDemo` | `mvn clean javafx:run@mousekey` |
| 3. Drag and drop | `DragDropDemo` | `mvn clean javafx:run@dragdrop` |
| 4. Custom events | `LoginSuccessEvent`, `CustomEventDemo` | `mvn clean javafx:run@customevent` |

Requirements: JDK 17 or newer, Maven, and internet the first time (Maven downloads JavaFX 21).
`screenshots/` holds pictures and console output from real test runs; answers are in `SelfCheck.md`.
