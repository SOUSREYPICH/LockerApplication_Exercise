import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LockerApp extends JFrame {

    private JTextField passcodeField;
    private JLabel statusLabel;
    private String savedPasscode = null;

    public LockerApp() {
        // Set up the frame
        setTitle("Lock Class");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the passcode field
        passcodeField = new JTextField();
        passcodeField.setEditable(false);
        add(passcodeField, BorderLayout.NORTH);

        // Create the status label
        statusLabel = new JLabel("Enter Password");
        add(statusLabel, BorderLayout.SOUTH);

        // Create the panel for number buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 3));

        // Add number buttons
        for (int i = 1; i <= 9; i++) {
            addButton(buttonPanel, String.valueOf(i));
        }

        // Add clear button
        addButton(buttonPanel, "Clear");

        // Add 0 button and Enter button
        addButton(buttonPanel, "0");
        addButton(buttonPanel, "Enter");

        add(buttonPanel, BorderLayout.CENTER);
    }

    private void addButton(Container container, String label) {
        JButton button = new JButton(label);
        button.addActionListener(new ButtonClickListener());
        container.add(button);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("Clear")) {
                passcodeField.setText("");
                statusLabel.setText("Enter Password");
            } else if (command.equals("Enter")) {
                String enteredPasscode = passcodeField.getText();
                if (savedPasscode == null) {
                    savedPasscode = enteredPasscode;
                    statusLabel.setText("Password Set");
                } else {
                    if (savedPasscode.equals(enteredPasscode)) {
                        statusLabel.setText("Correct Password");
                    } else {
                        statusLabel.setText("Incorrect Password");
                    }
                }
                passcodeField.setText("");
            } else {
                // Handle number button clicks
                passcodeField.setText(passcodeField.getText() + command);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LockerApp lockerApplication = new LockerApp();
            lockerApplication.setVisible(true);
        });
    }
}