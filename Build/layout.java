import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class layout {
    public static void main(String[] args) {
        System.out.println("die");

        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new java.awt.BorderLayout()); 
        frame.setSize(700, 800); 
        
        // Parent panel to hold title and answer sections
        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());

        // Top panel for the title
        JPanel topPanel = new JPanel();
        JLabel titleLabel = new JLabel("Nam's Calculator");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size and style
        topPanel.add(titleLabel);
        parentPanel.add(topPanel, BorderLayout.NORTH);

        // Panel for the answer display box
        JPanel answerPanel = new JPanel();
        answerPanel.setPreferredSize(new Dimension(400, 300)); // Set width and height
        answerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Align to top-left
        answerPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding

        JTextArea answerBox = new JTextArea(10, 30); // Multi-line text area
        answerBox.setEditable(false); // Make it read-only
        answerBox.setText("0"); // Default value
        answerBox.setFont(new Font("Arial", Font.PLAIN, 20)); // Set font size
        answerBox.setLineWrap(true); // Enable line wrapping
        answerBox.setWrapStyleWord(true); // Wrap at word boundaries
        answerBox.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add a border
        answerPanel.add(answerBox);

        parentPanel.add(answerPanel, BorderLayout.CENTER);
        
        // Panel for the number buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 3, 10, 10)); // 4 rows, 3 columns, with spacing
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding

        // Add buttons 0 to 9
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.setFont(new Font("Arial", Font.BOLD, 20)); // Set font size and style
            buttonPanel.add(button);
        }

        // Add the "0" button in the last row
        JButton zeroButton = new JButton("0");
        zeroButton.setFont(new Font("Arial", Font.BOLD, 20)); // Set font size and style
        buttonPanel.add(zeroButton);

        JButton minusButton = new JButton("-");
        minusButton.setFont(new Font("Arial", Font.BOLD, 20)); // Set font size and style
        buttonPanel.add(minusButton);
        
        JButton plusButton = new JButton("+");
        plusButton.setFont(new Font("Arial", Font.BOLD, 20)); // Set font size and style
        buttonPanel.add(plusButton);
        
        // Add a new row with "/", "*", and "=" buttons
        JButton divideButton = new JButton("/");
        divideButton.setFont(new Font("Arial", Font.BOLD, 20)); // Set font size and style
        buttonPanel.add(divideButton);
        
        JButton multiplyButton = new JButton("*");
        multiplyButton.setFont(new Font("Arial", Font.BOLD, 20)); // Set font size and style
        buttonPanel.add(multiplyButton);
        
        JButton equalsButton = new JButton("=");
        equalsButton.setFont(new Font("Arial", Font.BOLD, 20)); // Set font size and style
        buttonPanel.add(equalsButton);

        parentPanel.add(buttonPanel, BorderLayout.SOUTH); // Add the button panel below the answer box

        frame.add(parentPanel, BorderLayout.NORTH); // Add the parent panel to the top section

        frame.setVisible(true);
    }
}