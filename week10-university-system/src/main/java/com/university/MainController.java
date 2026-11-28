package com.university;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class MainController {

    // This connects to the StackPane in MainView.fxml
    @FXML private StackPane centerPane;

    // Triggered when the Dashboard button is clicked
    @FXML
    private void showDashboard() {
        // Create a simple label and set it as the center content
        centerPane.getChildren().setAll(new Label("Welcome to the University Dashboard!"));
    }

    // Triggered when the Students button is clicked
    @FXML
    private void showStudents() {
        try {
            // Load the StudentView.fxml file
            Parent studentView = FXMLLoader.load(getClass().getResource("StudentView.fxml"));
            // Replace the center content with the StudentView
            centerPane.getChildren().setAll(studentView);
        } catch (IOException e) {
            // Print an error if the file isn't found
            e.printStackTrace();
        }
    }
}
