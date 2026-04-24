package shopix;
import java.io.Serializable; // Nesnenin dosyaya kaydedilebilmesi veya ağ üzerinden gönderilebilmesi için gerekli
/*CartItem sınıfı, alışveriş sepetindeki her bir öğeyi (ürün ve miktar) temsil eder.
 * Serializable arayüzünü uygulayarak nesne durumunun korunmasını sağlar.
 */
public class CartItem implements Serializable{
	private Product product;
    private int quantity;
    
    //Constructor, sepete eklenecek ürün ve ürün sayısı
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    
    //Ürünün toplam maliyetini hesaplayan metot
    public double getSubTotal() {
        return product.getPrice() * quantity;
    }
    
    //Getter metotları
    public Product getProduct() {
        return product;
    }
    public int getQuantity() {
        return quantity;
    }
    
 // Nesne serileştirme sırasında versiyon kontrolü için kullanılan benzersiz kimlik
    private static final long serialVersionUID = 1L;

}
