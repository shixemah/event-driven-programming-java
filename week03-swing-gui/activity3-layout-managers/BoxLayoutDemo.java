import javax.swing.*;
import java.awt.*;

// The 4th layout manager named in the Activity 3 purpose: BoxLayout.
public class BoxLayoutDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("BoxLayout Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(450, 350);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout(10, 10));

            // Vertical stack (Y_AXIS)
            JPanel vertical = new JPanel();
            vertical.setLayout(new BoxLayout(vertical, BoxLayout.Y_AXIS));
            vertical.setBorder(BorderFactory.createTitledBorder("Y_AXIS (vertical stack)"));
            for (int i = 1; i <= 4; i++) {
                JButton b = new JButton("Item " + i);
                b.setAlignmentX(Component.CENTER_ALIGNMENT);
                vertical.add(b);
                vertical.add(Box.createVerticalStrut(8)); // invisible spacer
            }
            frame.add(vertical, BorderLayout.CENTER);

            // Horizontal row (X_AXIS)
            JPanel horizontal = new JPanel();
            horizontal.setLayout(new BoxLayout(horizontal, BoxLayout.X_AXIS));
            horizontal.setBorder(BorderFactory.createTitledBorder("X_AXIS (horizontal row)"));
            horizontal.add(new JButton("Left"));
            horizontal.add(Box.createHorizontalGlue()); // pushes the next button to the right edge
            horizontal.add(new JButton("Right"));
            frame.add(horizontal, BorderLayout.SOUTH);

            frame.setVisible(true);
        });
    }
}
