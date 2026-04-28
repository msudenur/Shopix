package shopix;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        ProductManager productManager = ProductManager.getInstance();

        userManager.register(new Admin(0, "Admin", "admin@shopix.com", "admin", "1234"));

        productManager.addProduct(new Product(101, "Laptop", 25000, 10, "Electronics"));
        productManager.addProduct(new Product(102, "Mouse", 500, 20, "Accessories"));
        productManager.addProduct(new Product(103, "Keyboard", 1200, 15, "Accessories"));

        new LoginFrame(userManager, productManager);
    }
}

