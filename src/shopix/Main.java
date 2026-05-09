package shopix;

import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        try {
            // PRODUCT MANAGER
            ProductManager pm = ProductManager.getInstance();

            // Ürün yoksa ekle (Bu pm.getAllProducts() SQL'den çeker)
            if (pm.getAllProducts().isEmpty()) {
                pm.addProduct(new Product(1, "Telefon", 15000, 10, "Elektronik"));
                pm.addProduct(new Product(2, "Laptop", 30000, 5, "Elektronik"));
                pm.addProduct(new Product(3, "Kulaklık", 500, 20, "Aksesuar"));
            }

            // USER MANAGER
            UserManager um = new UserManager();

            // Kullanıcı yoksa admin oluştur (SQL'e kaydeder)
            if (um.getUsers().isEmpty()) {
                User admin = new Admin(1, "Admin", "admin@mail.com", "admin", "1234");
                um.register(admin);
                System.out.println("Varsayılan admin oluşturuldu -> admin / 1234");
            }

            // ORDER MANAGER - BURAYI GÜNCELLEDİK: DosyaYonetimi SİLİNDİ
            // Eğer Order için de bir OrderManager/SQL yapınız varsa oradan çekilmeli.
            // Şimdilik hata vermemesi için boş liste veya mevcut SQL yapısına göre güncellendi.
            List<Order> siparisler = new ArrayList<>(); 
            
            // Eğer siparişleri SQL'den çekmek isterseniz buraya pm.getAllOrders() gibi bir yapı gelmeli.
            if (siparisler != null && !siparisler.isEmpty()) {
                int maxId = 0;
                for (Order o : siparisler) {
                    if (o.getOrderId() > maxId) {
                        maxId = o.getOrderId();
                    }
                }
                Order.setCounter(maxId + 1);
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
    }
}