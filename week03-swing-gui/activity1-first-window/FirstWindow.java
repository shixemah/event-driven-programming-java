import javax.swing.*; // Swing components (JFrame, JButton, etc.)

// FIRST SWING WINDOW: the simplest possible Swing application.
public class FirstWindow {
    public static void main(String[] args) {
        // CRITICAL: all Swing code must run on the EDT (the golden rule of Swing).
        SwingUtilities.invokeLater(() -> {
            createAndShowWindow();
        });
    }

    private static void createAndShowWindow() {
        // 1. Create the JFrame (the main window)
        JFrame frame = new JFrame("My First Swing App");
        // 2. EXIT_ON_CLOSE terminates the JVM when the window closes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 3. Size in pixels (width x height)
        frame.setSize(500, 400);
        // 4. Center the window on the screen
        frame.setLocationRelativeTo(null);
        // 5. Make the window visible (hidden by default)
        frame.setVisible(true);
        // Log to console to prove we're on the EDT
        System.out.println("Window created on thread: " + Thread.currentThread().getName());
    }
}
