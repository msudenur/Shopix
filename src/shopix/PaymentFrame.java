package shopix;

import javax.swing.*;

public class PaymentFrame extends JFrame {

	public PaymentFrame(double totalPrice) {

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

                CreditCard card = new CreditCard(number.trim(), cvv.trim(), "12/28");

                PaymentStrategy<CreditCard> payment =
                        (PaymentStrategy<CreditCard>) PaymentFactory.getPayment("card");

                payment.pay(card);

                JOptionPane.showMessageDialog(this,
                        "Kredi kartı ile ödeme başarılı!\nTutar: " + totalPrice);

                dispose();

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

                PaymentStrategy<String> payment =
                        (PaymentStrategy<String>) PaymentFactory.getPayment("paypal");

                payment.pay(email);

                JOptionPane.showMessageDialog(this,
                        "PayPal ödeme başarılı!\nTutar: " + totalPrice);

                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        // =======================
        // KAPIDA ÖDEME
        // =======================
        cashBtn.addActionListener(e -> {

            try {
                PaymentStrategy<Double> payment =
                        (PaymentStrategy<Double>) PaymentFactory.getPayment("cash");

                payment.pay(totalPrice);

                JOptionPane.showMessageDialog(this,
                        "Kapıda ödeme seçildi!\nTutar: " + totalPrice);

                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        setVisible(true);
    }
}