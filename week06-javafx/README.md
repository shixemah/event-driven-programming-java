# Week 6: Introduction to Modern JavaFX

A Maven project (the layout the guide asks for: `src/main/java/com/example` and `src/main/resources/com/example`).

| Activity | Files | Run (from this folder) |
|---|---|---|
| 1. First JavaFX window | `App.java` | `mvn clean javafx:run` |
| 2. Layouts (HBox, VBox, GridPane, BorderPane) | `LayoutDemo.java` | `mvn clean javafx:run@layout` |
| 3. Scene Builder + FXML | `src/main/resources/com/example/login.fxml` | open it in Scene Builder |
| 4. FXML + Controller | `LoginController.java`, `FXMLApp.java`, `login.fxml` | `mvn clean javafx:run@fxml` |
| 5. Capstone dashboard | `DashboardApp.java` | `mvn clean javafx:run@dashboard` |

Requirements: JDK 17 or newer, Maven, and an internet connection the first time so Maven can download JavaFX 21.
`screenshots/` holds pictures and console output from real test runs. Answers are in `SelfCheck.md`.

**About the run commands:** the guide uses `-Djavafx.mainClass=...`, but that flag is ignored when the pom already sets `<mainClass>`. I added three named executions to `pom.xml` (`layout`, `fxml`, `dashboard`) so the commands above work without editing the pom.
