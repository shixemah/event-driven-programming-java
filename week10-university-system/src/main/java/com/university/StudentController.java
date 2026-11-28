package com.university;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class StudentController {

    // The @FXML annotation connects these variables to the fx:id in the FXML file
    @FXML private TextField regField;                        // The reg number input field
    @FXML private TextField firstNameField;                  // The first name input field
    @FXML private TextField lastNameField;                   // The last name input field
    @FXML private TableView<Student> studentTable;           // The table displaying students
    @FXML private TableColumn<Student, String> colReg;       // Table column for Reg Number
    @FXML private TableColumn<Student, String> colFirst;     // Table column for First Name
    @FXML private TableColumn<Student, String> colLast;      // Table column for Last Name
    @FXML private Label statusLabel;                         // The status message label
    @FXML private Button saveButton;                         // The save button

    // This list holds the data that the TableView displays
    private ObservableList<Student> studentList = FXCollections.observableArrayList();

    // The initialize() method is automatically called when the FXML loads
    @FXML
    public void initialize() {
        // 1. Connect the TableColumns to the Student properties (Week 8 skill)
        colReg.setCellValueFactory(cellData -> cellData.getValue().regNumberProperty());
        colFirst.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        colLast.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // 2. Tell the TableView to use our studentList
        studentTable.setItems(studentList);

        // 3. Disable the Save button if the text fields are empty (Week 8 Binding)
        saveButton.disableProperty().bind(
                regField.textProperty().isEmpty()                    // If reg field is empty
                        .or(firstNameField.textProperty().isEmpty()) // OR first name is empty
                        .or(lastNameField.textProperty().isEmpty())  // OR last name is empty
        );

        // 4. Load the initial data from the database
        loadStudents(false);
    }

    // This method is triggered when the user clicks "Save Student"
    @FXML
    private void handleSaveStudent() {
        // Get the text the user typed into the fields
        String reg = regField.getText();
        String fName = firstNameField.getText();
        String lName = lastNameField.getText();

        // Create a Background Task to save the data (Producer-Consumer Pattern)
        Task<Void> saveTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                // The SQL query to insert a new student
                String sql = "INSERT INTO students (registration_number, first_name, last_name, email) VALUES (?, ?, ?, ?)";

                // Connect and execute
                try (Connection conn = DatabaseUtil.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, reg);                  // Replace 1st ? with reg
                    stmt.setString(2, fName);                // Replace 2nd ? with first name
                    stmt.setString(3, lName);                // Replace 3rd ? with last name
                    stmt.setString(4, reg + "@university.edu"); // Generate a fake email
                    stmt.executeUpdate();                    // Execute the insert
                }
                return null;
            }
        };

        // When the background task succeeds, update the UI
        saveTask.setOnSucceeded(e -> {
            setStatus("Student saved successfully!", Color.BLUE);              // Update label
            regField.clear(); firstNameField.clear(); lastNameField.clear();   // Clear fields
            loadStudents(true);                                                // Refresh the table to show the new student
        });

        // NOT IN THE GUIDE: without this, a failed save (e.g. a duplicate registration number, or the
        // database being switched off) would silently do nothing and the user would never know.
        saveTask.setOnFailed(e -> setStatus("Could not save student: "
                + saveTask.getException().getMessage(), Color.RED));

        // Start the task on a background thread
        new Thread(saveTask).start();
    }

    // Method to load students from the database.
    // afterSave: the guide's version overwrote "Student saved successfully!" with "Loaded N students..."
    // a few milliseconds later, so the user never saw the success message. Now it stays visible.
    private void loadStudents(boolean afterSave) {
        // Create our custom background task
        LoadStudentsTask task = new LoadStudentsTask();

        // When the task finishes, update the UI
        task.setOnSucceeded(e -> {
            // Get the list returned by the task and put it in our TableView
            studentList.setAll(task.getValue());
            setStatus((afterSave ? "Student saved successfully! " : "")
                    + "Loaded " + studentList.size() + " students from database.", Color.BLUE);
        });

        // NOT IN THE GUIDE: tell the user when the database cannot be reached
        task.setOnFailed(e -> setStatus("Could not load students: "
                + task.getException().getMessage(), Color.RED));

        // Start the task on a new background thread
        new Thread(task).start();
    }

    private void setStatus(String message, Color color) {
        statusLabel.setText(message);
        statusLabel.setTextFill(color);
    }
}
