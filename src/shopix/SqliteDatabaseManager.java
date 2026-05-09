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
        // Kullanıcı tablosu (zaten AUTOINCREMENT özelliğine sahipti)
        String userTable = "CREATE TABLE IF NOT EXISTS users ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " name TEXT,"
                + " email TEXT,"
                + " username TEXT NOT NULL,"
                + " password TEXT NOT NULL,"
                + " role TEXT);"; 
        
        // Ürün tablosu - productId artık AUTOINCREMENT
        String productTable = "CREATE TABLE IF NOT EXISTS products ("
                            + " productId INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + " productName TEXT NOT NULL,"
                            + " price REAL,"
                            + " stockQuantity INTEGER);";
        
        // Sipariş tablosu - Yeni eklendi ve orderId AUTOINCREMENT
        String orderTable = "CREATE TABLE IF NOT EXISTS orders ("
                          + " orderId INTEGER PRIMARY KEY AUTOINCREMENT,"
                          + " totalPrice REAL,"
                          + " status TEXT);";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(userTable);
            stmt.execute(productTable);
            stmt.execute(orderTable);
        } catch (SQLException e) {
            System.out.println("Tablo hazırlama hatası: " + e.getMessage());
        }
    }

    @Override
    public void kaydet(Object veri, String tabloAdi) {
        if (veri instanceof User && tabloAdi.equalsIgnoreCase("users")) {
            User user = (User) veri;
            String sql = "INSERT INTO users (name, email, username, password, role) VALUES(?, ?, ?, ?, ?)";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, user.getName());
                pstmt.setString(2, user.getEmail());
                pstmt.setString(3, user.getUsername());
                pstmt.setString(4, user.getPassword());
                pstmt.setString(5, user.getRole()); 
                pstmt.executeUpdate();
                System.out.println("Sistem: Kullanıcı başarıyla kaydedildi.");
            } catch (SQLException e) {
                System.out.println("Kullanıcı kayıt hatası: " + e.getMessage());
            }
        } 
        else if (veri instanceof Product && tabloAdi.equalsIgnoreCase("products")) {
            Product p = (Product) veri;
            // ID kısmını null (veya sorgudan çıkararak) bırakıyoruz ki SQLite otomatik atasın
            String sql = "INSERT INTO products (productName, price, stockQuantity) VALUES(?, ?, ?)";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, p.getProductName());
                pstmt.setDouble(2, p.getPrice());
                pstmt.setInt(3, p.getStockQuantity());
                pstmt.executeUpdate();
                System.out.println("Sistem: Ürün veritabanına otomatik ID ile kaydedildi.");
            } catch (SQLException e) {
                System.out.println("Ürün kayıt hatası: " + e.getMessage());
            }
        }
        else if (veri instanceof Order && tabloAdi.equalsIgnoreCase("orders")) {
            Order o = (Order) veri;
            String sql = "INSERT INTO orders (totalPrice, status) VALUES(?, ?)";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setDouble(1, o.getTotalPrice());
                pstmt.setString(2, o.getStatus());
                pstmt.executeUpdate();
                System.out.println("Sistem: Sipariş başarıyla kaydedildi.");
            } catch (SQLException e) {
                System.out.println("Sipariş kayıt hatası: " + e.getMessage());
            }
        }
    }

    @Override
    public Object veriCek(String tabloAdi) { return null; }
}