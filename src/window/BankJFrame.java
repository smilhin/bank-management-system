package window;

import database.*;
import window.coworker.CoworkerAccountsJPanel;
import window.coworker.CoworkerBarJPanel;
import window.coworker.CoworkerJPanel;
import window.coworker.CoworkerTransactionsJPanel;
import window.customer.CustomerBarJPanel;
import window.customer.CustomerJPanel;
import window.customer.CustomerTransactionsJPanel;
import window.customer.CustomerCreditJPanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

/**
 * This class represents the main application window for a simple Bank Management System
 * built using Java Swing. It manages multiple views (login, registration, dashboard,
 * and account views) using a CardLayout to switch between panels dynamically.
 * -----------------------------------------------------------------------------------
 * Key Responsibilities:
 * - Initializes an in-memory database (non-persistent) with a few sample users.
 * - Sets up the main JFrame and adds panels for different parts of the application.
 * - Handles transitions between panels (e.g., login to dashboard, register to log in).
 * - Registers event listeners to respond to user actions like logging in, registering,
 *   switching views via a sidebar, and logging out.
 */

public class BankJFrame {

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardContainer;

    private LoginJPanel loginPanel;
    private RegisterJPanel registerPanel;
    private CoworkerJPanel dashboardCoworkerPanel;
    private CustomerJPanel dashboardCustomerPanel;
    private CoworkerAccountsJPanel accountsPanel;
    private CoworkerTransactionsJPanel transactionsCoworkerPanel;
    private CustomerTransactionsJPanel transactionsCustomerPanel;
    private CustomerCreditJPanel creditCustomerPanel;

    Database database;

    public BankJFrame() {
        init();
    }

    public void init() {

        /*
         Database
         No server is connected to this program,
         so any data in the database will be lost after the program terminates
        */
        database = Database.getInstance();
        database.createUser(new Coworker("admin", "root"));
        database.createUser(new Coworker("Dan", "123"));

        database.createUser(new Customer("Danissimo", "1233"));
        database.createUser(new Customer("Arnold", "1959"));
        database.createUser(new Customer("Josef", "000"));
        database.createUser(new Customer("Vlad", "344"));
        ((Customer) database.getUser("Danissimo")).setBalance(1000.123);


        //---------- Frames ----------
        frame = new JFrame();
        cardLayout = new CardLayout();
        cardContainer = new JPanel(cardLayout);

        this.frame.setTitle("Bank Management System");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(1000, 600);
        this.frame.setLocationRelativeTo(null); // Center on screen
        this.frame.setLayout(new BorderLayout());

        //Login Panel (layout)
        loginPanel = new LoginJPanel();
        cardContainer.add(loginPanel.createLoginPanel(), "login");

        //Register panel (layout)
        registerPanel = new RegisterJPanel();
        cardContainer.add(registerPanel.createRegisterPanel(), "register");

        //Account Panel (layout)
        accountsPanel = new CoworkerAccountsJPanel();
        cardContainer.add(accountsPanel.createAccountsPanel(database), "accounts");

        //Transactions Coworker Panel (layout)
        transactionsCoworkerPanel = new CoworkerTransactionsJPanel();
        cardContainer.add(transactionsCoworkerPanel.createCoworkerTransactionsJPanel(database), "transactionsCoworker");

        this.frame.add(cardContainer);

        //Listeners

        loginListener();
        registerListener();


        this.frame.setVisible(true);
    }

    /*
     Event Listener Setup

     This section defines the listeners for user interactions
     within the application. It includes logic for:

    - Login Panel:
      * Validates user credentials against the in-memory database
      * Navigates to dashboard on success or shows an error
      * Allows navigation to the registration panel

    - Register Panel:
      * Validates registration fields (empty fields, password match, duplicate usernames)
      * Creates a new user if all checks pass
      * Navigates back to the login panel after successful registration

    - Dashboard & Accounts Panels:
      * Sidebar navigation for switching between panels
      * Includes logout functionality that returns the user to the login screen
    */

    private void loginListener() {
        /*
        ---------- Login Listeners ----------
        Login event listener
        */
        loginPanel.addLoginListener(e -> {
            String userText = loginPanel.getUserField().getText();
            String passText = String.valueOf(loginPanel.getPassField().getPassword());
            User user = database.getUser(userText);

            System.out.println("Logging in: " + userText);
            if (database.containsUser(userText, passText)) {
                loginPanel.getUserField().setText("");
                loginPanel.getPassField().setText("");

                // After successful login, determine the user role and set up the appropriate dashboard panel
                if (user.getRole().equals(User.Role.COWORKER)) {
                    dashboardCoworkerPanel = new CoworkerJPanel();
                    cardContainer.add(dashboardCoworkerPanel.createBankPanel(), "dashboardCoworker");

                    dashboardCoworkerListener(database);
                    accountsCoworkerListener();
                    transactionsCoworkerListener();

                   cardLayout.show(cardContainer, "dashboardCoworker");
                }
                else if (user.getRole().equals(User.Role.CUSTOMER)) {
                    //Dashboard Customer Panel (layout)
                    dashboardCustomerPanel = new CustomerJPanel((Customer) user, this.frame, this.database);
                    cardContainer.add(dashboardCustomerPanel.createBankPanel(), "dashboardCustomer");

                    //Transactions Customer Panel (layout)
                    transactionsCustomerPanel = new CustomerTransactionsJPanel();
                    cardContainer.add(transactionsCustomerPanel.createCustomerTransactionsJPanel(database, user), "transactionsCustomer");

                    //Credit Customer Panel (layout)
                    creditCustomerPanel = new CustomerCreditJPanel();
                    cardContainer.add(creditCustomerPanel.createCustomerCreditJPanel(database, user), "creditCustomer");

                    dashboardCustomerListener();
                    transactionsCustomerListener(database, (Customer) user);
                    creditCustomerListener(database, (Customer) user);

                    cardLayout.show(cardContainer, "dashboardCustomer");
                }
            }
            else {
                JOptionPane.showMessageDialog(this.frame, "Wrong username or password");
            }
        });

        //Login listener to show register container
        loginPanel.addRegisterListener(e -> {
            cardLayout.show(cardContainer, "register");
        });
    }

