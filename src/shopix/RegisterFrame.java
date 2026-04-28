package shopix;

import javax.swing.*;

public class RegisterFrame extends JFrame {

    private UserManager userManager;

    public RegisterFrame(UserManager userManager) {

        this.userManager = userManager;

        setTitle("Kayıt Ol");
        setSize(350, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel nameLabel = new JLabel("İsim:");
        nameLabel.setBounds(30, 20, 100, 25);
        add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(140, 20, 150, 25);
        add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(30, 60, 100, 25);
        add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(140, 60, 150, 25);
        add(emailField);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 100, 100, 25);
        add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(140, 100, 150, 25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 140, 100, 25);
        add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(140, 140, 150, 25);
        add(passwordField);

        JButton registerButton = new JButton("Kayıt Ol");
        registerButton.setBounds(110, 190, 120, 30);
        add(registerButton);

        registerButton.addActionListener(e -> {

            String name = nameField.getText();
            String email = emailField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            int id = (int) (Math.random() * 1000); // basit id üretme

            Customer newUser = new Customer(id, name, email, username, password);

            boolean success = userManager.register(newUser);

            if (success) {
                JOptionPane.showMessageDialog(this, "Kayıt başarılı!");
                dispose(); // pencereyi kapat
            } else {
                JOptionPane.showMessageDialog(this, "Kullanıcı adı zaten var!");
            }
        });

        setVisible(true);
    }
}