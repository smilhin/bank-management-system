package window;

import database.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegisterJPanel {

    private JTextField userField;
    private JPasswordField passField;
    private JPasswordField passConfirmField;
    private JButton registerButton;
    private JButton loginButton;
    private JComboBox<User.Role> roleComboBox;

    public RegisterJPanel() {}

    public JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");
        JLabel passConfirmLabel = new JLabel("Confirm Password:");
        JLabel  roleLabel = new JLabel("Role:");
        userField = new JTextField(15);
        passField = new JPasswordField(15);
        passConfirmField = new JPasswordField(15);
        roleComboBox = new JComboBox(new User.Role[]{User.Role.CUSTOMER, User.Role.COWORKER});
        registerButton = new JButton("Register");
        loginButton = new JButton("Login");

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(userLabel, gbc);
        gbc.gridx = 1;
        panel.add(userField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(passLabel, gbc);
        gbc.gridx = 1;
        panel.add(passField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(passConfirmLabel, gbc);
        gbc.gridx = 1;
        panel.add(passConfirmField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(roleLabel, gbc);
        gbc.gridx = 1;
        panel.add(roleComboBox, gbc);

        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(registerButton, gbc);

        gbc.gridx = 1; gbc.gridy = 5;
        panel.add(loginButton, gbc);


        return panel;

    }

    public void addRegisterListener(ActionListener actionListener) {
        registerButton.addActionListener(actionListener);
    }
    public void addLoginListener(ActionListener actionListener) {
        loginButton.addActionListener(actionListener);
    }

    public boolean isValidUsername(String username) {
        // Starts with a letter, followed by letters, digits, underscores allowed
        return username.matches("^[A-Za-z][A-Za-z0-9_]*$");
    }

    //Getters
    public JTextField getUserField() {return userField;}
    public JPasswordField getPassField() {return passField;}
    public JPasswordField getPassConfirmField() {return passConfirmField;}
    public User.Role getRoleComboBox() {return (User.Role) roleComboBox.getSelectedItem();}
    public JButton getRegisterButton() {return registerButton;}
    public JButton getLoginButton() {return loginButton;}

}
