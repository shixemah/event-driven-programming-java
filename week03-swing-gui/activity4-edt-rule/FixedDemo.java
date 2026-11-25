import javax.swing.*;
import java.awt.*;

public class FixedDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("EDT Demo - GOOD Practice");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new FlowLayout());

            JLabel status = new JLabel("Ready");
            JButton button = new JButton("Do Long Operation");

            // GOOD: run the long operation in a background thread
            button.addActionListener(e -> {
                status.setText("Working...");
                button.setEnabled(false);
                System.out.println("Handler running on: " + Thread.currentThread().getName());

                new Thread(() -> {
                    System.out.println("Long work running on: " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(5000); // blocks THIS thread, not the EDT
                    } catch (InterruptedException ex) { }

                    // CRITICAL: update the GUI on the EDT
                    SwingUtilities.invokeLater(() -> {
                        status.setText("Done!");
                        button.setEnabled(true);
                        System.out.println("GUI updated on: " + Thread.currentThread().getName());
                    });
                }).start();
            });

            frame.add(button);
            frame.add(status);
            frame.setVisible(true);
        });
    }
}
