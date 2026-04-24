package shopix;
import java.io.Serializable;// Nesnenin dosyaya kaydedilebilmesi veya ağ üzerinden gönderilebilmesi için gerekli
import java.util.ArrayList;
import java.util.List;
/*
 * Cart sınıfı, müşterinin seçtiği ürünleri yöneten alışveriş sepetini temsil eder.
 */
public class Cart implements Serializable{
	// Sepetteki ürünlerin (CartItem) tutulduğu dinamik liste
	private List<CartItem> items = new ArrayList<>();
	
	//Sepete ürün ekler, ekliyse miktarı günceller.
    public void addProduct(Product product, int qty) {
        for (CartItem item : items) { //ürün daha önce kelenmiş mi kontrolü
            if (item.getProduct() == product) {
                items.remove(item); //eskisini sil
                items.add(new CartItem(product, item.getQuantity() + qty)); //yeni şekilde güncelle
                return;
            }
        }
        //Ürün ekli değilse doğrudan ekler.
        items.add(new CartItem(product, qty));
    }
    
    //Sepetten ürünü kaldırır.
    public void removeProduct(Product product) {
    	//lamda operatörü ile eşleşen ürünü silme.
        items.removeIf(i -> i.getProduct() == product);
    }
    
    //Sepetteki yeni listeyi döndürür.
    public List<CartItem> getItems() {
        return items;
    }
    
    //Sepettin toplam tutarını hesaplar.
    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getSubTotal();
        }
        return total;
    }
    
    //Sepeti tamamen boşaltır.
    public void clear() {
        items.clear();
    }
	
    //sınıf  sürüm damgası
	private static final long serialVersionUID = 1L;

}
