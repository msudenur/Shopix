package shopix;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private UserManager userManager;
    private ProductManager productManager;

    public LoginFrame(UserManager userManager, ProductManager productManager) {
        this.userManager = userManager;
        this.productManager = productManager;

        setTitle("Shopix - Login");
        setSize(350, 230);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Giriş Yap");
        JButton registerButton = new JButton("Kayıt Ol");

        add(new JLabel("Kullanıcı Adı:"));
        add(usernameField);
        add(new JLabel("Şifre:"));
        add(passwordField);
        add(loginButton);
        add(registerButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            User user = userManager.login(username, password);

            if (user == null) {
                JOptionPane.showMessageDialog(this, "Hatalı kullanıcı adı veya şifre!");
            } else if (user instanceof Admin) {
                new AdminFrame(productManager);
                dispose();
            } else {
                new ProductFrame(productManager);
                dispose();
            }
        });

        registerButton.addActionListener(e -> new RegisterFrame(userManager));

        setVisible(true);
    }
}