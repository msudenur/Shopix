package shopix;

public class Admin extends User {
    
    public Admin(int id, String name, String email, String username, String password) {
        super(id, name, email, username, password, "ADMIN");
    }
    
    public Admin(String name, String email, String username, String password) {
        super(name, email, username, password, "ADMIN");
    }
}