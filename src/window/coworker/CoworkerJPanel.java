package window.coworker;

import database.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CoworkerJPanel {
    private CoworkerBarJPanel coworkerBarJPanel;

    private JTextField nameField;
    private JTextField idField;
    private JComboBox<User.Role> roleComboBox;
    private JButton searchButton;
    private JTextArea results;

    public CoworkerJPanel() {}

    public JPanel createBankPanel() {
        // ---------- Sidebar ----------
        coworkerBarJPanel = new CoworkerBarJPanel();
        coworkerBarJPanel.createBarJPanel();

        // ---------- Main Dashboard ----------
        JPanel dashboard = new JPanel();
        dashboard.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username and Password Field
        gbc.gridx = 1; gbc.gridy = 0;
        JLabel findUser = new JLabel("Find a User");
        findUser.setFont(new Font("SansSerif", Font.BOLD, 20));
        dashboard.add(findUser, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        dashboard.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(15);
        dashboard.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        dashboard.add(new JLabel("Id:"), gbc);
        gbc.gridx = 1;
        idField = new JTextField(15);
        dashboard.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        dashboard.add(new JLabel("Role:"), gbc);
        gbc.gridx = 1;
        roleComboBox = new JComboBox(new User.Role[]{User.Role.CUSTOMER, User.Role.COWORKER});
        dashboard.add(roleComboBox, gbc);

        gbc.gridx = 1; gbc.gridy = 4;
        searchButton = new JButton("Find");
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        dashboard.add(searchButton, gbc);

        gbc.gridy = 5;
        results = new JTextArea();
        results.setEditable(false);
        results.setFont(new Font("SansSerif", Font.BOLD, 14));
        dashboard.add(results, gbc);

        JPanel dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.add(coworkerBarJPanel.getSidebar(), BorderLayout.WEST);
        dashboardPanel.add(dashboard, BorderLayout.CENTER);
        dashboardPanel.add(coworkerBarJPanel.getTopBar(), BorderLayout.NORTH);

        return dashboardPanel;
    }

    public void addSearchListener(ActionListener actionListener) {
        searchButton.addActionListener(actionListener);
    }

    public void refreshResults(String result) {
        results.setText(result);
    }

    public CoworkerBarJPanel getBarJPanel() {
        return coworkerBarJPanel;
    }

    public JTextField getNameField() {
        return nameField;
    }
    public JTextField getIdField() {
        return idField;
    }

    public User.Role getRole() {
        return (User.Role) roleComboBox.getSelectedItem();
    }

    public JTextArea getResults() {
        return results;
    }

}
