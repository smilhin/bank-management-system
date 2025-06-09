package database;

public interface User {

    enum Role {
        CUSTOMER,
        COWORKER
    }

    int getId();
    Role getRole();
    String getUsername();
    String getPassword();
    String toString();
}
