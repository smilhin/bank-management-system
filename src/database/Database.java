package database;

import java.util.*;

//Singleton for a database
public final class Database {
    private static Database instance;

    private final HashMap<Integer, User> customers = new HashMap<>();
    private final HashMap<Integer, User> coworkers = new HashMap<>();
    private final ArrayList<Transaction> transactions = new ArrayList<>();
    private final Map<User, ArrayList<Transaction>> userTransactions = new HashMap<>();

    private Database() {}

    private static class Holder {
        private static final Database INSTANCE = new Database();
    }

    public static synchronized Database getInstance() {
        return Holder.INSTANCE;
    }

    public void createUser(User user) {
        if (user.getRole() == User.Role.CUSTOMER && !customers.containsKey(user.getId())) {
            customers.put(user.getId(), user);
        }
        else if (user.getRole() == User.Role.COWORKER && !customers.containsKey(user.getId())) {
            coworkers.put(user.getId(), user);
        }
        else {
            throw new IllegalArgumentException("User already exists!");
        }
    }

    public boolean containsUser(String name) {
        for (User customer : customers.values()) {
            if (customer.getUsername().equals(name)) {
                return true;
            }
        }
        for (User coworker : coworkers.values()) {
            if (coworker.getUsername().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsUser(String name, String password) {
        for (User customer : customers.values()) {
            if (customer.getUsername().equals(name) &&  customer.getPassword().equals(password)) {
                return true;
            }
        }
        for (User coworker : coworkers.values()) {
            if (coworker.getUsername().equals(name) &&   coworker.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsCustomer(int id) {
        for(User customer : customers.values()) {
            if (customer.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean containsCoworker(int id) {
        for(User coworker : coworkers.values()) {
            if (coworker.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public User getUser(String name) {
        for (User customer : customers.values()) {
            if (customer.getUsername().equals(name)) {
                return customer;
            }
        }
        for (User coworker : coworkers.values()) {
            if (coworker.getUsername().equals(name)) {
                return coworker;
            }
        }
        return null;
    }

    public User getCustomerById(int id) {
        for (User customer : customers.values()) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    public User getCoworkerById(int id) {
        for (User coworker : coworkers.values()) {
            if (coworker.getId() == id) {
                return coworker;
            }
        }
        return null;
    }

    public void addTransaction(Transaction transaction) {
        User sender = transaction.sender();
        transactions.add(transaction);
        userTransactions.computeIfAbsent(sender, k -> new ArrayList<>()).add(transaction);
    }

    public void addRecipientTransaction(Transaction transaction) {
        User recipient = transaction.recipient();
        userTransactions.computeIfAbsent(recipient, k -> new ArrayList<>()).add(transaction);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Customers: " + "\n");
        for(User customer : customers.values()) {
            str.append(customer.toString());
            str.append("\n");
        }
        str.append("Coworkers: " + "\n");
        for(User coworker : coworkers.values()) {
            str.append(coworker.toString());
            str.append("\n");
        }

        return str.toString();
    }

    public String coworkersToString() {
        StringBuilder str = new StringBuilder();
        for(User coworker : coworkers.values()) {
            str.append(coworker.toString());
            str.append("\n");
        }
        return str.toString();
    }
    public String customersToString() {
        StringBuilder str = new StringBuilder();
        for(User customer : customers.values()) {
            str.append(customer.toString());
            str.append("\n");
        }
        return str.toString();
    }

    public String transactionsToString() {
        StringBuilder str = new StringBuilder();
        for(int i = transactions.size() - 1; i >= 0; i--) {
            str.append(transactions.get(i).toString());
            str.append("\n");
        }
        return  str.toString();
    }

    public String userTransactionsToString(User currentUser) {
        StringBuilder str = new StringBuilder();

        List<Transaction> transactions = userTransactions.get(currentUser);

        if (transactions != null && !transactions.isEmpty()) {
            for (int i = transactions.size() - 1; i >= 0; i--) {
                str.append(transactions.get(i).toString());
                str.append("\n");
            }
        }
        else {
            str.append("No transactions found!");
        }
        return str.toString();

    }

}
