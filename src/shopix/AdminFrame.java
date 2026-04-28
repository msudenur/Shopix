package shopix;

import javax.swing.*;

public class AdminFrame extends JFrame {

    public AdminFrame(ProductManager productManager) {

        setTitle("Admin Paneli");
        setSize(450, 400);
        setLayout(null);

        JLabel titleLabel = new JLabel("Ürün Yönetimi");
        titleLabel.setBounds(170, 20, 150, 25);
        add(titleLabel);

        JLabel idLabel = new JLabel("Ürün ID:");
        idLabel.setBounds(40, 60, 100, 25);
        add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(150, 60, 180, 25);
        add(idField);

        JLabel nameLabel = new JLabel("Ürün Adı:");
        nameLabel.setBounds(40, 95, 100, 25);
        add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 95, 180, 25);
        add(nameField);

        JLabel priceLabel = new JLabel("Fiyat:");
        priceLabel.setBounds(40, 130, 100, 25);
        add(priceLabel);

        JTextField priceField = new JTextField();
        priceField.setBounds(150, 130, 180, 25);
        add(priceField);

        JLabel stockLabel = new JLabel("Stok:");
        stockLabel.setBounds(40, 165, 100, 25);
        add(stockLabel);

        JTextField stockField = new JTextField();
        stockField.setBounds(150, 165, 180, 25);
        add(stockField);

        JLabel categoryLabel = new JLabel("Kategori:");
        categoryLabel.setBounds(40, 200, 100, 25);
        add(categoryLabel);

        JTextField categoryField = new JTextField();
        categoryField.setBounds(150, 200, 180, 25);
        add(categoryField);

        JButton addButton = new JButton("Ürün Ekle");
        addButton.setBounds(40, 250, 120, 30);
        add(addButton);

        JButton deleteButton = new JButton("Ürün Sil");
        deleteButton.setBounds(170, 250, 120, 30);
        add(deleteButton);

        JButton listButton = new JButton("Ürünleri Gör");
        listButton.setBounds(300, 250, 120, 30);
        add(listButton);

        addButton.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int stock = Integer.parseInt(stockField.getText());
            String category = categoryField.getText();

            Product product = new Product(id, name, price, stock, category);
            productManager.addProduct(product);

            JOptionPane.showMessageDialog(this, "Ürün eklendi!");
        });

        deleteButton.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            productManager.removeProduct(id);

            JOptionPane.showMessageDialog(this, "Ürün silme işlemi yapıldı!");
        });

        listButton.addActionListener(e -> {
            new ProductFrame(productManager);
        });

        setVisible(true);
    }
}