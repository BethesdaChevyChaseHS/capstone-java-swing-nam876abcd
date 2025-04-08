import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Die {
    public static void main(String[] args) {
        System.out.println("die");

        JFrame frame = new JFrame("NOOOOOOOOOOOO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new java.awt.BorderLayout()); 
        frame.setSize(800, 400); 

        JPanel topPanel = new JPanel();
        JLabel titleLabel = new JLabel("Nam's funeral"); 
        topPanel.add(titleLabel);
        frame.add(topPanel, java.awt.BorderLayout.NORTH);

        frame.setVisible(true);

    }
}