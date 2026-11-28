# Week 10 Final Project: Test Notes and Changes

## How this was tested
I installed a database server on my test machine, loaded your `database/schema.sql`, and ran the whole application against it. The app was driven with scripted clicks and typing (not a physical mouse or keyboard); console output, table contents, status messages and snapshots are in `screenshots/`, and I also queried the database directly afterwards to confirm the data really was saved (`screenshots/database_rows_after_test.txt`).

**Not exactly the same as your set-up:** my test machine could not install Oracle MySQL or `mysql-connector-j 8.3.0`. I used **MariaDB 10.11** (a MySQL-compatible server) with **MariaDB Connector/J 2.7.6**, through the same `jdbc:mysql://localhost:3306/university_db` URL, and JavaFX 11 instead of 21. The SQL and JDBC code is standard, so it should behave the same on MySQL, but **please run it once on your own PC** (`mvn clean javafx:run`) to confirm. I also could not run Maven or MySQL Workbench here.

## Results (all as the guide describes, Phase 10)
| Guide step | What I saw |
|---|---|
| Run App: dark sidebar on the left | Yes (`app_1_start.png`). Dashboard button shows "Welcome to the University Dashboard!" |
| Click Students: table is empty | `Table rows=0`, status "Loaded 0 students from database." |
| Save disabled until all fields are filled | Disabled with 0, 1 or 2 fields filled; enabled with all 3 |
| Click Save Student | Row `S101 / Emmanuel / Shikuku` appears; fields cleared; table refreshed; status "Student saved successfully! Loaded 1 students from database." |
| Database check | `students` table holds S101 and S102 with generated emails `S101@university.edu` and `S102@university.edu` |
| Console: Heartbeat every 5 seconds | `System Heartbeat: Checking for updates at ...` printed at 07:16:28, :33, :38, :43, :48 |

## Changes from the guide (and why)
1. **Password security (important).** The guide tells you to type your real MySQL password into `DatabaseUtil.java`. If you push that to a **public** GitHub repo, anyone can read it. `DatabaseUtil` now reads `DB_PASSWORD` from an environment variable (default `your_password`, a harmless placeholder). **Never commit your real password.**
2. **The program never exits (scheduler thread).** The guide's `Executors.newScheduledThreadPool(1)` makes a normal thread, which keeps Java running after you close the window. I tested both versions by closing the window: the guide's version was still running after 20 seconds (exit code 124, heartbeat still printing); mine exited cleanly (exit code 0). The fix: make the scheduler thread a daemon thread and shut the scheduler down in `stop()`.
3. **Silent failures.** If a save or load failed, the guide's code did nothing, and the user was never told. I added `setOnFailed` handlers: saving a duplicate registration number shows, in red, `Could not save student: ... Duplicate entry 'S101' for key 'registration_number'`; with the database switched off, loading and saving both show `Could not load / save ... Connection refused` (`screenshots/database_down_output.txt`).
4. **The success message vanished.** After a save the guide sets "Student saved successfully!" and immediately reloads the table, which overwrites it with "Loaded N students" a few milliseconds later, so the user never sees the success message. It now reads "Student saved successfully! Loaded N students from database."
5. **Small copy errors.** The guide's `MainView.fxml` has `-fx- background-color` (a space from a line break), which is invalid CSS; it is `-fx-background-color`. `schema.sql` uses `IF NOT EXISTS`, so you can re-run it safely.

## Mismatch between the learning outcome and the steps
LO3 says "auto-refresh the dashboard every 5 seconds", but the guide's steps only print a "heartbeat" line in the console; the dashboard itself never refreshes. I implemented exactly the guide's steps. If your lecturer expects a dashboard that updates, the scheduler's task could use `Platform.runLater()` to show a live student count on the Dashboard screen; ask me and I will add it.

## How it meets the four learning outcomes
- **LO1 (EDA principles):** the UI (`StudentController`) never talks to MySQL directly; `DatabaseUtil` and the Tasks sit in between.
- **LO2 (Producer-Consumer):** `LoadStudentsTask` and the save Task run database work on background threads; the UI thread only handles results (`setOnSucceeded`), so it never freezes.
- **LO3 (Scheduled tasks):** `ScheduledExecutorService` runs the heartbeat every 5 seconds.
- **LO4 (Event communication):** button clicks (`onAction`), property bindings (the Save button's `disableProperty`) and the `TableView` bound to an `ObservableList` connect the parts without tight coupling.
