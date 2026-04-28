package shopix;

import javax.swing.*;

public class ProductFrame extends JFrame {

    private Cart cart = new Cart();

    public ProductFrame(ProductManager productManager) {

        setTitle("Shopix Ürünler");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titleLabel = new JLabel("Ürün Listesi");
        titleLabel.setBounds(200, 20, 150, 25);
        add(titleLabel);

        DefaultListModel<Product> model = new DefaultListModel<>();

        for (Product p : productManager.getAllProducts()) {
            model.addElement(p);
        }

        JList<Product> productList = new JList<>(model);
        productList.setBounds(50, 60, 380, 180);
        add(productList);

        JButton addButton = new JButton("Sepete Ekle");
        addButton.setBounds(50, 270, 150, 30);
        add(addButton);

        JButton cartButton = new JButton("Sepeti Gör");
        cartButton.setBounds(250, 270, 150, 30);
        add(cartButton);

        addButton.addActionListener(e -> {

            Product selectedProduct = productList.getSelectedValue();

            if (selectedProduct != null) {
                cart.addProduct(selectedProduct, 1);
                JOptionPane.showMessageDialog(this, "Ürün sepete eklendi!");
            } else {
                JOptionPane.showMessageDialog(this, "Lütfen bir ürün seçiniz!");
            }
        });

        cartButton.addActionListener(e -> {
            new CartFrame(cart);
        });

        setVisible(true);
    }
}