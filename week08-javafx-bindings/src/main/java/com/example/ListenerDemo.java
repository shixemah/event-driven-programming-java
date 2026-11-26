package com.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class ListenerDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        Person person = new Person();

        // 1. InvalidationListener (fires when the property becomes "invalid")
        person.nameProperty().addListener((observable) -> {
            System.out.println("1. [InvalidationListener] Name was changed!");
        });

        // 2. ChangeListener (provides old and new values)
        person.nameProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("2. [ChangeListener] Name changed from '"
                    + oldValue + "' to '" + newValue + "'");
        });

        // Trigger the changes
        System.out.println("--- Setting name to Alice ---");
        person.setName("Alice");

        System.out.println("\n--- Setting name to Bob ---");
        person.setName("Bob");

        System.out.println("\n--- Setting name to Bob (same value) ---");
        person.setName("Bob"); // Neither listener fires here!

        // No window is ever shown, so JavaFX would keep running forever
        // (and "mvn javafx:run" would hang). Exit explicitly once the output is printed.
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
