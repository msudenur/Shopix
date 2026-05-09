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
        if (cart.getItems().isEmpty()) {
            System.out.println("Hata: Sepet boş!");
            return;
        }
        
        this.items = new ArrayList<>(cart.getItems());
        this.totalPrice = cart.getTotalPrice();
        this.status = "CREATED";
        
        // Sepetteki her bir ürün için stok miktarını düşürür
        for (CartItem item : items) {
            manager.reduceStock(
                item.getProduct().getProductId(),
                item.getQuantity());
        }
        
       
        cart.clear();
    }
    
    // Ürün durumunu güncelleme
    public void updateStatus(String status) {
        this.status = status;
    }
    
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