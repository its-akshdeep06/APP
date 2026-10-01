import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserLogin extends JFrame {

    JTextField username;
    JPasswordField password;
    JCheckBox rememberMe, notifications;
    JButton login;

    UserLogin() {

        setTitle("User Login");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2, 10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Username
        add(new JLabel("Username:"));
        username = new JTextField();
        add(username);

        // Password
        add(new JLabel("Password:"));
        password = new JPasswordField();
        add(password);

        // Remember Me
        add(new JLabel("Preferences:"));
        rememberMe = new JCheckBox("Remember Me");
        add(rememberMe);

        // Receive Notifications
        add(new JLabel(""));
        notifications = new JCheckBox("Receive Notifications");
        add(notifications);

        // Login Button
        add(new JLabel(""));
        login = new JButton("Login");
        add(login);

        // Login Button Action
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String user = username.getText();
                String pass = new String(password.getPassword());

                if (user.equals("admin") && pass.equals("1234")) {

                    String message = "Login Successful!\n"
                            + "Welcome, " + user + "\n"
                            + "Remember Me: "
                            + (rememberMe.isSelected() ? "Yes" : "No") + "\n"
                            + "Receive Notifications: "
                            + (notifications.isSelected() ? "Yes" : "No");

                    JOptionPane.showMessageDialog(
                            UserLogin.this,
                            message,
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            UserLogin.this,
                            "Invalid Username or Password!",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new UserLogin();
    }
}
