package shopix;
import javax.swing.*;
import java.awt.*;

public class AdminFrame extends JFrame{
	private ProductManager pm = ProductManager.getInstance();

    public AdminFrame() {
        setTitle("Admin Panel");
        setSize(400,400);
        setLayout(new GridLayout(7,2));

        JTextField id = new JTextField();
        JTextField name = new JTextField();
        JTextField price = new JTextField();
        JTextField stock = new JTextField();

        JButton add = new JButton("Ekle");
        JButton delete = new JButton("Sil");
        JButton update = new JButton("Güncelle");
        JButton list = new JButton("Listele");

        add(new JLabel("ID"));
        add(id);
        add(new JLabel("İsim"));
        add(name);
        add(new JLabel("Fiyat"));
        add(price);
        add(new JLabel("Stok"));
        add(stock);

        add(add);
        add(delete);
        add(update);
        add(list);

        // EKLE
        add.addActionListener(e -> {
            Product p = new Product(
                Integer.parseInt(id.getText()),
                name.getText(),
                Double.parseDouble(price.getText()),
                Integer.parseInt(stock.getText()),
                "Genel"
            );
            pm.addProduct(p);
        });

        // SİL
        delete.addActionListener(e -> {
            pm.removeProduct(Integer.parseInt(id.getText()));
        });

        // GÜNCELLE
        update.addActionListener(e -> {
            Product p = new Product(
                Integer.parseInt(id.getText()),
                name.getText(),
                Double.parseDouble(price.getText()),
                Integer.parseInt(stock.getText()),
                "Genel"
            );
            pm.updateProduct(p);
        });

        // LİSTELE
        list.addActionListener(e -> {
            for (Product p : pm.getAllProducts()) {
                System.out.println(p);
            }
        });

        setVisible(true);
    }

}