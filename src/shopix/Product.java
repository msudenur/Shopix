package shopix;

import java.io.Serializable;

public class Product implements Serializable {
	private int productId;
	private String productName;
	private double price;
	private int stockQuantity;
	private String category;

	// 1. Orijinal Constructor (5 Parametreli)
	public Product(int productId, String productName, double price, int stockQuantity, String category) {
		this.productId = productId;
	    this.productName = productName;
	    this.price = price;
	    this.stockQuantity = stockQuantity;
	    this.category = category;
	}

	// 2. YENİ EKLEDİĞİMİZ CONSTRUCTOR (4 Parametreli)
	// resim_10.png'deki hatayı bu kısım düzeltecek
	public Product(int productId, String productName, double price, int stockQuantity) {
		this.productId = productId;
	    this.productName = productName;
	    this.price = price;
	    this.stockQuantity = stockQuantity;
	    this.category = "Genel"; // Veri tabanında kategori yoksa varsayılan atar
	}

	public int getProductId() {
		return productId ;
	}
	public void setProductId(int productId) {
		this.productId=productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName=productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		if(price <= 0) {
	        System.out.println("Hata: Ürün ücreti negatif veya 0 olamaz! İşlem iptal edildi.");
	        return;
	    }
		this.price=price;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		if(stockQuantity < 0) {
	        System.out.println("Hata: Stok adedi negatif olamaz! İşlem iptal edildi.");
	        return;
	    }
		this.stockQuantity=stockQuantity;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category=category;
	}
	
	@Override
	public String toString() {
		return "Ürün [ID=" + productId + ", İsim=" + productName + ", Fiyat=" + price + ", Stok=" + stockQuantity + ", Kategori=" + category + "]";
	}
}