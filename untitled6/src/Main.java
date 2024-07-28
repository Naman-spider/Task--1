
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private JTextField input1Field;
    private JTextField input2Field;
    private JTextField resultField;
    private JComboBox<String> operationComboBox;
    public JButton calculateButton;
    public JTextArea instructionsArea;

    public Main() {
        setupGUI();
        setVisible(true);
    }

    private void setupGUI() {
        setTitle("Percentage Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Instructions
        instructionsArea = new JTextArea(
                """
                        Instructions:
                        1. Enter two numbers in Input 1 and Input 2 fields.
                        2. Select the desired operation from the dropdown.
                        3. Click the Calculate button to see the result.
                        Operations:
                        - Calculate Percentage: (Input 1 / Input 2) * 100
                        - Percentage Increase: ((Input 2 - Input 1) / Input 1) * 100
                        - Percentage Decrease: ((Input 1 - Input 2) / Input 1) * 100
                        - Find Whole: (Input 1 * 100) / Input 2
                        """
        );
        instructionsArea.setEditable(false);
        add(instructionsArea, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        JLabel input1Label = new JLabel("Input 1:");
        input1Field = new JTextField();
        JLabel input2Label = new JLabel("Input 2:");
        input2Field = new JTextField();
        JLabel operationLabel = new JLabel("Operation:");
        String[] operations = {"Calculate Percentage", "Percentage Increase", "Percentage Decrease", "Find Whole"};
        operationComboBox = new JComboBox<>(operations);
        JLabel resultLabel = new JLabel("Result:");
        resultField = new JTextField();
        resultField.setEditable(false);
        calculateButton = new JButton("Calculate");

        panel.add(input1Label);
        panel.add(input1Field);
        panel.add(input2Label);
        panel.add(input2Field);
        panel.add(operationLabel);
        panel.add(operationComboBox);
        panel.add(resultLabel);
        panel.add(resultField);
        panel.add(new JLabel());
        panel.add(calculateButton);

        add(panel, BorderLayout.CENTER);

        calculateButton.addActionListener(new CalculateButtonListener());
    }

    private double calculateResult(double input1, double input2, String operation) {
        return switch (operation) {
            case "Calculate Percentage" -> (input1 / input2) * 100;
            case "Percentage Increase" -> ((input2 - input1) / input1) * 100;
            case "Percentage Decrease" -> ((input1 - input2) / input1) * 100;
            case "Find Whole" -> (input1 * 100) / input2;
            default -> throw new IllegalArgumentException("Invalid operation");
        };
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double input1 = Double.parseDouble(input1Field.getText());
                double input2 = Double.parseDouble(input2Field.getText());
                String operation = (String) operationComboBox.getSelectedItem();
                assert operation != null;
                double result = calculateResult(input1, input2, operation);

                resultField.setText(String.format("%.2f", result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
