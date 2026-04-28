package shopix;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    private UserManager userManager;

    public RegisterFrame(UserManager userManager) {
        this.userManager = userManager;

        setTitle("Shopix - Kayıt Ol");
        setSize(350, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton registerButton = new JButton("Kayıt Ol");

        add(new JLabel("İsim:"));
        add(nameField);
        add(new JLabel("E-mail:"));
        add(emailField);
        add(new JLabel("Kullanıcı Adı:"));
        add(usernameField);
        add(new JLabel("Şifre:"));
        add(passwordField);
        add(new JLabel(""));
        add(registerButton);

        registerButton.addActionListener(e -> {
            int id = (int) (Math.random() * 10000);

            Customer customer = new Customer(
                    id,
                    nameField.getText(),
                    emailField.getText(),
                    usernameField.getText(),
                    new String(passwordField.getPassword())
            );

            boolean success = userManager.register(customer);

            if (success) {
                JOptionPane.showMessageDialog(this, "Kayıt başarılı!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Bu kullanıcı adı zaten var!");
            }
        });

        setVisible(true);
    }
}