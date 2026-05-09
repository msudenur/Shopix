package shopix;
import java.util.ArrayList;
import java.util.List;

public class Order {
    
  
    private int orderId;
    private List<CartItem> items;  
    private double totalPrice;
    private String status; 
 
    public Order() {
        this.items = new ArrayList<>();
        this.status = "CREATED"; 
    }
    
    // Sepetten sipariş oluşturma
    public void createFromCart (Cart cart, ProductManager manager) {
        if (cart.getItems().isEmpty()) {// Sepet boşsa sipariş oluşturma işlemini iptal etme
            System.out.println("Hata: Sepet boş!");
            return;
        }
     // Sepetteki öğelerin bir kopyasını alma
        this.items = List.copyOf(cart.getItems());
        this.totalPrice = cart.getTotalPrice();
        this.status = "CREATED";
        
        // Sepetteki her bir ürün için stok miktarını düşürür
        for (CartItem item : items) {
            manager.reduceStock(
                item.getProduct().getProductId(),
                item.getQuantity());
        }
        
     // Sipariş tamamlandıktan sonra sepetin içeriğini temizle
        cart.clear();
    }
    
    // Ürün durumunu güncelleme
    public void updateStatus(String status) {
        this.status = status;
    }
    // --- Getter ve Setter Metotları ---
    public double getTotalPrice() {
        return totalPrice;
    }
    
    public String getStatus() {
        return status;
    }
    
    public int getOrderId() {
        return orderId;
    }

    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}