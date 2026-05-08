package shopix;
import javax.swing.*;
import java.awt.*;

public class CartFrame extends JFrame {
	
	public CartFrame(Cart cart) {
        setTitle("Sepet");
        setSize(400,300);
        setLayout(new BorderLayout());

        DefaultListModel<CartItem> model = new DefaultListModel<>();
        for (CartItem item : cart.getItems()) {
            model.addElement(item);
        }

        JList<CartItem> list = new JList<>(model);

        JLabel total = new JLabel("Toplam: " + cart.getTotalPrice());

        JButton payBtn = new JButton("Ödeme Yap");

        add(new JScrollPane(list), BorderLayout.CENTER);
        add(total, BorderLayout.NORTH);
        add(payBtn, BorderLayout.SOUTH);

     // =========================
        // ÖDEME EKRANINA YÖNLENDİR
        // =========================
        payBtn.addActionListener(e -> {

            if (cart.getItems().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Sepet boş!");
                return;
            }

            // STRATEGY + FACTORY burada değil, PaymentFrame içinde olacak
            new PaymentFrame(cart.getTotalPrice());

            dispose();
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
	

}