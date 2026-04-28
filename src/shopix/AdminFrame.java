package shopix;

import javax.swing.*;
import java.awt.*;

public class AdminFrame extends JFrame {
    private ProductManager productManager;
    private JList<Product> productList;

    private JTextField idField;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField stockField;
    private JTextField categoryField;

    public AdminFrame(ProductManager productManager) {
        this.productManager = productManager;

        setTitle("Shopix - Admin Paneli");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        productList = new JList<>();
        refreshProductList();

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(new JLabel("Ürün Listesi"), BorderLayout.NORTH);
        listPanel.add(new JScrollPane(productList), BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        idField = new JTextField();
        nameField = new JTextField();
        priceField = new JTextField();
        stockField = new JTextField();
        categoryField = new JTextField();

        formPanel.add(new JLabel("Ürün ID:"));
        formPanel.add(idField);

        formPanel.add(new JLabel("Ürün Adı:"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Fiyat:"));
        formPanel.add(priceField);

        formPanel.add(new JLabel("Stok:"));
        formPanel.add(stockField);

        formPanel.add(new JLabel("Kategori:"));
        formPanel.add(categoryField);

        JButton addButton = new JButton("Ürün Ekle");
        JButton deleteButton = new JButton("Ürün Sil");
        JButton updateButton = new JButton("Ürün Güncelle");
        JButton listButton = new JButton("Listeyi Yenile");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(listButton);

        add(listPanel, BorderLayout.WEST);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        productList.addListSelectionListener(e -> fillFields());

        addButton.addActionListener(e -> addProduct());
        deleteButton.addActionListener(e -> deleteProduct());
        updateButton.addActionListener(e -> updateProduct());
        listButton.addActionListener(e -> refreshProductList());

        setVisible(true);
    }

    private void refreshProductList() {
        DefaultListModel<Product> model = new DefaultListModel<>();

        for (Product p : productManager.getAllProducts()) {
            model.addElement(p);
        }

        productList.setModel(model);
    }

    private void fillFields() {
        Product p = productList.getSelectedValue();

        if (p != null) {
            idField.setText(String.valueOf(p.getProductId()));
            nameField.setText(p.getProductName());
            priceField.setText(String.valueOf(p.getPrice()));
            stockField.setText(String.valueOf(p.getStockQuantity()));
            categoryField.setText(p.getCategory());
        }
    }

    private void addProduct() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int stock = Integer.parseInt(stockField.getText());
            String category = categoryField.getText();

            Product product = new Product(id, name, price, stock, category);
            productManager.addProduct(product);

            refreshProductList();
            JOptionPane.showMessageDialog(this, "Ürün eklendi.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lütfen geçerli bilgiler giriniz.");
        }
    }

    private void deleteProduct() {
        try {
            int id = Integer.parseInt(idField.getText());
            productManager.removeProduct(id);

            refreshProductList();
            JOptionPane.showMessageDialog(this, "Ürün silindi.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Silmek için geçerli ürün ID giriniz.");
        }
    }

    private void updateProduct() {
        Product selected = productList.getSelectedValue();

        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Lütfen güncellenecek ürünü seçiniz.");
            return;
        }

        try {
            selected.setProductId(Integer.parseInt(idField.getText()));
            selected.setProductName(nameField.getText());
            selected.setPrice(Double.parseDouble(priceField.getText()));
            selected.setStockQuantity(Integer.parseInt(stockField.getText()));
            selected.setCategory(categoryField.getText());

            refreshProductList();
            JOptionPane.showMessageDialog(this, "Ürün güncellendi.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lütfen geçerli bilgiler giriniz.");
        }
    }
}