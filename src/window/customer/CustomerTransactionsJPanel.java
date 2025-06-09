package window.customer;

import database.Database;
import database.User;

import javax.swing.*;
import java.awt.*;

public class CustomerTransactionsJPanel {
    private CustomerBarJPanel customerBarJPanel;
    private JPanel customerTransactionsJPanel;
    private JTextArea transactionsTextArea;

    private User currentUser;

    public CustomerTransactionsJPanel() {}

    public JPanel createCustomerTransactionsJPanel(Database db, User user) {

        this.currentUser = user;

        customerBarJPanel = new CustomerBarJPanel();
        customerBarJPanel.createBarJPanel();

        customerTransactionsJPanel = new JPanel(new BorderLayout());
        customerTransactionsJPanel.setPreferredSize(new Dimension(1000, 600));

        transactionsTextArea = new JTextArea(db.userTransactionsToString(currentUser));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);

        JScrollPane transactionScroll = new JScrollPane(transactionsTextArea);
        transactionScroll.setBorder(BorderFactory.createTitledBorder("Transactions"));

        customerTransactionsJPanel.add(transactionScroll, BorderLayout.CENTER);
        customerTransactionsJPanel.add(customerBarJPanel.getSidebar(), BorderLayout.WEST);
        customerTransactionsJPanel.add(customerBarJPanel.getTopBar(), BorderLayout.NORTH);

        return customerTransactionsJPanel;

    }

    public void refreshText(Database db) {
        transactionsTextArea.setText(db.userTransactionsToString(currentUser));
    }

    public CustomerBarJPanel getBarJPanel() {return customerBarJPanel;}

}
