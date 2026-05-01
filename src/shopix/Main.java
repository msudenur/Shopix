package shopix;
import java.util.List;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
    	 try {
             // PRODUCT MANAGER
             ProductManager pm = ProductManager.getInstance();

             // Ürün yoksa ekle
             if (pm.getAllProducts().isEmpty()) {
                 pm.addProduct(new Product(1, "Telefon", 15000, 10, "Elektronik"));
                 pm.addProduct(new Product(2, "Laptop", 30000, 5, "Elektronik"));
                 pm.addProduct(new Product(3, "Kulaklık", 500, 20, "Aksesuar"));
             }

             // USER MANAGER
             UserManager um = new UserManager();

             // Kullanıcı yoksa admin oluştur
             if (um.getUsers().isEmpty()) {
                 User admin = new Admin(1, "Admin", "admin@mail.com", "admin", "1234");
                 um.register(admin);
                 System.out.println("Varsayılan admin oluşturuldu -> admin / 1234");
             }

             // GUI başlat
             SwingUtilities.invokeLater(() -> {
                 try {
                     new LoginFrame();
                 } catch (Exception e) {
                     System.err.println("GUI başlatma hatası: " + e.getMessage());
                 }
             });

         } catch (Exception e) {
             System.err.println("Uygulama başlatılırken hata oluştu: " + e.getMessage());
             e.printStackTrace();
         }
    	 
    	 List<Order> siparisler = (List<Order>) DosyaYonetimi.getInstance().veriCek("siparisler.dat");

    	 if (siparisler != null && !siparisler.isEmpty()) {
    	     // Liste içindeki en büyük ID'yi bul
    	     int maxId = 0;
    	     for (Order o : siparisler) {
    	         if (o.getOrderId() > maxId) {
    	             maxId = o.getOrderId();
    	         }
    	     }
    	     // Sayacı en büyük ID'nin bir fazlasından başlat
    	     Order.setCounter(maxId + 1);
    	 }
    }
}