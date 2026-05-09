package shopix;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.GridLayout;

public class LoginFrame extends JFrame {
    private UserManager userManager = new UserManager();

    public LoginFrame() {
        setTitle("Shopix - Giriş ve Kayıt");
        setSize(350, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));
        setLocationRelativeTo(null);
        setResizable(false);

        // --- INPUT ALANLARI ---
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton loginBtn = new JButton("Giriş Yap");
        JButton registerBtn = new JButton("Kayıt Ol");

        // --- UI EKLEME ---
        add(new JLabel("  Ad Soyad:"));
        add(nameField);
        add(new JLabel("  E-posta:"));
        add(emailField);
        add(new JLabel("  Kullanıcı Adı:"));
        add(usernameField);
        add(new JLabel("  Şifre:"));
        add(passwordField);
        add(loginBtn);
        add(registerBtn);

        // =========================
        // GİRİŞ YAP (LOGIN)
        // =========================
        loginBtn.addActionListener(e -> {
            try {
                String user = usernameField.getText();
                String pass = new String(passwordField.getPassword()).trim();

                if (user.isEmpty() || pass.isEmpty()) {
                    throw new Exception("Kullanıcı adı ve şifre boş olamaz!");
                }

                User u = userManager.login(user, pass);

                if (u == null) {
                    throw new Exception("Hatalı kullanıcı adı veya şifre!");
                }

                // ROL KONTROLÜ: Veritabanından gelen role göre ekran açılır
                if ("ADMIN".equalsIgnoreCase(u.getRole())) {
                    new AdminFrame();
                } else {
                    new MainScreen(u);
                }

                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Giriş Hatası", JOptionPane.ERROR_MESSAGE);
            }
        });

        // =========================
        // KAYIT OL (REGISTER)
        // =========================
        registerBtn.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String email = emailField.getText();
                String user = usernameField.getText();
                String pass = new String(passwordField.getPassword()).trim();

                if (user.isEmpty() || pass.isEmpty()) {
                    throw new Exception("Kullanıcı adı ve şifre zorunludur!");
                }

                // Varsayılan değerler
                if (email.isEmpty()) email = "eposta@yok.com";
                if (name.isEmpty()) name = "İsimsiz Kullanıcı";

                // Yeni kullanıcıyı "CUSTOMER" rolüyle oluşturuyoruz
                User newUser = new User(0,name, email, user, pass, "CUSTOMER");

                boolean result = userManager.register(newUser);

                if (!result) {
                    throw new Exception("Kayıt başarısız! Bu kullanıcı adı zaten alınmış.");
                }

                JOptionPane.showMessageDialog(this, "Kayıt başarıyla oluşturuldu!");

                // Alanları temizle
                nameField.setText("");
                emailField.setText("");
                usernameField.setText("");
                passwordField.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Kayıt Hatası", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}