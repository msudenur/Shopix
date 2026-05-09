package shopix;
import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {
    private Cart cart = new Cart();
    private ProductManager pm = ProductManager.getInstance();
    private DefaultListModel<Product> model;
    private JList<Product> list;

    public MainScreen(User user) {
        setTitle("Shopix - Ana Sayfa");
        setSize(400, 350);
        setLayout(new BorderLayout());

        model = new DefaultListModel<>();
        list = new JList<>(model);
        
        refreshTable();

        JButton addToCart = new JButton("Sepete Ekle");
        JButton goCart = new JButton("Sepet");
        
        add(new JScrollPane(list), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.add(addToCart);
        panel.add(goCart);

        add(panel, BorderLayout.SOUTH);

        addToCart.addActionListener(e -> {
            Product p = list.getSelectedValue();
            if (p == null) {
                JOptionPane.showMessageDialog(this, "Lütfen bir ürün seçin!");
                return;
            }
            if (p.getStockQuantity() <= 0) {
                JOptionPane.showMessageDialog(this, "Bu ürün stokta yok!");
                return;
            }
            cart.addProduct(p, 1);
            JOptionPane.showMessageDialog(this, p.getProductName() + " sepete eklendi!");
        });


        goCart.addActionListener(e -> {
           
            new CartFrame(cart, this); 
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void refreshTable() {
        if (model != null) {
            model.clear();
            for (Product p : pm.getAllProducts()) {
                model.addElement(p);
            }
        }
    }
}