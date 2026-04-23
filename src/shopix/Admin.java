package shopix;

import java.io.Serializable;

public class Admin extends User implements Serializable {
	public Admin(int id,String name, String email, String username,String password) {
		super(id, name, email, username, password);
	}
		
	}


