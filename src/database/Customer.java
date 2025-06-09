package database;

public class Customer implements User {
    private static int id = 0;
    private final Role role;
    private final int userId;
    private final String name;
    private final String password;
    private double balance;

    public Customer(String name, String password) {
        userId = Customer.id;
        id++;
        this.name = name;
        this.password = password;
        this.balance = 0;
        this.role = Role.CUSTOMER;
    }

    public boolean equals(Customer customer) {
        return this.name.equals(customer.name) && this.password.equals(customer.password);
    }

    //Getters
    public int getId() {return userId;}

    @Override
    public Role getRole() {
        return this.role;
    }

    @Override
    public String getUsername() {
        return name;
    }

    public String getPassword() {return password;}
    public double getBalance() {return balance;}

    //Balance Setter
    public void setBalance(double balance) {this.balance = Math.round(balance * 100.0) / 100.0;}

    public String toString() {
        return "Customer [id=" + userId + ", name=" + name + ", balance=" + balance + "]";
    }
}
