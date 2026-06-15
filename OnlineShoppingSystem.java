/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.onlineshoppingsystem;

/**
 *
 * @author Zaki
 */
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class OnlineShoppingSystem extends JFrame implements ActionListener {

    JLabel title, quantityLabel, stockLabel;
    JComboBox<String> products;
    JTextField quantityField;
    JTextArea billArea;
    JButton addButton, billButton, recommendationButton, clearButton;

    String[] productNames = {
            "Laptop", "Mobile", "Headphones", "Keyboard",
            "Mouse", "Smart Watch", "Tablet", "Printer",
            "Speaker", "Power Bank"
    };

    double[] prices = {
            70000, 30000, 5000, 3000,
            1500, 8000, 25000, 12000,
            4000, 2500
    };

    int[] stock = {
            5, 10, 15, 20,
            25, 10, 8, 6,
            12, 18
    };

    double total = 0;

    public OnlineShoppingSystem() {

        setTitle("Online Shopping System");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Color bgColor = new Color(25, 25, 35);
        Color panelColor = new Color(40, 40, 55);
        Color accent = new Color(0, 173, 181);

        JPanel header = new JPanel();
        header.setBackground(accent);

        title = new JLabel("ONLINE SHOPPING SYSTEM");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        header.add(title);

        add(header, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(bgColor);
        mainPanel.setLayout(null);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(panelColor);
        formPanel.setBounds(30, 20, 820, 220);
        formPanel.setBorder(new LineBorder(accent, 2, true));

        JLabel productLabel = new JLabel("Select Product");
        productLabel.setForeground(Color.WHITE);
        productLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        productLabel.setBounds(50, 40, 150, 30);
        formPanel.add(productLabel);

        products = new JComboBox<>(productNames);
        products.setBounds(220, 40, 300, 35);
        products.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        formPanel.add(products);

        quantityLabel = new JLabel("Enter Quantity");
        quantityLabel.setForeground(Color.WHITE);
        quantityLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        quantityLabel.setBounds(50, 95, 150, 30);
        formPanel.add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(220, 95, 300, 35);
        quantityField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        formPanel.add(quantityField);

        stockLabel = new JLabel("Available Stock: " + stock[0]);
        stockLabel.setForeground(accent);
        stockLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        stockLabel.setBounds(550, 40, 200, 35);
        formPanel.add(stockLabel);

        products.addActionListener(e ->
                stockLabel.setText("Available Stock: " +
                        stock[products.getSelectedIndex()])
        );

        addButton = createButton("Add To Cart", accent);
        addButton.setBounds(40, 160, 170, 40);

        billButton = createButton("Generate Bill", accent);
        billButton.setBounds(230, 160, 170, 40);

        recommendationButton = createButton("Recommend", accent);
        recommendationButton.setBounds(420, 160, 170, 40);

        clearButton = createButton("Clear", accent);
        clearButton.setBounds(610, 160, 150, 40);

        formPanel.add(addButton);
        formPanel.add(billButton);
        formPanel.add(recommendationButton);
        formPanel.add(clearButton);

        mainPanel.add(formPanel);

        billArea = new JTextArea();
        billArea.setEditable(false);
        billArea.setBackground(new Color(18, 18, 25));
        billArea.setForeground(Color.WHITE);
        billArea.setFont(new Font("Consolas", Font.PLAIN, 15));
        billArea.setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(billArea);
        scrollPane.setBounds(30, 260, 820, 300);
        scrollPane.setBorder(new TitledBorder(
                new LineBorder(accent, 2),
                "Shopping Cart",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                Color.WHITE));

        mainPanel.add(scrollPane);

        add(mainPanel, BorderLayout.CENTER);

        addButton.addActionListener(this);
        billButton.addActionListener(this);
        recommendationButton.addActionListener(this);

        clearButton.addActionListener(e -> {
            billArea.setText("");
            total = 0;
        });

        setVisible(true);
    }

    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(8, 15, 8, 15));
        return btn;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addButton) {

            int index = products.getSelectedIndex();

            try {
                int quantity = Integer.parseInt(quantityField.getText());

                if (quantity <= 0) {
                    JOptionPane.showMessageDialog(this,
                            "Quantity must be greater than 0");
                    return;
                }

                if (quantity <= stock[index]) {

                    double amount = prices[index] * quantity;
                    total += amount;
                    stock[index] -= quantity;

                    billArea.append(
                            productNames[index] + "   x " +
                            quantity + "   = Rs." +
                            amount + "\n"
                    );

                    stockLabel.setText(
                            "Available Stock: " + stock[index]);

                    quantityField.setText("");

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

            billArea.append("\n");
            billArea.append("====================================\n");
            billArea.append("TOTAL BILL : Rs." + total + "\n");
            billArea.append("====================================\n");
        }

        else if (e.getSource() == recommendationButton) {

            String recommendation;

            if (total >= 100000)
                recommendation = "Recommended Product: Headphones";
            else if (total >= 50000)
                recommendation = "Recommended Product: Mouse";
            else
                recommendation = "Recommended Product: Keyboard";

            JOptionPane.showMessageDialog(this, recommendation);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new OnlineShoppingSystem());
    }
}
