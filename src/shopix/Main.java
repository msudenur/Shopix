package shopix;

public class Main {

    public static void main(String[] args) {

        UserManager userManager = new UserManager();

        userManager.register(new Customer(1, "Ali", "ali@mail.com", "ali", "123"));

        ProductManager productManager = ProductManager.getInstance();

        productManager.addProduct(new Product(101, "Laptop", 25000, 10, "Electronics"));
        productManager.addProduct(new Product(102, "Mouse", 500, 20, "Accessories"));
        productManager.addProduct(new Product(103, "Keyboard", 1200, 15, "Accessories"));

        new LoginFrame(userManager, productManager);
    }
}

