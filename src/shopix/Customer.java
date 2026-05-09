package shopix;

public class Customer extends User {
    
    public Customer(int id, String name, String email, String username, String password) {
        super(id, name, email, username, password, "CUSTOMER");
    }

    public Customer(String name, String email, String username, String password) {
        super(name, email, username, password, "CUSTOMER");
    }
}