package shopix;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainScreen extends JFrame{
	private Cart cart = new Cart();
    private ProductManager pm = ProductManager.getInstance();

    public MainScreen(User user) {
        setTitle("Shopix - Ana Sayfa");
        setSize(400,300);
        setLayout(new BorderLayout());

        DefaultListModel<Product> model = new DefaultListModel<>();
        for (Product p : pm.getAllProducts()) {
            model.addElement(p);
        }

        JList<Product> list = new JList<>(model);

        JButton addToCart = new JButton("Sepete Ekle");
        JButton goCart = new JButton("Sepet");
        
        add(new JScrollPane(list), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.add(addToCart);
        panel.add(goCart);

        add(panel, BorderLayout.SOUTH);

        addToCart.addActionListener(e -> {

            Product p = list.getSelectedValue();

            if (p == null) return;

            if (p.getStockQuantity() <= 0) {
                JOptionPane.showMessageDialog(this,
                    "Bu ürün stokta yok!");
                return;
            }

            cart.addProduct(p, 1);
            JOptionPane.showMessageDialog(this, "Sepete eklendi!");
        });

        goCart.addActionListener(e -> {
            new CartFrame(cart);
        });
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);
    }

}