package shopix;
import java.util.ArrayList;

public class FileManager {
	private static final String USER_FILE = "users.dat";

    // KULLANICILARI KAYDET
    public static void saveUsers(ArrayList<User> users) {
        DosyaYonetimi db = DosyaYonetimi.getInstance();
        db.kaydet(users, USER_FILE);
    }

    // KULLANICILARI YÜKLE
    public static ArrayList<User> loadUsers() {
        DosyaYonetimi db = DosyaYonetimi.getInstance();
        Object data = db.veriCek(USER_FILE);

        if (data == null) {
            return new ArrayList<>();
        }

        return (ArrayList<User>) data;
    }
    private static final String PRODUCT_FILE = "products.dat";

 // ÜRÜNLERİ KAYDET
 public static void saveProducts(ArrayList<Product> products) {
     DosyaYonetimi db = DosyaYonetimi.getInstance();
     db.kaydet(products, PRODUCT_FILE);
 }

 // ÜRÜNLERİ YÜKLE
 public static ArrayList<Product> loadProducts() {
     DosyaYonetimi db = DosyaYonetimi.getInstance();
     Object data = db.veriCek(PRODUCT_FILE);

     if (data == null) {
         return new ArrayList<>();
     }

     return (ArrayList<Product>) data;
 }

}