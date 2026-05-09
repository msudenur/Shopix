package shopix;
import javax.swing.*;
import java.awt.*;

public class CartFrame extends JFrame {
  
    private JFrame parentFrame; 

    
    public CartFrame(Cart cart, JFrame parentFrame) {
        this.parentFrame = parentFrame; 
        
        setTitle("Sepet");
        setSize(400, 350); 
        setLayout(new BorderLayout());

        // SEPETTEKİ ÜRÜNLERİ LİSTELEME
        DefaultListModel<CartItem> model = new DefaultListModel<>();
        for (CartItem item : cart.getItems()) {
            model.addElement(item);
        }

        JList<CartItem> list = new JList<>(model);
        JLabel total = new JLabel("Toplam: " + cart.getTotalPrice());
        total.setHorizontalAlignment(SwingConstants.CENTER);

        // BUTONLAR
        JButton removeBtn = new JButton("Ürün Sil");
        JButton payBtn = new JButton("Ödeme Yap");

        // ALT PANEL
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(removeBtn);
        buttonPanel.add(payBtn);

        // COMPONENTLERİ EKLEME
        add(new JScrollPane(list), BorderLayout.CENTER);
        add(total, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        // =========================
        // ÜRÜN SİL AKSİYONU
        // =========================
        removeBtn.addActionListener(e -> {
            CartItem selectedItem = list.getSelectedValue();

            if (selectedItem == null) {
                JOptionPane.showMessageDialog(this, "Lütfen silmek istediğiniz ürünü seçin!");
                return;
            }

            // 1. Mantıksal sepetten sil
            cart.removeProduct(selectedItem.getProduct());
            
            // 2. Arayüz listesinden sil
            model.removeElement(selectedItem);
            
            // 3. Toplam fiyatı güncelle
            total.setText("Toplam: " + cart.getTotalPrice());

            JOptionPane.showMessageDialog(this, "Ürün sepetten çıkarıldı.");
        });

        // =========================
        // ÖDEME EKRANINA YÖNLENDİR
        // =========================
        payBtn.addActionListener(e -> {
            if (cart.getItems().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Sepet boş!");
                return;
            }
            new PaymentFrame(cart, parentFrame); 

            dispose();
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); 
        setVisible(true);
    }
}