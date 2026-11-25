import javax.swing.*;
import java.awt.*;

public class ScholarshipGUI extends JFrame {
    private JTextField ageField, scoreField, failedField, incomeField;
    private JLabel resultLabel;

    public ScholarshipGUI() {
        setTitle("Scholarship Eligibility Checker");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        ageField = new JTextField();
        scoreField = new JTextField();
        failedField = new JTextField();
        incomeField = new JTextField();
        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JButton checkButton = new JButton("Check Eligibility");
        JButton clearButton = new JButton("Clear");

        add(new JLabel("  Student’s age:"));
        add(ageField);
        add(new JLabel("  Examination score:"));
        add(scoreField);
        add(new JLabel(" Number of failed courses:"));
        add(failedField);
        add(new JLabel(" Family income (cedis):"));
        add(incomeField);
        add(checkButton);
        add(clearButton);
        add(new JLabel(""));
        add(resultLabel);

        checkButton.addActionListener(e -> checkEligibility());
        clearButton.addActionListener(e -> {
            ageField.setText("");
            scoreField.setText("");
            failedField.setText("");
            incomeField.setText("");
            resultLabel.setText("");
        });
    }

    private void checkEligibility() {
        try {
            int age = Integer.parseInt(ageField.getText().trim());
            double score = Double.parseDouble(scoreField.getText().trim());
            int failed = Integer.parseInt(failedField.getText().trim());
            double income = Double.parseDouble(incomeField.getText().trim());

            boolean noFailed = (failed == 0);
            boolean special = (score >= 95 && noFailed);
            boolean normal = (score >= 80 && noFailed
                    && age >= 18 && age <= 25
                    && income < 3000);

            if (special || normal) {
                resultLabel.setText("QUALIFIES for the scholarship");
                resultLabel.setForeground(new Color(0, 130, 0));
            } else {
                resultLabel.setText("Does NOT qualify");
                resultLabel.setForeground(Color.RED);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numbers in all fields.",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ScholarshipGUI().setVisible(true));
    }
}