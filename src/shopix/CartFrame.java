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

        payBtn.addActionListener(e -> {String[] options = {"Kart", "PayPal", "Kapıda"};
            int choice = JOptionPane.showOptionDialog(this,
                    "Ödeme seç",
                    "Ödeme",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

         // ... önceki kodlar ...
            try {
                if (choice == 0) {
                    // 1. Kullanıcıdan bilgileri JOptionPane ile alalım (veya PaymentFrame'e yönlendir)
                    String kartNo = JOptionPane.showInputDialog(this, "16 Haneli Kart Numaranızı Giriniz:");
                    String cvv = JOptionPane.showInputDialog(this, "3 Haneli CVV Giriniz:");
                    String skt = JOptionPane.showInputDialog(this, "Son Kullanma Tarihi (AA/YY):");

                    // 2. Girilen verilerin boş olup olmadığını kontrol et
                    if (kartNo == null || cvv == null || skt == null) return;

                    // 3. NESNE OLUŞTURMA (Hata burada yakalanacak)
                    // Eğer kartNo 16 hane değilse, CreditCard sınıfı Exception fırlatır.
                    CreditCard kartBilgisi = new CreditCard(kartNo.trim(), cvv.trim(), skt.trim());

                    PaymentStrategy<CreditCard> p = (PaymentStrategy<CreditCard>) PaymentFactory.getPayment("card");
                    p.pay(kartBilgisi);

                } else if (choice == 1) {
                  
                    PaymentStrategy<String> p =
                            (PaymentStrategy<String>) PaymentFactory.getPayment("paypal");

                    p.pay("mail@test.com");

                } else {
                    PaymentStrategy<Double> p =
                            (PaymentStrategy<Double>) PaymentFactory.getPayment("cash");

                    p.pay(cart.getTotalPrice());
                }

                JOptionPane.showMessageDialog(this,"Ödeme başarılı!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setVisible(true);
    }

}