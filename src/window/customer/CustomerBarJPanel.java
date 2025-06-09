package window.customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomerBarJPanel {
    private JButton dashboardButton;
    private JButton depositButton;
    private JButton transactionsButton;
    private JButton logoutButton;

    private JPanel sidebar;
    private JPanel topBar;

    public CustomerBarJPanel() {}

    public JPanel createBarJPanel() {
        JPanel panel = new JPanel();
        // ---------- Sidebar ----------
        sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(6, 1, 0, 10));
        sidebar.setPreferredSize(new Dimension(180, 0));

        dashboardButton = makeButton("Dashboard", sidebar);
        depositButton = makeButton("Credit", sidebar);
        transactionsButton = makeButton("Transactions", sidebar);
        logoutButton = makeButton( "Logout", sidebar);

        topBar = new JPanel(new BorderLayout());
        topBar.setPreferredSize(new Dimension(0, 60));
        JLabel title = new JLabel("Bank Management System");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        topBar.add(title, BorderLayout.WEST);

        panel.add(sidebar, BorderLayout.WEST);
        panel.add(topBar, BorderLayout.NORTH);
        return panel;
    }

    private JButton makeButton(String label, JPanel panel) {
        JButton btn = new JButton(label);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btn.setBorderPainted(false);
        panel.add(btn);
        return btn;
    }

    public void addDashboardListener(ActionListener listener) {
        dashboardButton.addActionListener(listener);
    }

    public void addDepositListener(ActionListener listener) {
        depositButton.addActionListener(listener);
    }

    public void addTransactionsListener(ActionListener listener) {
        transactionsButton.addActionListener(listener);
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }

    public JPanel getSidebar() {
        return sidebar;
    }
    public JPanel getTopBar() {
        return topBar;
    }
}
