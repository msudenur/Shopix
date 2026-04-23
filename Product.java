package shopix.zbt;

import java.io.Serializable;

public class Product implements Serializable {
	private int productId;
	private String productName;
	private double price;
	private int stockQuantity;
	private String category;

	public Product(int productId, String productName, double price, int stockQuantity, String category) {
		this.productId = productId;
	    this.productName = productName;
	    this.price = price;
	    this.stockQuantity = stockQuantity;
	    this.category = category;}
	public int getProductId() {
		return productId ;}
	public void setProductId(int productId) {
		this.productId=productId;}
	public String getProductName() {
		return productName;}
	public void setProductName(String productName) {
		this.productName=productName;}
	public double getPrice() {
		return price;}
	public void setPrice(double price) {
		if(price<=0) {
			System.out.println("Ürün ücreti negatif veya 0 olamaz!");
		}
		this.price=price;}
	public int getStockQuantity() {
		return stockQuantity;}
	public void setStockQuantity(int stockQuantity) {
		if(stockQuantity <0) {
			System.out.println("Stok adedi negatif olamaz!");
		}
		this.stockQuantity=stockQuantity;}
	public String getCategory() {
		return category;}
	public void setCategory(String category) {
		this.category=category;}
	@Override
	public String toString() {
		return "Ürün [ID=" + productId + ", İsim=" + productName + ",Fiyat=" + price + ",Stok=" + stockQuantity + ", Kategori=" + category + "]";
}
	}


