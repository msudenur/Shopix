package shopix;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * Order sınıfı, tamamlanmış bir alışveriş işlemini temsil eder.
 * Sepetteki ürünleri dondurur, stoktan düşer ve bir sipariş numarası atar.
 */

public class Order implements Serializable {
	
	private static int counter = 1; // Tüm siparişler arasında paylaşılan ve her yeni siparişte artan sayaç
	private int orderId;
	private List<CartItem> items;  // Sipariş edilen ürünlerin listesi
	private double totalPrice;
	private String status;  // Siparişin durumu
	
	//Yapıcı metot: Yeni bir sipariş oluşturulduğunda otomatik bir ID atar.
	public Order() {
		this.orderId = counter++;
		this.items = new ArrayList<>();
	}
	
	//Sepetten sipariş oluşturma.
	 public void createFromCart (Cart cart, ProductManager manager) {
		 //Ürünlerin bir kopyasını oluşturma.
		  this.items = new ArrayList<>(cart.getItems());
	      this.totalPrice = cart.getTotalPrice();
	      this.status = "CREATED";
	      
	      //Stok yönetimi. Sepetteki her bir ürün için stok miktarını düşürürür.
	      for (CartItem item : items) {
	            manager.reduceStock(
	            	item.getProduct().getProductId(),
	            	item.getQuantity());
	      }
	      //sipariş oluştuktan sonra sepeti sıfırlar.
	      cart.clear();
	 }
	
	//ürün durumunu güncelleme
	public void updateStatus(String status) {
        this.status = status;
    }
	//getter metotları.
    public double getTotalPrice() {
        return totalPrice;
    }
    public String getStatus() {
        return status;
    }
    public int getOrderId() {
        return orderId;
    }
	
	//sınıf sürüm damgası.
	private static final long serialVersionUID = 1L;
	
}
