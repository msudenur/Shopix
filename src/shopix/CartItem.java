package shopix;

//CartItem sınıfı, alışveriş sepetindeki her bir ögeyi (ürün ve miktar) temsil eder.

public class CartItem {
	private Product product;
    private int quantity;
    
    //Constructor, sepete eklenecek ürün ve ürün sayısı
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    
    public void setQuantity(int quantity) {
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
    @Override
    public String toString() {
        return product.getProductName() + " x " + quantity + " = " + getSubTotal() + " TL";
    }

}