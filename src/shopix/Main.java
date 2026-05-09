package shopix;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. PRODUCT MANAGER BAŞLATMA
            ProductManager pm = ProductManager.getInstance();

            if (pm.getAllProducts().isEmpty()) {
                pm.addProduct(new Product("Telefon", 15000, 10, "Elektronik"));
                pm.addProduct(new Product("Laptop", 30000, 5, "Elektronik"));
                pm.addProduct(new Product("Kulaklık", 500, 20, "Aksesuar"));
            }

            // 2. USER MANAGER BAŞLATMA
            UserManager um = new UserManager();

         
            if (um.getUsers().isEmpty()) {
              
                User admin = new Admin("Admin", "admin@mail.com", "admin", "1234");
                um.register(admin);
                System.out.println("Sistem: Varsayılan admin oluşturuldu -> Kullanıcı Adı: admin / Şifre: 1234");
            }

            
            // 4. GUI (ARAYÜZ) BAŞLATMA
            SwingUtilities.invokeLater(() -> {
                try {
                    new LoginFrame();
                } catch (Exception e) {
                    System.err.println("Görsel arayüz başlatma hatası: " + e.getMessage());
                }
            });

        } catch (Exception e) {
            System.err.println("Uygulama kritik bir hata nedeniyle başlatılamadı: " + e.getMessage());
            e.printStackTrace();
        }
    }
}