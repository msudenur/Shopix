package shopix;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteDatabaseManager implements IDataBase {
    
    private static final String URL = "jdbc:sqlite:shopix.db";

    public SqliteDatabaseManager() {
        tablolariHazirla();
    }

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Bağlantı hatası: " + e.getMessage());
        }
        return conn;
    }

    private void tablolariHazirla() {
        // users ve products tablolarını arayüzdeki 4 etkeni de kapsayacak şekilde oluşturuyoruz
    	// SqliteDatabaseManager.java içindeki tablolariHazirla metodunda:
    	String userTable = "CREATE TABLE IF NOT EXISTS users ("
    	                 + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
    	                 + " name TEXT,"
    	                 + " email TEXT,"
    	                 + " username TEXT NOT NULL,"
    	                 + " password TEXT NOT NULL);";
        
        String productTable = "CREATE TABLE IF NOT EXISTS products ("
                            + " productId INTEGER PRIMARY KEY,"
                            + " productName TEXT NOT NULL,"
                            + " price REAL,"
                            + " stockQuantity INTEGER);";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(userTable);
            stmt.execute(productTable);
        } catch (SQLException e) {
            System.out.println("Tablo hazırlama hatası: " + e.getMessage());
        }
    }

    @Override
    public void kaydet(Object veri, String tabloAdi) {
        // 1. Kullanıcı Kayıt İşlemi
        if (veri instanceof User && tabloAdi.equalsIgnoreCase("users")) {
            User user = (User) veri;
            String sql = "INSERT INTO users (username, password) VALUES(?, ?)";

            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, user.getUsername());
                pstmt.setString(2, user.getPassword());
                pstmt.executeUpdate();
                System.out.println("Sistem: Kullanıcı başarıyla '" + tabloAdi + "' tablosuna kaydedildi.");
            } catch (SQLException e) {
                System.out.println("Kullanıcı kayıt hatası: " + e.getMessage());
            }
        } 
        // 2. Ürün ve Stok Kayıt İşlemi (4 Temel Etken)
        else if (veri instanceof Product && tabloAdi.equalsIgnoreCase("products")) {
            Product p = (Product) veri;
            // Arayüzdeki ve Product sınıfındaki 4 etkenin tamamını SQL sorgusuna ekliyoruz
            String sql = "INSERT INTO products (productId, productName, price, stockQuantity) VALUES(?,?,?,?)";

            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setInt(1, p.getProductId());    // 1. Etken: ID
                pstmt.setString(2, p.getProductName()); // 2. Etken: İsim
                pstmt.setDouble(3, p.getPrice());       // 3. Etken: Fiyat
                pstmt.setInt(4, p.getStockQuantity()); // 4. Etken: Stok Adedi
                
                pstmt.executeUpdate();
                System.out.println("Sistem: Ürün 4 ana etkeniyle birlikte '" + tabloAdi + "' tablosuna kaydedildi.");
            } catch (SQLException e) {
                System.out.println("Ürün kayıt hatası (ID çakışması olabilir): " + e.getMessage());
            }
        }
    }

    @Override
    public Object veriCek(String tabloAdi) {
        // İhtiyaç duyduğunda SELECT sorguları için burayı kullanabilirsin
        return null;
    }
}