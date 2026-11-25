# Week 6 Self-Check and Test Notes

## How this was tested
I ran each application on a virtual display with scripted button clicks and typing, taking snapshots of the real windows (`screenshots/`). I could not run `mvn` here (Maven's download servers are not reachable from my test machine), so the code was compiled with `javac` against JavaFX 11 instead of JavaFX 21. The programs use only features common to both versions, but **please run `mvn clean javafx:run` once on your PC** to confirm the pom works with your setup.

## Activity 1: First JavaFX window (`App.java`)
Clicking "Click Me!" changed the label to "Button was clicked!" and printed `Button clicked on: JavaFX Application Thread` (`screenshots/activity1_console_output.txt`, snapshots `act1_*.png`).

**Q1. Stage vs Scene?** The `Stage` is the window itself (title bar, buttons); the `Scene` is the content shown inside it. A stage shows one scene at a time, but scenes can be swapped.
**Q2. Name of the main thread?** The *JavaFX Application Thread*, the equivalent of Swing's EDT. All UI changes must happen on it.

## Activity 2: Layouts (`LayoutDemo.java`) - snapshot `act2_layouts.png`
`HBox` (horizontal row, header), `VBox` (vertical column, sidebar), `GridPane` (3x3 grid of buttons) and `BorderPane` (top, left and centre regions that holds the others).

## Activity 3: Scene Builder and FXML (`login.fxml`)
`login.fxml` is the file Scene Builder produces for the login form: a `VBox` (spacing 15, centred, padding 20) with a Label, a `TextField` (`fx:id="usernameField"`), a `PasswordField` (`fx:id="passwordField"`) and a Button (`fx:id="loginButton"`). I wrote the XML directly, so it already contains `fx:controller="com.example.LoginController"` and the `onAction="#handleLoginButtonAction"` from Activity 4. To do the drag-and-drop steps yourself, open the file in Scene Builder (File > Open) - it loads exactly as the guide describes.

## Activity 4: FXML + Controller (`LoginController.java`, `FXMLApp.java`)
Console output (`screenshots/activity4_console_output.txt`):
- empty fields -> `Error: Fields cannot be empty.`
- admin / password123 -> `Attempting login for: admin` then `Login Successful!` (fields cleared)
- user / pass -> `Attempting login for: user` then `Login Failed!`

**Q1. What does @FXML do?** It tells the FXML loader to inject the component with the matching `fx:id` into that field (or to make the method available to `onAction`), even when the field is `private`.
**Q2. Why FXML instead of Java?** Separation of concerns: the layout (View) lives in a file designers can edit visually, and the Java Controller holds the logic, which keeps code cleaner and easier to maintain (MVC).

## Activity 5: Capstone dashboard (`DashboardApp.java`) - snapshot `act5_dashboard.png`
Dark top bar, light sidebar with three buttons, a welcome message and three coloured cards (Revenue $12,450, Users 1,204, Orders 342). The sidebar emoji are written as `\uXXXX` escapes so the file compiles on any computer; on my test machine no emoji font was installed, so the house and door icons appear blank in my snapshot, but they will show on Windows.

## Fix to the guide
`-Djavafx.mainClass=...` does not override a `<mainClass>` that is already in the pom, so I added `javafx:run@layout`, `@fxml` and `@dashboard` (see README).
