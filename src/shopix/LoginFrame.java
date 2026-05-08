package shopix;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
	private UserManager userManager = new UserManager();

    public LoginFrame() {

        setTitle("Shopix - Giriş ve Kayıt");
        setSize(350, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));
        setLocationRelativeTo(null);
        setResizable(false);

        // =========================
        // INPUT ALANLARI
        // =========================
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton loginBtn = new JButton("Giriş Yap");
        JButton registerBtn = new JButton("Kayıt Ol");

        // =========================
        // UI EKLEME
        // =========================
        add(new JLabel("Ad Soyad:"));
        add(nameField);

        add(new JLabel("E-posta:"));
        add(emailField);

        add(new JLabel("Kullanıcı Adı:"));
        add(usernameField);

        add(new JLabel("Şifre:"));
        add(passwordField);

        add(loginBtn);
        add(registerBtn);

        // =========================
        // LOGIN
        // =========================
        loginBtn.addActionListener(e -> {
            try {

                String user = usernameField.getText();
                String pass = new String(passwordField.getPassword()).trim();

                if (user == null || user.trim().isEmpty() ||
                    pass == null || pass.trim().isEmpty()) {
                    throw new Exception("Kullanıcı adı ve şifre boş olamaz!");
                }

                User u = userManager.login(user, pass);

                if (u == null) {
                    throw new Exception("Hatalı kullanıcı adı veya şifre!");
                }

                if (u instanceof Admin) {
                    new AdminFrame();
                } else {
                    new MainScreen(u);
                }

                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Giriş Hatası",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // =========================
        // REGISTER
        // =========================
        registerBtn.addActionListener(e -> {
            try {

                String name = nameField.getText();
                String email = emailField.getText();
                String user = usernameField.getText();
                String pass = new String(passwordField.getPassword()).trim();

                // VALIDATION
                if (user == null || user.trim().isEmpty() ||
                    pass == null || pass.trim().isEmpty()) {
                    throw new Exception("Kullanıcı adı ve şifre zorunludur!");
                }

                if (email == null || email.trim().isEmpty()) {
                    email = "eposta@yok.com";
                }

                if (name == null || name.trim().isEmpty()) {
                    name = "İsimsiz Kullanıcı";
                }

                User newUser = new User(
                        0,
                        name,
                        email,
                        user,
                        pass
                );

                boolean result = userManager.register(newUser);

                if (!result) {
                    throw new Exception("Kayıt başarısız! Kullanıcı adı zaten var.");
                }

                JOptionPane.showMessageDialog(this,
                        "Kayıt başarıyla oluşturuldu!");

                // CLEAR FIELDS
                nameField.setText("");
                emailField.setText("");
                usernameField.setText("");
                passwordField.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Kayıt Hatası",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}