package shopix;

import java.sql.*;

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
        if (veri instanceof User && tabloAdi.equalsIgnoreCase("users")) {
            User user = (User) veri;
            String sql = "INSERT INTO users (name, email, username, password) VALUES(?, ?, ?, ?)";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, user.getName());
                pstmt.setString(2, user.getEmail());
                pstmt.setString(3, user.getUsername());
                pstmt.setString(4, user.getPassword());
                pstmt.executeUpdate();
                System.out.println("Sistem: Kullanıcı başarıyla '" + tabloAdi + "' tablosuna kaydedildi.");
            } catch (SQLException e) {
                System.out.println("Kullanıcı kayıt hatası: " + e.getMessage());
            }
        } 
        else if (veri instanceof Product && tabloAdi.equalsIgnoreCase("products")) {
            Product p = (Product) veri;
            String sql = "INSERT INTO products (productId, productName, price, stockQuantity) VALUES(?,?,?,?)";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, p.getProductId());
                pstmt.setString(2, p.getProductName());
                pstmt.setDouble(3, p.getPrice());
                pstmt.setInt(4, p.getStockQuantity());
                pstmt.executeUpdate();
                System.out.println("Sistem: Ürün 4 ana etkeniyle kaydedildi.");
            } catch (SQLException e) {
                System.out.println("Ürün kayıt hatası: " + e.getMessage());
            }
        }
    }

    @Override
    public Object veriCek(String tabloAdi) { return null; }
}