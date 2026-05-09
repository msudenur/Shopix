package shopix;

import javax.swing.*;

public class PaymentFrame extends JFrame {
    private JFrame parentFrame; 

    public PaymentFrame(Cart cart, JFrame parentFrame) { 
        this.parentFrame = parentFrame; // Referansı saklar.
        double totalPrice = cart.getTotalPrice();

        setTitle("Ödeme Ekranı");
        setSize(450, 300);
        setLayout(null);

        JLabel label = new JLabel("Ödeme Yöntemini Seçin");
        label.setBounds(150, 20, 200, 25);
        add(label);

        JButton cardBtn = new JButton("Kredi Kartı");
        JButton paypalBtn = new JButton("PayPal");
        JButton cashBtn = new JButton("Kapıda Ödeme");

        cardBtn.setBounds(50, 80, 120, 30);
        paypalBtn.setBounds(180, 80, 120, 30);
        cashBtn.setBounds(310, 80, 120, 30);

        add(cardBtn);
        add(paypalBtn);
        add(cashBtn);

        // =======================
        // KREDİ KARTI
        // =======================
        cardBtn.addActionListener(e -> {
            try {
                String name = JOptionPane.showInputDialog(this, "Kart Sahibi:");
                String number = JOptionPane.showInputDialog(this, "16 haneli Kart No:");
                String cvv = JOptionPane.showInputDialog(this, "CVV:");

                if (name == null || number == null || cvv == null) return;

                CreditCard cardDetails = new CreditCard(number.trim(), cvv.trim(), "12/28");
                PaymentStrategy<CreditCard> payment = (PaymentStrategy<CreditCard>) PaymentFactory.getPayment("card");
                payment.pay(cardDetails);

                tamamlaOdeme(cart, totalPrice);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        // =======================
        // PAYPAL
        // =======================
        paypalBtn.addActionListener(e -> {
            try {
                String email = JOptionPane.showInputDialog(this, "Email:");
                if (email == null) return;

                PaymentStrategy<String> payment = (PaymentStrategy<String>) PaymentFactory.getPayment("paypal");
                payment.pay(email);

                tamamlaOdeme(cart, totalPrice);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        // =======================
        // KAPIDA ÖDEME
        // =======================
        cashBtn.addActionListener(e -> {
            try {
                PaymentStrategy<Double> payment = (PaymentStrategy<Double>) PaymentFactory.getPayment("cash");
                payment.pay(totalPrice);

                tamamlaOdeme(cart, totalPrice);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     *  Stok düşer ve ilgili arayüzü yeniler.
     */
    private void tamamlaOdeme(Cart cart, double totalPrice) {
        // 1. STOK AZALTMA VE SİPARİŞ OLUŞTURMA
        Order order = new Order();
        order.createFromCart(cart, ProductManager.getInstance());

        // 2. ARAYÜZÜ YENİLE 
        if (parentFrame instanceof ProductFrame) {
            ((ProductFrame) parentFrame).refreshTable();
        } else if (parentFrame instanceof MainScreen) {
            ((MainScreen) parentFrame).refreshTable();
        }

        JOptionPane.showMessageDialog(this, "Ödeme başarılı! Stoklar güncellendi.\nTutar: " + totalPrice);
        dispose();
    }
}