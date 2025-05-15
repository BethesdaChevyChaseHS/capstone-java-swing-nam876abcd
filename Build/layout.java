import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A simple calculator GUI using Java Swing.
 * - Supports addition, subtraction, multiplication, and division.
 * - Shows each equation and result, with results right-aligned.
 * - Maintains a history of calculations in the answer box.
 */
public class layout {
    public static void main(String[] args) {
        // Create the main calculator window (JFrame)
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(700, 800);

        // Parent panel holds all UI components (title, answer box, buttons)
        JPanel parentPanel = new JPanel(new BorderLayout());

        // --- Title Section ---
        // Displays the calculator's title at the top
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
        answerBox.setEditable(false); // User cannot type directly
        answerBox.setText("");        // Start with empty text
        answerBox.setFont(new Font("Arial", Font.PLAIN, 20));
        answerBox.setLineWrap(true);  // Wrap lines if too long
        answerBox.setWrapStyleWord(true); // Wrap at word boundaries
        answerBox.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Black border for visibility
        answerPanel.add(answerBox);
        parentPanel.add(answerPanel, BorderLayout.CENTER);

        // --- Calculator State Variables ---
        StringBuilder currentInput = new StringBuilder(); // Holds the current equation being typed (e.g., "12 + 7")
        StringBuilder history = new StringBuilder();      // Holds all previous equations/results for display
        int[] operand = new int[2];                      // Stores the two operands for calculation
        String[] operator = new String[1];               // Stores the operator ("+", "-", "*", "/")
        final boolean[] waitingForSecondOperand = {false}; // Tracks if we're waiting for the second number

        // --- Button Panel Section ---
        // Holds all calculator buttons in a grid layout
        JPanel buttonPanel = new JPanel(new GridLayout(5, 3, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // --- Number Buttons (0-9) ---
        // When clicked, append the digit to the current input and update the answer box
        ActionListener numberListener = e -> {
            String digit = ((JButton) e.getSource()).getText();
            currentInput.append(digit); // Add digit to current input
            // Show the current input appended to the history
            answerBox.setText(history + currentInput.toString());
        };
        // Create and add buttons 1-9
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(numberListener);
            buttonPanel.add(button);
        }

        // --- Zero Button ---
        // Button for digit 0, works like other number buttons
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
                operand[0] = Integer.parseInt(currentInput.toString()); // Store first number
                operator[0] = "-"; // Store operator
                currentInput.append(" - "); // Add operator to input string
                answerBox.setText(history + currentInput.toString()); // Update display
                waitingForSecondOperand[0] = true; // Now waiting for second number
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
                operand[0] = Integer.parseInt(currentInput.toString()); // Store first number
                operator[0] = "+"; // Store operator
                currentInput.append(" + "); // Add operator to input string
                answerBox.setText(history + currentInput.toString()); // Update display
                waitingForSecondOperand[0] = true; // Now waiting for second number
            }
        });
        buttonPanel.add(plusButton);

        // --- Division Button ---
        // When clicked, store the first operand and operator, and prepare for the second operand
        JButton divideButton = new JButton("/");
        divideButton.setFont(new Font("Arial", Font.BOLD, 20));
        divideButton.addActionListener(e -> {
            // Only allow operator if not already waiting for second operand
            if (currentInput.length() > 0 && !waitingForSecondOperand[0]) {
                operand[0] = Integer.parseInt(currentInput.toString()); // Store first number
                operator[0] = "/"; // Store operator
                currentInput.append(" / "); // Add operator to input string
                answerBox.setText(history + currentInput.toString()); // Update display
                waitingForSecondOperand[0] = true; // Now waiting for second number
            }
        });
        buttonPanel.add(divideButton);

        // --- Multiplication Button ---
        // When clicked, store the first operand and operator, and prepare for the second operand
        JButton multiplyButton = new JButton("*");
        multiplyButton.setFont(new Font("Arial", Font.BOLD, 20));
        multiplyButton.addActionListener(e -> {
            // Only allow operator if not already waiting for second operand
            if (currentInput.length() > 0 && !waitingForSecondOperand[0]) {
                operand[0] = Integer.parseInt(currentInput.toString()); // Store first number
                operator[0] = "*"; // Store operator
                currentInput.append(" * "); // Add operator to input string
                answerBox.setText(history + currentInput.toString()); // Update display
                waitingForSecondOperand[0] = true; // Now waiting for second number
            }
        });
        buttonPanel.add(multiplyButton);

        // --- Equals Button ---
        // When clicked, parses the input, performs the calculation (including division and multiplication),
        // and displays the result right-aligned in the answer box. Also handles division by zero.
        JButton equalsButton = new JButton("=");
        equalsButton.setFont(new Font("Arial", Font.BOLD, 20));
        equalsButton.addActionListener(e -> {
            // Only compute if an operator and second operand are present
            if (waitingForSecondOperand[0] && currentInput.length() > 0) {
                // Split input into two operands based on operator (+, -, *, /)
                String regex = " [\\+\\-\\*/] ";
                String[] parts = currentInput.toString().split(regex);
                if (parts.length == 2) {
                    try {
                        operand[1] = Integer.parseInt(parts[1].trim()); // Parse second operand
                        int result = 0;
                        boolean error = false;
                        // --- Computational Logic ---
                        // Perform the calculation based on the operator
                        switch (operator[0]) {
                            case "+":
                                result = operand[0] + operand[1];
                                break;
                            case "-":
                                result = operand[0] - operand[1];
                                break;
                            case "*":
                                result = operand[0] * operand[1];
                                break;
                            case "/":
                                if (operand[1] == 0) {
                                    error = true; // Division by zero
                                } else {
                                    result = operand[0] / operand[1];
                                }
                                break;
                        }
                        // Format: equation left, result right-aligned
                        if (error) {
                            // If division by zero, show "Error" right-aligned
                            String line = String.format("%-20s%10s\n", currentInput.toString(), "Error");
                            history.append(line);
                        } else {
                            // Otherwise, show result right-aligned
                            String line = String.format("%-20s%10d\n", currentInput.toString(), result);
                            history.append(line);
                        }
                        answerBox.setText(history.toString()); // Update the answer box with the full history
                    } catch (NumberFormatException ex) {
                        // If parsing fails, show error
                        answerBox.setText(history + "Error\n");
                    }
                }
                // Reset for next equation
                currentInput.setLength(0);
                waitingForSecondOperand[0] = false;
            }
        });
        buttonPanel.add(equalsButton);

        // Add the button panel to the parent panel (bottom of calculator)
        parentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the parent panel to the frame (top section)
        frame.add(parentPanel, BorderLayout.NORTH);
        frame.setVisible(true);
    }
}