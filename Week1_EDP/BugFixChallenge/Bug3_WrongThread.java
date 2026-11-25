import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Bug3_WrongThread extends JFrame {

    private JLabel timeLabel;
    private JButton startButton;
    // FIX: volatile so the background thread sees changes made on the EDT
    private volatile boolean running = false;

    public Bug3_WrongThread() {
        setTitle("Bug 3 - Wrong Thread (FIXED)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        timeLabel = new JLabel("Time: ");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        startButton = new JButton("Start Clock");
        startButton.addActionListener(e -> {
            if (running) {
                return; // avoid starting a second clock thread
            }
            running = true;
            new Thread(() -> {
                while (running) {
                    String now = "Time: " + new Date();
                    // FIX: the label is updated on the EDT, not the background thread
                    SwingUtilities.invokeLater(() -> timeLabel.setText(now));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        });

        add(timeLabel);
        add(startButton);
        setSize(350, 150);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Bug3_WrongThread().setVisible(true);
        });
    }
}
