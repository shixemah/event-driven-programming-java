# Week 10 Final Project: University Management System

An event-driven JavaFX application backed by a MySQL database (Maven project `UniversitySystem`).

## Set-up
1. **Database:** in MySQL Workbench open and run `database/schema.sql` (creates `university_db` with the `programs` and `students` tables).
2. **Password:** the app reads your MySQL password from an environment variable, so it is never written into a file you might push to GitHub.
   - Command Prompt: `set DB_PASSWORD=your_real_password`
   - PowerShell / VS Code terminal: `$env:DB_PASSWORD="your_real_password"`
   (Optional overrides: `DB_USER` (default `root`) and `DB_URL` (default `jdbc:mysql://localhost:3306/university_db`).)
3. **Run** from this folder, in the same terminal: `mvn clean javafx:run`

Requirements: JDK 17+, Maven, MySQL server, internet the first time (Maven downloads JavaFX 21 and the MySQL connector).

## Using it
Dark sidebar on the left -> click **Students** -> type a registration number, first name and last name -> **Save Student** (it stays disabled until all three are filled). Watch the console: every 5 seconds it prints `System Heartbeat: ...`.

## Project layout
| Path | Purpose |
|---|---|
| `database/schema.sql` | Phase 1: tables |
| `pom.xml` | Phase 2: JavaFX + MySQL connector |
| `DatabaseUtil.java` | Phase 3: connection to the database |
| `Student.java` | Phase 4: data model with JavaFX properties |
| `LoadStudentsTask.java` | Phase 5: background database query (Task) |
| `StudentView.fxml`, `StudentController.java` | Phases 6-7: the students screen and its event handlers |
| `App.java` | Phase 8: main application + 5-second scheduler |
| `MainView.fxml`, `MainController.java` | Phase 9: sidebar navigation |

`screenshots/` holds pictures and console output from the test runs; `SelfCheck.md` explains what was tested and what I changed from the guide.
