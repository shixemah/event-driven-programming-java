import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Event-driven prototype of a Parking Garage.
 * Event sources: three buttons (entry sensor, payment kiosk, exit sensor) and a Timer.
 */
public class ParkingGaragePrototype extends JFrame {

    private static final int CAPACITY = 5;
    private static final int FEE_PER_CAR = 200; // simple flat fee (KSh)

    private int occupied = 0;
    private int paidCars = 0;   // cars inside that have already paid

    private JLabel statusLabel;
    private JLabel clockLabel;
    private JLabel spacesLabel;
    private JTextArea eventLog;

    public ParkingGaragePrototype() {
        setTitle("Parking Garage Controller");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ---- Status panel (NORTH) ----
        JPanel statusPanel = new JPanel(new GridLayout(3, 1));
        statusPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        statusLabel = new JLabel("System Ready");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        spacesLabel = new JLabel();
        clockLabel = new JLabel();
        statusPanel.add(statusLabel);
        statusPanel.add(spacesLabel);
        statusPanel.add(clockLabel);
        add(statusPanel, BorderLayout.NORTH);
        refreshSpaces();

        // ---- Event log (CENTER) ----
        eventLog = new JTextArea(10, 35);
        eventLog.setEditable(false);
        add(new JScrollPane(eventLog), BorderLayout.CENTER);

        // ---- Controls (SOUTH) ----
        JPanel controls = new JPanel(new GridLayout(3, 1, 5, 5));
        controls.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton entryButton = new JButton("Car Arrives at Entry");
        JButton payButton = new JButton("Pay Ticket at Kiosk");
        JButton exitButton = new JButton("Car Leaves at Exit");

        // EVENT HANDLER 1: CarArrived
        entryButton.addActionListener(e -> {
            logEvent("Entry sensor triggered: car arrived");
            if (occupied >= CAPACITY) {
                logEvent("GARAGE FULL - entry barrier stays down");
                statusLabel.setText("GARAGE FULL");
            } else {
                occupied++;
                logEvent("Ticket issued, entry barrier raised");
                statusLabel.setText("Car entered");
                if (occupied == CAPACITY) {
                    logEvent("Last space taken - FULL sign ON");
                }
            }
            refreshSpaces();
        });

        // EVENT HANDLER 2: TicketPaid
        payButton.addActionListener(e -> {
            logEvent("Payment kiosk: pay button pressed");
            if (paidCars < occupied) {
                paidCars++;
                logEvent("Fee of KSh " + FEE_PER_CAR + " paid, receipt printed");
                statusLabel.setText("Ticket paid");
            } else {
                logEvent("No unpaid tickets to pay");
                statusLabel.setText("Nothing to pay");
            }
        });

        // EVENT HANDLER 3: CarLeaving
        exitButton.addActionListener(e -> {
            logEvent("Exit sensor triggered: car at exit");
            if (occupied == 0) {
                logEvent("No cars inside - ignored");
                statusLabel.setText("Garage is empty");
            } else if (paidCars == 0) {
                logEvent("Unpaid ticket - exit barrier stays down");
                statusLabel.setText("Please pay first");
            } else {
                occupied--;
                paidCars--;
                logEvent("Exit barrier raised, space freed");
                statusLabel.setText("Car left");
            }
            refreshSpaces();
        });

        controls.add(entryButton);
        controls.add(payButton);
        controls.add(exitButton);
        add(controls, BorderLayout.SOUTH);

        // EVENT HANDLER 4: ClockTick - javax.swing.Timer fires on the EDT every second
        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");
        clockLabel.setText("Time: " + fmt.format(new Date()));
        new Timer(1000, e -> clockLabel.setText("Time: " + fmt.format(new Date()))).start();

        setSize(450, 480);
        setLocationRelativeTo(null);
    }

    private void refreshSpaces() {
        spacesLabel.setText("Free spaces: " + (CAPACITY - occupied) + " / " + CAPACITY);
    }

    private void logEvent(String event) {
        String timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
        eventLog.append("[" + timestamp + "] " + event + "\n");
        eventLog.setCaretPosition(eventLog.getDocument().getLength());
    }

    public static void main(String[] args) {
        // ALWAYS start Swing applications on the EDT
        SwingUtilities.invokeLater(() -> {
            new ParkingGaragePrototype().setVisible(true);
        });
    }
}
