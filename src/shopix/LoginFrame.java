package shopix;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private UserManager userManager = new UserManager();

    public LoginFrame() {
        setTitle("Shopix - Giriş ve Kayıt");
        setSize(350, 300); // Biraz daha genişlettik
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // 5 satır, 2 sütun düzeni
        setLayout(new GridLayout(5, 2, 10, 10)); 

        // Giriş Alanları
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton loginBtn = new JButton("Giriş Yap");
        JButton registerBtn = new JButton("Kayıt Ol");

        // Arayüze Ekleme Sırası
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

        // --- GİRİŞ BUTONU ---
        loginBtn.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());
            
            User u = userManager.login(user, pass);
            if (u != null) {
                if (u instanceof Admin) {
                    new AdminFrame();
                } else {
                    new MainScreen(u);
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Hatalı kullanıcı adı veya şifre!");
            }
        });

        // --- KAYIT BUTONU ---
        registerBtn.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Kullanıcı adı ve şifre boş bırakılamaz!");
                return;
            }

            // ID kısmına 0 veriyoruz, SQLite bunu otomatik artıracak
            User newUser = new User(
                0, 
                name.isEmpty() ? "İsimsiz Kullanıcı" : name,
                email.isEmpty() ? "eposta@yok.com" : email,
                user,
                pass
            );
            
            userManager.register(newUser);
            JOptionPane.showMessageDialog(this, "Kaydınız başarıyla oluşturuldu!");
            
            // Kayıttan sonra alanları temizle
            nameField.setText("");
            emailField.setText("");
            usernameField.setText("");
            passwordField.setText("");
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}