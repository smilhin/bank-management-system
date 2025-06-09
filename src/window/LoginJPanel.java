package window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginJPanel {

    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginJPanel() {}

    public JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");
        userField = new JTextField(15);
        passField = new JPasswordField(15);
        loginButton = new JButton("Login");
        registerButton = new JButton("No account? Register");

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(userLabel, gbc);
        gbc.gridx = 1;
        panel.add(userField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(passLabel, gbc);
        gbc.gridx = 1;
        panel.add(passField, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(loginButton, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(registerButton, gbc);

        return panel;
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void addRegisterListener(ActionListener listener) {
        registerButton.addActionListener(listener);
    }

    public JTextField getUserField() {return userField;}
    public JPasswordField getPassField() {return passField;}
    public JButton getRegisterButton() {return registerButton;}
    public JButton getLoginButton() {return loginButton;}
}
