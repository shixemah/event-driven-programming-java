package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Same as EventPropagationDemo, but the click target is a Label styled like a button.
 *
 * Why this file exists: a real JavaFX Button has built-in behaviour that consumes mouse events
 * after its own handlers run, so (on the JavaFX version I tested, 11) the root's bubbling handler
 * is never called when you click a Button. A Label does not consume the click, so all three
 * phases are visible: 1. filter (capturing)  ->  2. target handler  ->  3. root handler (bubbling).
 */
public class EventPropagationLabelDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label logLabel = new Label("Click the orange box...");
        Label target = new Label("Click Me!");
        target.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-padding: 8 16; "
                + "-fx-font-size: 14px;");

        VBox root = new VBox(20, logLabel, target);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // 1. EVENT FILTER on the root (Capturing Phase - top down)
        root.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            System.out.println("1. [FILTER] Root intercepted the click!");
            // e.consume();   // <- uncomment: the event never reaches the target or any handler
        });

        // 2. EVENT HANDLER on the target (the event's destination)
        target.setOnMouseClicked(e -> {
            System.out.println("2. [HANDLER] Target was clicked!");
            logLabel.setText("Target was clicked!");
        });

        // 3. EVENT HANDLER on the root (Bubbling Phase - bottom up)
        root.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            System.out.println("3. [HANDLER] Root saw the click bubbling up!");
        });

        primaryStage.setTitle("Event Propagation (Label target)");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
