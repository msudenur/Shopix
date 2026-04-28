package shopix;

import javax.swing.*;

public class LoginFrame extends JFrame {

    private UserManager userManager;
    private ProductManager productManager;

    public LoginFrame(UserManager userManager, ProductManager productManager) {

        this.userManager = userManager;
        this.productManager = productManager;

        setTitle("Shopix Login");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // USERNAME
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 30, 100, 25);
        add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(140, 30, 150, 25);
        add(usernameField);

        // PASSWORD
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 70, 100, 25);
        add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(140, 70, 150, 25);
        add(passwordField);

        // LOGIN BUTTON
        JButton loginButton = new JButton("Giriş Yap");
        loginButton.setBounds(50, 130, 100, 30);
        add(loginButton);

        // REGISTER BUTTON
        JButton registerButton = new JButton("Kayıt Ol");
        registerButton.setBounds(170, 130, 100, 30);
        add(registerButton);

        // LOGIN ACTION
        loginButton.addActionListener(e -> {

            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            User user = userManager.login(username, password);

            if (user != null) {
                JOptionPane.showMessageDialog(this, "Giriş başarılı!");

                // 🔥 ADMIN / CUSTOMER AYRIMI
                if (user instanceof Admin) {
                    new AdminFrame(productManager);
                } else {
                    new ProductFrame(productManager);
                }

                dispose(); // login ekranını kapat

            } else {
                JOptionPane.showMessageDialog(this, "Hatalı giriş!");
            }
        });

        // REGISTER ACTION
        registerButton.addActionListener(e -> {
            new RegisterFrame(userManager);
        });

        setVisible(true);
    }
}