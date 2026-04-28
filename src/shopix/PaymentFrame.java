package shopix;

import javax.swing.*;

public class PaymentFrame extends JFrame {

    public PaymentFrame(double totalPrice) {

        setTitle("Ödeme Ekranı");
        setSize(450, 400);
        setLayout(null);

        JLabel titleLabel = new JLabel("Ödeme Yöntemi Seçiniz");
        titleLabel.setBounds(140, 20, 200, 25);
        add(titleLabel);

        JRadioButton cashOption = new JRadioButton("Kapıda Ödeme");
        cashOption.setBounds(50, 60, 150, 25);
        add(cashOption);

        JRadioButton emailOption = new JRadioButton("E-mail ile Ödeme");
        emailOption.setBounds(50, 90, 150, 25);
        add(emailOption);

        JRadioButton cardOption = new JRadioButton("Kredi Kartı ile Ödeme");
        cardOption.setBounds(50, 120, 180, 25);
        add(cardOption);

        ButtonGroup paymentGroup = new ButtonGroup();
        paymentGroup.add(cashOption);
        paymentGroup.add(emailOption);
        paymentGroup.add(cardOption);

        JLabel cardNameLabel = new JLabel("Kart Sahibi:");
        cardNameLabel.setBounds(50, 170, 100, 25);
        add(cardNameLabel);

        JTextField cardNameField = new JTextField();
        cardNameField.setBounds(160, 170, 200, 25);
        add(cardNameField);

        JLabel cardNumberLabel = new JLabel("Kart No:");
        cardNumberLabel.setBounds(50, 205, 100, 25);
        add(cardNumberLabel);

        JTextField cardNumberField = new JTextField();
        cardNumberField.setBounds(160, 205, 200, 25);
        add(cardNumberField);

        JLabel cvvLabel = new JLabel("CVV:");
        cvvLabel.setBounds(50, 240, 100, 25);
        add(cvvLabel);

        JTextField cvvField = new JTextField();
        cvvField.setBounds(160, 240, 200, 25);
        add(cvvField);

        JButton payButton = new JButton("Ödemeyi Yap");
        payButton.setBounds(140, 300, 150, 30);
        add(payButton);

        payButton.addActionListener(e -> {

            if (cashOption.isSelected()) {
                JOptionPane.showMessageDialog(this,
                        "Kapıda ödeme seçildi.\nToplam Tutar: " + totalPrice + " TL");
                dispose();

            } else if (emailOption.isSelected()) {
                JOptionPane.showMessageDialog(this,
                        "E-mail ile ödeme seçildi.\nÖdeme bilgileri e-mail adresinize gönderilecektir.\nToplam Tutar: "
                                + totalPrice + " TL");
                dispose();

            } else if (cardOption.isSelected()) {

                if (cardNameField.getText().isEmpty()
                        || cardNumberField.getText().isEmpty()
                        || cvvField.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(this,
                            "Lütfen kart bilgilerini eksiksiz giriniz!");

                } else {
                    JOptionPane.showMessageDialog(this,
                            "Kredi kartı ile ödeme başarılı!\nToplam Tutar: " + totalPrice + " TL");
                    dispose();
                }

            } else {
                JOptionPane.showMessageDialog(this,
                        "Lütfen bir ödeme yöntemi seçiniz!");
            }
        });

        setVisible(true);
    }
}