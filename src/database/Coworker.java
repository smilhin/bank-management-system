package database;

public class Coworker implements User {

    private static int id = 0;
    private final Role role;
    private final int userId;
    private final String name;
    private final String password;

    public Coworker(String name, String password) {
        this.userId = id;
        id++;
        this.name = name;
        this.password = password;
        this.role = Role.COWORKER;
    }

    public boolean equals(Coworker cuoworker) {
        return this.name.equals(cuoworker.name) && this.password.equals(cuoworker.password);
    }

    @Override
    public int getId() {return userId;}

    @Override
    public Role getRole() {
        return this.role;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Coworker [id=" + userId + ", name=" + name + "]";
    }
}
