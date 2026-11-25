# Week 3 Final Self-Assessment

(Rate yourself 1-5 honestly in the lines marked "Rating". Ratings are yours to give.)

## Outcome 1: Create a functional Swing application with a main window
Rating: ___

Minimal JFrame code from memory:
```java
import javax.swing.*;

public class Minimal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Title");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
```

## Outcome 2: Add, customize and arrange components using layouts
Rating: ___

| Layout | Use case |
|---|---|
| FlowLayout | Toolbar / row of buttons |
| BorderLayout | Main window with header, sidebar, footer |
| GridLayout | Calculator keypad or image grid |
| BoxLayout | Vertical stack of items or a form column |

## Outcome 3: Ensure GUI code runs on the EDT
Rating: ___

1. All GUI creation and updates must happen on the EDT (`SwingUtilities.invokeLater`).
2. Never perform long operations on the EDT (use a background thread, then `invokeLater` to update the GUI).

## Outcome 4: Build a static, non-interactive UI layout
Rating: ___

Did StudentPortal.java run successfully? **Yes** - it compiled and ran, printing `Mockup running on: AWT-EventQueue-0`.
