package shopix;

import javax.swing.*;

public class CartFrame extends JFrame {

    public CartFrame(Cart cart) {

        setTitle("Sepetim");
        setSize(500, 380);
        setLayout(null);

        JLabel titleLabel = new JLabel("Sepetteki Ürünler");
        titleLabel.setBounds(180, 20, 150, 25);
        add(titleLabel);

        JTextArea cartArea = new JTextArea();
        cartArea.setBounds(50, 60, 380, 160);
        cartArea.setEditable(false);
        add(cartArea);

        updateCartText(cartArea, cart);

        JButton clearButton = new JButton("Sepeti Temizle");
        clearButton.setBounds(70, 240, 150, 30);
        add(clearButton);

        JButton orderButton = new JButton("Sipariş Oluştur");
        orderButton.setBounds(250, 240, 160, 30);
        add(orderButton);

        clearButton.addActionListener(e -> {
            cart.getItems().clear();
            updateCartText(cartArea, cart);
            JOptionPane.showMessageDialog(this, "Sepet temizlendi!");
        });

        orderButton.addActionListener(e -> {

            if (cart.getItems().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Sepet boş!");
                return;
            }

            Order order = new Order();
            order.createFromCart(cart, ProductManager.getInstance());

            JOptionPane.showMessageDialog(this, "Sipariş başarıyla oluşturuldu!");

            new PaymentFrame(cart.getTotalPrice());
        });

        setVisible(true);
    }

    private void updateCartText(JTextArea cartArea, Cart cart) {

        String text = "";

        for (Object obj : cart.getItems()) {
            CartItem item = (CartItem) obj;
            text += item.getProduct().getProductName()
                    + " | Adet: " + item.getQuantity()
                    + " | Toplam: " + item.getSubTotal() + " TL\n";
        }

        text += "\nGenel Toplam: " + cart.getTotalPrice() + " TL";

        cartArea.setText(text);
    }
}