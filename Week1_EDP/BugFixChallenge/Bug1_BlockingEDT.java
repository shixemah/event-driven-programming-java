import javax.swing.*;

public class Bug1_BlockingEDT extends JFrame {

    private JLabel statusLabel;
    private JButton processButton;

    public Bug1_BlockingEDT() {
        setTitle("Bug 1 - Blocking EDT (FIXED)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new java.awt.FlowLayout());

        statusLabel = new JLabel("Ready");
        processButton = new JButton("Process");

        processButton.addActionListener(e -> {
            // FIX: handler returns immediately; the slow work runs in a background thread
            processButton.setEnabled(false);
            statusLabel.setText("Processing...");

            new Thread(() -> {
                try {
                    Thread.sleep(5000); // long operation, now off the EDT
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                // FIX: GUI updates go back onto the EDT
                SwingUtilities.invokeLater(() -> {
                    statusLabel.setText("Done!");
                    processButton.setEnabled(true);
                });
            }).start();
        });

        add(statusLabel);
        add(processButton);
        setSize(300, 150);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Bug1_BlockingEDT().setVisible(true);
        });
    }
}
