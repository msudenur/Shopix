package shopix.zbt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class ProductManager implements Serializable {
		private static ProductManager instance;
	    private List<Product> productList;
	
	private ProductManager() {
        this.productList = new ArrayList<>();}
	public static ProductManager getInstance() {
        if (instance == null) {
            instance = new ProductManager();
        }
        return instance;}
	public void addProduct(Product newProduct) {
		if (newProduct == null) {
	        System.out.println("Hata: Boş ürün eklenemez.");
	        return;}
		for (Product p : productList) {
	        if (p.getProductId() == newProduct.getProductId()) {
	            System.out.println("Hata: " + newProduct.getProductId() + " ID'li ürün zaten sistemde kayıtlı!");
	            return; 
	        }}
        productList.add(newProduct);}
	public List<Product> getAllProducts() {
	    return productList;
	}
	public void removeProduct(int productId) {
		Product toRemove = null;
		for (Product p : productList) {
	        if (p.getProductId() == productId) {
	            toRemove = p;
	            break;}}
		if (toRemove != null) {
	        productList.remove(toRemove);
	        System.out.println(productId + " ID'li ürün sistemden silindi.");}
		else {
	        System.out.println("Hata: " + productId + " ID'li bir ürün bulunamadı.");}}
	public void updateProduct(Product updatedProduct) {
		if(updatedProduct == null)
			return;
		for(int i=0; i<productList.size(); i++) {
			if(productList.get(i).getProductId()== updatedProduct.getProductId()) {
				productList.set(i, updatedProduct);
				System.out.println(updatedProduct.getProductId() + " ID'li ürün güncellendi.");
	            return;}}
		System.out.println("Güncelleme hatası: Ürün bulunamadı.");}
	public boolean isStockAvailable(int productId ,int requestedQuantity) {
		for(Product p : productList) {
			if(p.getProductId()== productId) {
				return p.getStockQuantity() >= requestedQuantity;}}
		return false;}
	public void reduceStock(int productId,int quantity) {
		for(Product p: productList) {
			if(p.getProductId()==productId) {
				int currentStock=p.getStockQuantity();
				if (currentStock >= quantity) {
	                p.setStockQuantity(currentStock - quantity);
	                System.out.println("Stok güncellendi. Yeni Stok: " + p.getStockQuantity());
	                return;} 
				else {
	                System.out.println("Hata: Stok yetersiz, azaltma yapılamadı.");}
	            return;}
			System.out.println("Ürün bulunamadı!");}}
	}
	


