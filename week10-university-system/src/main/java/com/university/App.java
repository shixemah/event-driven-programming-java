package com.university;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App extends Application {

    private ScheduledExecutorService scheduler;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 1. Load the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));

        // 2. Set up the Scene and Stage
        primaryStage.setTitle("University Management System");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

        // 3. LO3: Schedule a background task to run every 5 seconds
        // Create a scheduler with a pool of 1 thread. The thread is made a DAEMON thread (not in the
        // guide): the guide's version uses a normal thread, which keeps the JVM alive after you close
        // the window, so the program never actually exits and the heartbeat keeps printing.
        scheduler = Executors.newScheduledThreadPool(1, runnable -> {
            Thread t = new Thread(runnable, "heartbeat-scheduler");
            t.setDaemon(true);
            return t;
        });

        // Define a task that runs automatically
        Runnable heartbeat = () -> {
            // Print to the console every 5 seconds (simulating a background check)
            System.out.println("System Heartbeat: Checking for updates at " + new java.util.Date());
            // If you wanted to update the UI, you would use Platform.runLater()
            // Platform.runLater(() -> statusLabel.setText("Heartbeat check..."));
        };

        // Schedule the task: run after 0 seconds, then every 5 seconds
        scheduler.scheduleAtFixedRate(heartbeat, 0, 5, TimeUnit.SECONDS);
    }

    // Called by JavaFX when the application is closing: stop the scheduler cleanly
    @Override
    public void stop() {
        if (scheduler != null) {
            scheduler.shutdownNow();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
