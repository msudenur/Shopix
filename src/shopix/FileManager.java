package shopix;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static void saveUsers(ArrayList<User> users) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat"));
            oos.writeObject(users);
            oos.close();
        } catch (IOException e) {
            System.out.println("Kullanıcılar kaydedilemedi.");
        }
    }

    public static ArrayList<User> loadUsers() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.dat"));
            ArrayList<User> users = (ArrayList<User>) ois.readObject();
            ois.close();
            return users;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static void saveProducts(List<Product> products) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("products.dat"));
            oos.writeObject(products);
            oos.close();
        } catch (IOException e) {
            System.out.println("Ürünler kaydedilemedi.");
        }
    }

    public static List<Product> loadProducts() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("products.dat"));
            List<Product> products = (List<Product>) ois.readObject();
            ois.close();
            return products;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}