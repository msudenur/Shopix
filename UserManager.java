package shopix;

	
	import java.util.ArrayList;

	public class UserManager {

	    private ArrayList<User> users;

	
	    public UserManager() {
	        users = new ArrayList<>();
	    }

	    // Kullanıcı kayıt 
	    public boolean register(User user) {

	        if (usernameExists(user.getUsername())) {
	            System.out.println("Bu kullanıcı adı zaten var!");
	            return false;
	        }

	        users.add(user);
	        System.out.println("Kayıt başarılı!");
	        return true;
	    }

	    // Giriş yapma
	    public User login(String username, String password) {

	        for (User u : users) {
	            if (u.getUsername().equals(username) &&
	                u.getPassword().equals(password)) {

	                return u; 
	            }
	        }

	        return null; 
	    }

	    // Kullanıcı adı var mı kontrolü
	    public boolean usernameExists(String username) {

	        for (User u : users) {
	            if (u.getUsername().equals(username)) {
	                return true;
	            }
	        }

	        return false;
	    }

	
	    public ArrayList<User> getUsers() {
	        return users;
	    }
	}
	

}
