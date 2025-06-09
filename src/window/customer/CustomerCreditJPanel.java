package window.customer;

import database.Database;
import database.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomerCreditJPanel {
    private CustomerBarJPanel customerBarJPanel;
    private JPanel customerCreditJPanel;

    private JButton creditButton;
    private JTextField creditAmount;
    private JTextField name;
    private JPasswordField password;

    public CustomerCreditJPanel() {}

    public JPanel createCustomerCreditJPanel(Database db, User user) {

        customerBarJPanel = new CustomerBarJPanel();
        customerBarJPanel.createBarJPanel();

        customerCreditJPanel = new JPanel(new GridBagLayout());
        customerCreditJPanel.setPreferredSize(new Dimension(1000, 600));

        creditAmount = new JTextField(15);
        name =  new JTextField(15);
        password = new JPasswordField(15);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.BOTH;

        // Username and Password Field
        gbc.gridx = 0; gbc.gridy = 0;
        customerCreditJPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        customerCreditJPanel.add(name, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        customerCreditJPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        customerCreditJPanel.add(password, gbc);

        // Amount Field
        gbc.gridx = 0; gbc.gridy = 3;
        customerCreditJPanel.add(new JLabel("Amount:"), gbc);
        gbc.gridx = 1;
        customerCreditJPanel.add(creditAmount, gbc);

        // Credit Button
        gbc.gridx = 1; gbc.gridy = 4;
        creditButton = new JButton("Credit");
        creditButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        customerCreditJPanel.add(creditButton, gbc);

        JPanel creditPanel = new JPanel(new BorderLayout());

        creditPanel.add(customerCreditJPanel, BorderLayout.CENTER);
        creditPanel.add(customerBarJPanel.getSidebar(), BorderLayout.WEST);
        creditPanel.add(customerBarJPanel.getTopBar(), BorderLayout.NORTH);

        return creditPanel;

    }

    public void addCreditListener(ActionListener actionListener) {
        creditButton.addActionListener(actionListener);
    }

    public JTextField getUserField() {
        return name;
    }
    public JPasswordField getPassField() {
        return password;
    }
    public JTextField getCreditAmount() {
        return creditAmount;
    }

    public CustomerBarJPanel getBarJPanel() {return customerBarJPanel;}

}
