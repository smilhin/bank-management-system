package window.customer;

import database.Customer;
import database.Database;
import database.Transaction;
import database.User;
import window.coworker.CoworkerBarJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Objects;

public class CustomerJPanel {
    private CustomerBarJPanel customerBarJPanel;
    private Customer currentCustomer;

    private JButton transferButton;
    private JButton confirmTransferButton;
    private JLabel balanceLabel;

    private JPopupMenu transferPopup;
    private JTextField toField;
    private JTextField amountField;
    private JPasswordField passField;


    private JFrame frame;
    private Database db;

    public CustomerJPanel(Customer customer, JFrame frame, Database db) {
        this.currentCustomer = customer;
        this.frame = frame;
        this.db = db;
    }

    public JPanel createBankPanel() {

        // ---------- Bar ----------
        customerBarJPanel = new CustomerBarJPanel();
        customerBarJPanel.createBarJPanel();


        // ---------- Main Dashboard ----------
        JPanel dashboard = new JPanel();
        dashboard.setLayout(new GridBagLayout());

        dashboard.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();

        // Title Label
        JLabel titleLabel = new JLabel("Account Balance");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        dashboard.add(titleLabel, gbc);

        // Balance Amount
        balanceLabel = new JLabel(String.format("%.2f €", currentCustomer.getBalance()));
        balanceLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        balanceLabel.setForeground(new Color(0x4CAF50)); // Green
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        dashboard.add(balanceLabel, gbc);

        // Transfer Button
        transferButton = new JButton("Transfer");
        transferButton.setPreferredSize(new Dimension(140, 40));
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        dashboard.add(transferButton, gbc);

        //Popup menu for transfers
        transferPopup = new JPopupMenu();
        JPanel popupPanel = new JPanel(new GridBagLayout());
        popupPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        popupPanel.add(new JLabel("To:"), gbc);
        gbc.gridx = 1;
        toField = new JTextField(15);
        popupPanel.add(toField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        popupPanel.add(new JLabel("Amount (€):"), gbc);
        gbc.gridx = 1;
        amountField = new JTextField();
        popupPanel.add(amountField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        popupPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passField = new JPasswordField();
        popupPanel.add(passField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        confirmTransferButton = new JButton("Transfer");
        popupPanel.add(confirmTransferButton, gbc);

        transferPopup.setLayout(new BorderLayout());
        transferPopup.add(popupPanel, BorderLayout.CENTER);

        JPanel dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.add(customerBarJPanel.getSidebar(), BorderLayout.WEST);
        dashboardPanel.add(dashboard, BorderLayout.CENTER);
        dashboardPanel.add(customerBarJPanel.getTopBar(), BorderLayout.NORTH);

        return dashboardPanel;
    }

    public void addTransferListener(ActionListener actionListener) {
        transferButton.addActionListener(actionListener);
    }

    public void addConfirmTransferListener(ActionListener actionListener) {
        confirmTransferButton.addActionListener(actionListener);
    }

    public void refreshAmount() {
        balanceLabel.setText(String.format("%.2f €", currentCustomer.getBalance()));
    }

    //Getters
    public CustomerBarJPanel getBarJPanel() {
        return customerBarJPanel;
    }

    public JButton getTransferButton() {
        return transferButton;
    }

    public JPopupMenu getTransferPopup() {
        return transferPopup;
    }

    public JTextField getToField() {
        return toField;
    }

    public JTextField getAmountField() {
        return amountField;
    }

    public JPasswordField getPassField() {
        return passField;
    }
}

