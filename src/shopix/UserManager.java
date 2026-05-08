package shopix;

import java.util.ArrayList;

public class UserManager {

    private IDataBase dbManager;

    public UserManager() {
        // Nesne oluşturulduğunda veri tabanı yöneticisini başlatılır

        this.dbManager = new SqliteDatabaseManager();
    }

    public boolean register(User user) {
        // 1. Kullanıcı adı kontrolü (Veri tabanından kontrol ediliyor)
        if (usernameExists(user.getUsername())) {
            System.out.println("Bu kullanıcı adı zaten var!");
            return false;
        }

 
        dbManager.kaydet(user, "users");
        
        System.out.println("Kayıt veri tabanına başarıyla eklendi!");
        return true;
    }

    public User login(String username, String password) {
 
        
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try (java.sql.Connection conn = java.sql.DriverManager.getConnection("jdbc:sqlite:shopix.db");
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            java.sql.ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("name"),     // Veri tabanından çekiliyor
                    rs.getString("email"),    // Veri tabanından çekiliyor
                    rs.getString("username"),
                    rs.getString("password")
                );
            
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Login hatası: " + e.getMessage());
        }
        return null;
    }

    public boolean usernameExists(String username) {
        // Belirli bir kullanıcının var olup olmadığını SQL ile kontrol edilir
        String sql = "SELECT count(*) FROM users WHERE username = ?";
        
        try (java.sql.Connection conn = java.sql.DriverManager.getConnection("jdbc:sqlite:shopix.db");
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            java.sql.ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0; // Eğer count 0'dan büyükse kullanıcı vardır
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Kontrol hatası: " + e.getMessage());
        }
        return false;
    }
    public ArrayList<User> getUsers() {
     
        ArrayList<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (java.sql.Connection conn = java.sql.DriverManager.getConnection("jdbc:sqlite:shopix.db");
             java.sql.Statement stmt = conn.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery(sql)) {
            
        	while (rs.next()) {
        	   
        	    userList.add(new User(
        	        rs.getInt("id"),
        	        rs.getString("name"),
        	        rs.getString("email"),
        	        rs.getString("username"),
        	        rs.getString("password")
        	    ));
        	}
        } catch (java.sql.SQLException e) {
            System.out.println("Liste çekme hatası: " + e.getMessage());
        }
        return userList;
    }
}