    private void registerListener() {
        //---------- Register Listeners ----------
        // Register listener to show login container
        registerPanel.addLoginListener(e -> {
            cardLayout.show(cardContainer, "login");
        });

        //Register listener to register a new user
        registerPanel.addRegisterListener(e -> {
            String username = registerPanel.getUserField().getText().trim();
            String password = String.valueOf(registerPanel.getPassField().getPassword());
            String confirmPassword = String.valueOf(registerPanel.getPassConfirmField().getPassword());
            User.Role role = registerPanel.getRoleComboBox();

            if(username.isEmpty()) {
                JOptionPane.showMessageDialog(this.frame, "Enter Username");
            }
            else if(password.isEmpty()) {
                JOptionPane.showMessageDialog(this.frame, "Enter Password");
            }
            else if(confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this.frame, "Confirm Password");
            }
            else if(!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this.frame, "Passwords do not match");
            }
            else if(database.containsUser(username) || !registerPanel.isValidUsername(username)) {
                JOptionPane .showMessageDialog(this.frame, "Invalid Username");
            }
            else {
                if (role == User.Role.CUSTOMER) {
                    database.createUser(new Customer(username, password));
                }
                else {
                    database.createUser(new Coworker(username, password));
                }
                registerPanel.getUserField().setText("");
                registerPanel.getPassField().setText("");
                registerPanel.getPassConfirmField().setText("");
                System.out.println(database.toString());
                cardLayout.show(cardContainer, "login");
                JOptionPane.showMessageDialog(this.frame, "Account Created! Now log in to this account!");
            }
        });
    }

    //---------- Dashboard Coworker Listeners ----------
    private void dashboardCoworkerListener(Database db) {
        sidebarCoworkerListener(dashboardCoworkerPanel.getBarJPanel());
        dashboardCoworkerPanel.addSearchListener(e ->  {
            String name = dashboardCoworkerPanel.getNameField().getText();
            String idString = dashboardCoworkerPanel.getIdField().getText();
            User.Role role = dashboardCoworkerPanel.getRole();

            if (name.isEmpty() && idString.isEmpty()) {
                JOptionPane.showMessageDialog(this.frame, "Please Enter Name or ID");
            }
            else {
                Integer id = null;
                if (!idString.isEmpty()) {
                    try {
                        id = Integer.parseInt(idString);
                        if (id < 0) {
                            JOptionPane.showMessageDialog(this.frame, "Invalid ID");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this.frame, "ID must be a number");
                    }
                }

                boolean found = false;
                StringBuilder resultBuilder = new StringBuilder();

                if (!name.isEmpty() && db.containsUser(name)) {
                    if(db.getUser(name).getRole().equals(role)) {
                        resultBuilder.append(db.getUser(name).toString());
                        found = true;
                    }
                }

                if (id != null && role.equals(User.Role.CUSTOMER) && db.containsCustomer(id)) {
                        String userStr = db.getCustomerById(id).toString();
                        if (!resultBuilder.toString().equals(userStr)) {
                            if (found) resultBuilder.append("\n");
                            resultBuilder.append(userStr);
                        }
                        found = true;
                }
                else if(id != null && role.equals(User.Role.COWORKER) && db.containsCoworker(id)) {
                    String userStr = db.getCoworkerById(id).toString();
                    if (!resultBuilder.toString().equals(userStr)) {
                        if (found) resultBuilder.append("\n");
                        resultBuilder.append(userStr);
                    }
                    found = true;
                }

                if (found) {
                    dashboardCoworkerPanel.refreshResults(resultBuilder.toString());
                } else {
                    dashboardCoworkerPanel.refreshResults("No users found!");
                }
            }

        });
    }

    //---------- Accounts Listeners ----------
    private void accountsCoworkerListener() {
        sidebarCoworkerListener(accountsPanel.getBarJPanel());
    }

    private void transactionsCoworkerListener() {
        sidebarCoworkerListener(transactionsCoworkerPanel.getBarJPanel());
    }

    private void transactionsCustomerListener(Database db, Customer sender) {
        sidebarCustomerListener(transactionsCustomerPanel.getBarJPanel());

        //Listener for confirming a transfer
        dashboardCustomerPanel.addConfirmTransferListener(e -> {

            String toUser = dashboardCustomerPanel.getToField().getText();
            String amount = dashboardCustomerPanel.getAmountField().getText();
            double amountVal = -1;
            String password = String.valueOf(dashboardCustomerPanel.getPassField().getPassword());
            Customer recipient = (!(db.getUser(toUser) == null) && db.getUser(toUser).getRole().equals(User.Role.CUSTOMER))
                    ? (Customer) db.getUser(toUser) : null;

            try {
                amountVal = Double.parseDouble(amount);
            }
            catch (NumberFormatException ex) {
                System.out.println(ex.getMessage());
            }

            if(toUser.isEmpty()
                    || amount.isEmpty()
                    ||password.isEmpty()) {
                JOptionPane.showMessageDialog(this.frame, "Please fill in the blanks");
            }
            else if(recipient == null) {
                JOptionPane.showMessageDialog(this.frame, "User doesn't exist");
            }
            else if(sender.getUsername().equals(toUser)) {
                JOptionPane.showMessageDialog(this.frame, "Invalid username");
            }
            else if(sender.getBalance() < amountVal) {
                JOptionPane.showMessageDialog(this.frame, "Insufficient balance");
            }
            else if(amountVal < 0) {
                JOptionPane.showMessageDialog(this.frame, "Invalid amount");
            }
            else if(!sender.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(this.frame, "Wrong password");
            }
            else {
                recipient.setBalance(recipient.getBalance() + amountVal);
                sender.setBalance(sender.getBalance() - amountVal);
                dashboardCustomerPanel.refreshAmount();
                db.addTransaction(
                        new Transaction(sender,
                                recipient,
                                amountVal, LocalDateTime.now()));
                db.addRecipientTransaction(
                        new Transaction(sender,
                                recipient,
                                amountVal, LocalDateTime.now()));

                JOptionPane.showMessageDialog(this.frame, "Successfully transferred!");
            }
        });

        //Listener for transfer popup menu
        dashboardCustomerPanel.addTransferListener(e -> {
            dashboardCustomerPanel.getTransferPopup().show(dashboardCustomerPanel.getTransferButton(), 0, dashboardCustomerPanel.getTransferButton().getHeight());
        });
    }

    private void creditCustomerListener(Database db, Customer customer) {
        sidebarCustomerListener(creditCustomerPanel.getBarJPanel());
        creditCustomerPanel.addCreditListener(e ->  {
            String username = creditCustomerPanel.getUserField().getText().trim();
            String password = String.valueOf(creditCustomerPanel.getPassField().getPassword());
            String amount = creditCustomerPanel.getCreditAmount().getText().trim();
            double amountVal = -1;
            try {
                amountVal = Double.parseDouble(amount);
            }
            catch (NumberFormatException ex) {
                System.out.println(ex.getMessage());
            }


            if(username.isEmpty()
            || password.isEmpty()
            || amount.isEmpty() ) {
                JOptionPane.showMessageDialog(this.frame, "Fill in all the blanks!");
            }
            else if(!customer.getUsername().equals(username) || !customer.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(this.frame, "Wrong username or password");
            }
            else if(amountVal <= 0) {
                JOptionPane.showMessageDialog(this.frame, "Invalid amount");
            }
            else {
                customer.setBalance(customer.getBalance() + amountVal);
                db.addTransaction(new Transaction(customer, customer, amountVal, LocalDateTime.now()));
                JOptionPane.showMessageDialog(this.frame, "Credited Successfully!");
            }

        });
    }

    //---------- Dashboard Customer Listeners ----------
    private void dashboardCustomerListener() {
        sidebarCustomerListener(dashboardCustomerPanel.getBarJPanel());
    }

    //---------- Sidebar Listeners ----------
    private void sidebarCoworkerListener(CoworkerBarJPanel panel) {

        panel.addDashboardListener(e -> {
            cardLayout.show(cardContainer, "dashboardCoworker");
        });

        panel.addAccountsListener(e -> {
            accountsPanel.refreshText(database);
            cardLayout.show(cardContainer, "accounts");
        });

        panel.addTransactionsListener(e -> {
            transactionsCoworkerPanel.refreshText(database);
            cardLayout.show(cardContainer, "transactionsCoworker");
        });

        panel.addLogoutListener(e -> {
            cardLayout.show(cardContainer, "login");
        });
    }

    private void sidebarCustomerListener(CustomerBarJPanel panel) {

        panel.addDashboardListener(e -> {
            cardLayout.show(cardContainer, "dashboardCustomer");
            dashboardCustomerPanel.refreshAmount();
        });

        panel.addTransactionsListener(e -> {
            transactionsCustomerPanel.refreshText(database);
            cardLayout.show(cardContainer, "transactionsCustomer");
        });

        panel.addDepositListener(e -> {
            cardLayout.show(cardContainer, "creditCustomer");
        });

        panel.addLogoutListener(e -> {
            cardLayout.show(cardContainer, "login");
        });
    }

}
