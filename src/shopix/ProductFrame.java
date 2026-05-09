package shopix;

import javax.swing.*;

public class ProductFrame extends JFrame {

    private Cart cart = new Cart();
    private ProductManager productManager; 
    private DefaultListModel<Product> model; 
    private JList<Product> productList; 

    public ProductFrame(ProductManager productManager) {
        this.productManager = productManager; 

        setTitle("Shopix Ürünler");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titleLabel = new JLabel("Ürün Listesi");
        titleLabel.setBounds(200, 20, 150, 25);
        add(titleLabel);

        // Model ve Liste Oluşturma
        model = new DefaultListModel<>();
        productList = new JList<>(model);
        productList.setBounds(50, 60, 380, 180);
        add(productList);

        // Verileri ilk kez yükler
        refreshTable();

        JButton addButton = new JButton("Sepete Ekle");
        addButton.setBounds(50, 270, 150, 30);
        add(addButton);

        JButton cartButton = new JButton("Sepeti Gör");
        cartButton.setBounds(250, 270, 150, 30);
        add(cartButton);

        // SEPETE EKLEME AKSİYONU
        addButton.addActionListener(e -> {
            Product selectedProduct = productList.getSelectedValue();

            if (selectedProduct == null) {
                JOptionPane.showMessageDialog(this, "Lütfen bir ürün seçiniz!");
                return;
            }

            if (selectedProduct.getStockQuantity() <= 0) {
                JOptionPane.showMessageDialog(this, "Bu ürünün stoğu yok!");
                return;
            }

            cart.addProduct(selectedProduct, 1);
            JOptionPane.showMessageDialog(this, "Ürün sepete eklendi!");
        });

        // SEPETİ GÖRÜNTÜLEME AKSİYONU
        cartButton.addActionListener(e -> {
         
            new CartFrame(cart, this); 
        });
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Veritabanından güncel ürün bilgilerini çekerek listeyi yeniler.
     */
    public void refreshTable() {
        if (model != null && productManager != null) {
            model.clear(); 
            for (Product p : productManager.getAllProducts()) {
                model.addElement(p); 
            }
        }
    }
}