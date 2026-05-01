package shopix;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame{
	private UserManager userManager = new UserManager();

    public LoginFrame() {
        setTitle("Shopix - Giriş");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,2));

        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();

        JButton loginBtn = new JButton("Giriş");
        JButton registerBtn = new JButton("Kayıt Ol");

        add(new JLabel("Username:"));
        add(username);
        add(new JLabel("Password:"));
        add(password);
        add(loginBtn);
        add(registerBtn);

        // LOGIN
        loginBtn.addActionListener(e -> {User u = userManager.login(username.getText(), new String(password.getPassword()));
            if (u != null) {
            	// ADMIN ise
                if (u instanceof Admin) {
                    new AdminFrame();
                }
                // NORMAL USER ise
                else {
                    new MainScreen(u);
                }dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Hatalı giriş!");
            }
        });

        // REGISTER
        registerBtn.addActionListener(e -> {
            User newUser = new User(
                userManager.getUsers().size()+1,
                "User",
                "mail@test.com",
                username.getText(),
                new String(password.getPassword())
            );
            userManager.register(newUser);
        });

        setVisible(true);
    }
}