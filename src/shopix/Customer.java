package shopix;

import java.io.Serializable;

public class Customer extends User implements Serializable {
     public Customer(int id,String name, String email, String username,String password) {
	 super(id, name, email, username, password);
}
}
