/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.onlineshoppingsystem;

/**
 *
 * @author Zaki
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OnlineShoppingSystem extends JFrame implements ActionListener {

    JLabel title, quantityLabel;
    JComboBox<String> products;
    JTextField quantityField;
    JTextArea billArea;
    JButton addButton, billButton, recommendationButton;

    String[] productNames = {"Laptop", "Mobile", "Headphones", "Keyboard", "Mouse"};
    double[] prices = {70000, 30000, 5000, 3000, 1500};
    int[] stock = {5, 10, 15, 20, 25};

    double total = 0;

public OnlineShoppingSystem() {

    setTitle("Online Shopping System");
    setSize(650, 550);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    // Main Panel (Sky Blue)
    JPanel mainPanel = new JPanel();
    mainPanel.setBackground(new Color(135, 206, 235));
    mainPanel.setLayout(null);
    add(mainPanel);

    // Black Panel
    JPanel panel = new JPanel();
    panel.setBounds(30, 30, 570, 450);
    panel.setBackground(Color.BLACK);
    panel.setLayout(null);
    mainPanel.add(panel);

    // Title
    title = new JLabel("Online Shopping System");
    title.setFont(new Font("Arial", Font.BOLD, 24));
    title.setForeground(Color.WHITE);
    title.setBounds(140, 20, 300, 30);
    panel.add(title);

    // Product Label
    JLabel p = new JLabel("Select Product:");
    p.setForeground(Color.WHITE);
    p.setFont(new Font("Arial", Font.BOLD, 14));
    p.setBounds(60, 80, 120, 30);
    panel.add(p);

    // Product Combo Box
    products = new JComboBox<>(productNames);
    products.setBounds(220, 80, 220, 30);
    panel.add(products);

    // Quantity Label
    quantityLabel = new JLabel("Quantity:");
    quantityLabel.setForeground(Color.WHITE);
    quantityLabel.setFont(new Font("Arial", Font.BOLD, 14));
    quantityLabel.setBounds(60, 130, 120, 30);
    panel.add(quantityLabel);

    // Quantity Field
    quantityField = new JTextField();
    quantityField.setBounds(220, 130, 220, 30);
    panel.add(quantityField);

    // Buttons
    addButton = new JButton("Add To Cart");
    addButton.setBounds(50, 200, 150, 40);
    addButton.setBackground(new Color(70, 130, 180));
    addButton.setForeground(Color.WHITE);

    billButton = new JButton("Generate Bill");
    billButton.setBounds(210, 200, 150, 40);
    billButton.setBackground(new Color(70, 130, 180));
    billButton.setForeground(Color.WHITE);

    recommendationButton = new JButton("Recommend");
    recommendationButton.setBounds(370, 200, 150, 40);
    recommendationButton.setBackground(new Color(70, 130, 180));
    recommendationButton.setForeground(Color.WHITE);

    panel.add(addButton);
    panel.add(billButton);
    panel.add(recommendationButton);

    // Bill Area
    billArea = new JTextArea();
    billArea.setEditable(false);
    billArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
    billArea.setBackground(new Color(224, 255, 255));

    JScrollPane scroll = new JScrollPane(billArea);
    scroll.setBounds(60, 280, 460, 120);
    panel.add(scroll);

    addButton.addActionListener(this);
    billButton.addActionListener(this);
    recommendationButton.addActionListener(this);

    setVisible(true);
}

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addButton) {

            int index = products.getSelectedIndex();
            int quantity;

            try {
                quantity = Integer.parseInt(quantityField.getText());

                if (quantity <= stock[index]) {
                    double amount = prices[index] * quantity;
                    total += amount;
                    stock[index] -= quantity;

                    billArea.append(productNames[index] +
                            " x " + quantity +
                            " = Rs." + amount + "\n");

                } else {
                    JOptionPane.showMessageDialog(this,
                            "Insufficient Stock!");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Enter a valid quantity.");
            }
        }

        else if (e.getSource() == billButton) {
            billArea.append("\n---------------------\n");
            billArea.append("Total Bill = Rs." + total + "\n");
            billArea.append("---------------------\n");
        }

        else if (e.getSource() == recommendationButton) {

            String recommendation;

            if (total >= 100000)
                recommendation = "Recommended: Headphones";
            else if (total >= 50000)
                recommendation = "Recommended: Mouse";
            else
                recommendation = "Recommended: Keyboard";

            JOptionPane.showMessageDialog(this, recommendation);
        }
    }

    public static void main(String[] args) {
        new OnlineShoppingSystem();
    }
}
