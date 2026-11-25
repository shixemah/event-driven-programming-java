import javax.swing.*;
import java.awt.*;

/**
 * Activity 5: Interactive Registration Form.
 * The guide builds this visually in NetBeans (Matisse). This is the same form written by hand
 * with the same variable names and the same three handlers, so it runs without NetBeans.
 */
public class RegistrationForm extends JFrame {

    private JLabel titleLabel, nameLabel, emailLabel, passwordLabel, genderLabel, countryLabel, commentsLabel, statusLabel;
    private JTextField nameField, emailField;
    private JPasswordField passwordField;
    private JRadioButton maleRadio, femaleRadio, otherRadio;
    private ButtonGroup genderGroup;
    private JCheckBox sportsCheck, musicCheck, readingCheck;
    private JComboBox<String> countryBox;
    private JTextArea commentsArea;
    private JButton submitButton, clearButton, exitButton;

    public RegistrationForm() {
        setTitle("Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 560);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        initComponents();
    }

    private void initComponents() {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 10, 5, 10);
        c.anchor = GridBagConstraints.WEST;

        // 1. Title
        titleLabel = new JLabel("Registration Form");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2; c.anchor = GridBagConstraints.CENTER;
        add(titleLabel, c);
        c.gridwidth = 1; c.anchor = GridBagConstraints.WEST;

        // 2-3. Name
        nameLabel = new JLabel("Full Name:");
        nameField = new JTextField(30);
        addRow(c, 1, nameLabel, nameField);

        // 4-5. Email
        emailLabel = new JLabel("Email:");
        emailField = new JTextField(30);
        addRow(c, 2, emailLabel, emailField);

        // 6-7. Password
        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(30);
        addRow(c, 3, passwordLabel, passwordField);

        // 8-11. Gender (radio buttons in one ButtonGroup)
        genderLabel = new JLabel("Gender:");
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        otherRadio = new JRadioButton("Other");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(otherRadio);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        genderPanel.add(otherRadio);
        addRow(c, 4, genderLabel, genderPanel);

        // 12-14. Interests (checkboxes)
        sportsCheck = new JCheckBox("Sports");
        musicCheck = new JCheckBox("Music");
        readingCheck = new JCheckBox("Reading");
        JPanel interestPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        interestPanel.add(sportsCheck);
        interestPanel.add(musicCheck);
        interestPanel.add(readingCheck);
        addRow(c, 5, new JLabel("Interests:"), interestPanel);

        // 15-16. Country
        countryLabel = new JLabel("Country:");
        countryBox = new JComboBox<>(new String[]{"Kenya", "Tanzania", "Uganda", "Rwanda"});
        addRow(c, 6, countryLabel, countryBox);

        // 17-18. Comments (inside a scroll pane)
        commentsLabel = new JLabel("Comments:");
        commentsArea = new JTextArea(3, 30);
        commentsArea.setLineWrap(true);
        addRow(c, 7, commentsLabel, new JScrollPane(commentsArea));

        // 19-21. Buttons
        submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(100, 200, 100));
        clearButton = new JButton("Clear");
        exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(230, 90, 90));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);
        c.gridx = 0; c.gridy = 8; c.gridwidth = 2; c.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, c);

        // 22. Status label
        statusLabel = new JLabel("Status: Ready");
        c.gridy = 9;
        add(statusLabel, c);

        // ---- EVENT HANDLERS ----
        submitButton.addActionListener(this::submitButtonActionPerformed);
        clearButton.addActionListener(this::clearButtonActionPerformed);
        exitButton.addActionListener(this::exitButtonActionPerformed);
    }

    private void addRow(GridBagConstraints c, int row, JComponent label, JComponent field) {
        c.gridy = row; c.gridwidth = 1; c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        add(label, c);
        c.gridx = 1;
        add(field, c);
    }

    // ---------------- Submit ----------------
    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        // Validation
        if (name.isEmpty()) {
            statusLabel.setText("Error: Please enter your name");
            statusLabel.setForeground(java.awt.Color.RED);
            return;
        }
        if (email.isEmpty() || !email.contains("@")) {
            statusLabel.setText("Error: Please enter a valid email");
            statusLabel.setForeground(java.awt.Color.RED);
            return;
        }
        if (password.length() < 6) {
            statusLabel.setText("Error: Password must be at least 6 characters");
            statusLabel.setForeground(java.awt.Color.RED);
            return;
        }

        // Gather gender
        String gender = "Not specified";
        if (maleRadio.isSelected()) gender = "Male";
        else if (femaleRadio.isSelected()) gender = "Female";
        else if (otherRadio.isSelected()) gender = "Other";

        // Gather interests
        StringBuilder interests = new StringBuilder();
        if (sportsCheck.isSelected()) interests.append("Sports ");
        if (musicCheck.isSelected()) interests.append("Music ");
        if (readingCheck.isSelected()) interests.append("Reading ");

        String country = (String) countryBox.getSelectedItem();
        String comments = commentsArea.getText().trim();

        String message = "Registration Successful!\n\n"
                + "Name: " + name + "\n"
                + "Email: " + email + "\n"
                + "Gender: " + gender + "\n"
                + "Interests: " + (interests.length() > 0 ? interests.toString() : "None") + "\n"
                + "Country: " + country + "\n"
                + "Comments: " + (comments.isEmpty() ? "None" : comments);

        javax.swing.JOptionPane.showMessageDialog(this, message,
                "Registration Complete",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);

        statusLabel.setText("Registration submitted successfully!");
        statusLabel.setForeground(new java.awt.Color(0, 128, 0));
    }

    // ---------------- Clear ----------------
    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {
        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        genderGroup.clearSelection();
        sportsCheck.setSelected(false);
        musicCheck.setSelected(false);
        readingCheck.setSelected(false);
        countryBox.setSelectedIndex(0);
        commentsArea.setText("");
        statusLabel.setText("Status: Form cleared");
        statusLabel.setForeground(java.awt.Color.BLACK);
    }

    // ---------------- Exit ----------------
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int choice = javax.swing.JOptionPane.showConfirmDialog(this,
                "Are you sure you want to exit?",
                "Confirm Exit",
                javax.swing.JOptionPane.YES_NO_OPTION);
        if (choice == javax.swing.JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationForm().setVisible(true));
    }
}
