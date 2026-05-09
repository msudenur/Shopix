package shopix;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManager implements IShopOperations {
    private static ProductManager instance;
    private IDataBase dbManager; 
    private static final String DB_URL = "jdbc:sqlite:shopix.db";

    private ProductManager() {
        this.dbManager = new SqliteDatabaseManager();
    }

    public static ProductManager getInstance() {
        if (instance == null) {
            instance = new ProductManager();
        }
        return instance;
    }

    public void addProduct(Product newProduct) {
        if (newProduct == null) return;
        dbManager.kaydet(newProduct, "products");
    }

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Product p = new Product(
                    rs.getInt("productId"),
                    rs.getString("productName"),
                    rs.getDouble("price"),
                    rs.getInt("stockQuantity")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Ürün listeleme hatası: " + e.getMessage());
        }
        return list;
    }

    // METOT: SİL ---
    public void removeProduct(int productId) {
        String sql = "DELETE FROM products WHERE productId = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, productId);
            pstmt.executeUpdate();
            System.out.println("Ürün veri tabanından silindi. ID: " + productId);
            
        } catch (SQLException e) {
            System.out.println("Ürün silme hatası: " + e.getMessage());
        }
    }

    // --- METOT: GÜNCELLE ---
    public void updateProduct(Product product) {
        String sql = "UPDATE products SET productName = ?, price = ?, stockQuantity = ? WHERE productId = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getProductName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setInt(3, product.getStockQuantity());
            pstmt.setInt(4, product.getProductId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Ürün başarıyla güncellendi: " + product.getProductName());
            }

        } catch (SQLException e) {
            System.out.println("Ürün güncelleme hatası: " + e.getMessage());
        }
    }
    
    public void reduceStock(int productId, int quantity) {
        String sql = "UPDATE products SET stockQuantity = stockQuantity - ? WHERE productId = ? AND stockQuantity >= ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, productId);
            pstmt.setInt(3, quantity);
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Stok veri tabanında güncellendi.");
            } else {
                System.out.println("Hata: Stok yetersiz!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}