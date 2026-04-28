package shopix;

import javax.swing.*;

public class ProductFrame extends JFrame {

    public ProductFrame(ProductManager productManager) {

        setTitle("Shopix Ürünler");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titleLabel = new JLabel("Ürün Listesi");
        titleLabel.setBounds(170, 20, 150, 25);
        add(titleLabel);

        JTextArea productArea = new JTextArea();
        productArea.setBounds(50, 60, 330, 200);
        productArea.setEditable(false);

        String productsText = "";

        for (Product p : productManager.getAllProducts()) {
            productsText += p.toString() + "\n";
        }

        productArea.setText(productsText);
        add(productArea);

        setVisible(true);
    }
}