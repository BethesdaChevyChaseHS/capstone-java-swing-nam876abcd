import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A simple calculator GUI using Java Swing.
 * - Supports addition and subtraction.
 * - Shows each equation and result, with results right-aligned.
 * - Maintains a history of calculations in the answer box.
 */
public class layout {
    public static void main(String[] args) {
        // Create the main calculator window
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(700, 800);

        // Parent panel holds all UI components
        JPanel parentPanel = new JPanel(new BorderLayout());

        // --- Title Section ---
        JPanel topPanel = new JPanel();
        JLabel titleLabel = new JLabel("Nam's Calculator");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel);
        parentPanel.add(topPanel, BorderLayout.NORTH);

        // --- Answer Box Section ---
        // This area displays the current input and the history of calculations
        JPanel answerPanel = new JPanel();
        answerPanel.setPreferredSize(new Dimension(400, 300));
        answerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        answerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JTextArea answerBox = new JTextArea(10, 30);
        answerBox.setEditable(false);
        answerBox.setText("");
        answerBox.setFont(new Font("Arial", Font.PLAIN, 20));
        answerBox.setLineWrap(true);
        answerBox.setWrapStyleWord(true);
        answerBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        answerPanel.add(answerBox);
        parentPanel.add(answerPanel, BorderLayout.CENTER);

        // --- Calculator State Variables ---
        StringBuilder currentInput = new StringBuilder(); // Holds the current equation being typed
        StringBuilder history = new StringBuilder();      // Holds all previous equations/results
        int[] operand = new int[2];                      // Stores the two operands for calculation
        String[] operator = new String[1];               // Stores the operator ("+" or "-")
        final boolean[] waitingForSecondOperand = {false}; // Tracks if we're waiting for the second number

        // --- Button Panel Section ---
        JPanel buttonPanel = new JPanel(new GridLayout(5, 3, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // --- Number Buttons (0-9) ---
        // When clicked, append the digit to the current input and update the answer box
        ActionListener numberListener = e -> {
            String digit = ((JButton) e.getSource()).getText();
            currentInput.append(digit);
            // Show the current input appended to the history
            answerBox.setText(history + currentInput.toString());
        };
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(numberListener);
            buttonPanel.add(button);
        }

        // --- Zero Button ---
        JButton zeroButton = new JButton("0");
        zeroButton.setFont(new Font("Arial", Font.BOLD, 20));
        zeroButton.addActionListener(numberListener);
        buttonPanel.add(zeroButton);

        // --- Minus Button ---
        // When clicked, store the first operand and operator, and prepare for the second operand
        JButton minusButton = new JButton("-");
        minusButton.setFont(new Font("Arial", Font.BOLD, 20));
        minusButton.addActionListener(e -> {
            // Only allow operator if not already waiting for second operand
            if (currentInput.length() > 0 && !waitingForSecondOperand[0]) {
                operand[0] = Integer.parseInt(currentInput.toString());
                operator[0] = "-";
                currentInput.append(" - ");
                answerBox.setText(history + currentInput.toString());
                waitingForSecondOperand[0] = true;
            }
        });
        buttonPanel.add(minusButton);

        // --- Plus Button ---
        // When clicked, store the first operand and operator, and prepare for the second operand
        JButton plusButton = new JButton("+");
        plusButton.setFont(new Font("Arial", Font.BOLD, 20));
        plusButton.addActionListener(e -> {
            // Only allow operator if not already waiting for second operand
            if (currentInput.length() > 0 && !waitingForSecondOperand[0]) {
                operand[0] = Integer.parseInt(currentInput.toString());
                operator[0] = "+";
                currentInput.append(" + ");
                answerBox.setText(history + currentInput.toString());
                waitingForSecondOperand[0] = true;
            }
        });
        buttonPanel.add(plusButton);

        // --- Division and Multiplication Buttons (Disabled) ---
        // These are placeholders for future functionality
        JButton divideButton = new JButton("/");
        divideButton.setFont(new Font("Arial", Font.BOLD, 20));
        divideButton.setEnabled(false); // Not implemented yet
        buttonPanel.add(divideButton);

        JButton multiplyButton = new JButton("*");
        multiplyButton.setFont(new Font("Arial", Font.BOLD, 20));
        multiplyButton.setEnabled(false); // Not implemented yet
        buttonPanel.add(multiplyButton);

        // --- Equals Button ---
        // When clicked, parses the input, performs the calculation, and displays the result right-aligned
        JButton equalsButton = new JButton("=");
        equalsButton.setFont(new Font("Arial", Font.BOLD, 20));
        equalsButton.addActionListener(e -> {
            // Only compute if an operator and second operand are present
            if (waitingForSecondOperand[0] && currentInput.length() > 0) {
                // Split input into two operands based on operator
                String[] parts = currentInput.toString().split(" [+-] ");
                if (parts.length == 2) {
                    try {
                        operand[1] = Integer.parseInt(parts[1].trim());
                        int result = 0;
                        // --- Computational Logic ---
                        // Perform the calculation based on the operator
                        if ("+".equals(operator[0])) {
                            result = operand[0] + operand[1];
                        } else if ("-".equals(operator[0])) {
                            result = operand[0] - operand[1];
                        }
                        // Format: equation left, result right-aligned
                        String line = String.format("%-20s%10d\n", currentInput.toString(), result);
                        history.append(line); // Add this equation/result to the history
                        answerBox.setText(history.toString()); // Update the answer box with the full history
                    } catch (NumberFormatException ex) {
                        answerBox.setText(history + "Error\n");
                    }
                }
                // Reset for next equation
                currentInput.setLength(0);
                waitingForSecondOperand[0] = false;
            }
        });
        buttonPanel.add(equalsButton);

        // Add the button panel to the parent panel
        parentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the parent panel to the frame (top section)
        frame.add(parentPanel, BorderLayout.NORTH);
        frame.setVisible(true);
    }
}