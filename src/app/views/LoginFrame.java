package views;

import authorization.Role;
import authorization.SessionManager;
import models.User;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame() {

        setTitle("Coffee House - Авторизация");
        setSize(400,250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {

        JPanel panel =
                new JPanel(
                        new GridLayout(3,2,10,10)
                );

        panel.add(new JLabel("Логин"));

        loginField =
                new JTextField();

        panel.add(loginField);

        panel.add(new JLabel("Пароль"));

        passwordField =
                new JPasswordField();

        panel.add(passwordField);

        loginButton =
                new JButton("Войти");

        panel.add(loginButton);

        add(panel);

        loginButton.addActionListener(e -> login());
    }

    private void login() {

        try {

            AuthService authService =
                    new AuthService();

            User user =
                    authService.login(
                            loginField.getText(),
                            new String(
                                    passwordField.getPassword()
                            )
                    );

            if(user == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Неверный логин или пароль"
                );

                return;
            }

            SessionManager.setCurrentUser(
                    user
            );

            dispose();

            if(user.getRole() ==
                    Role.ADMIN) {

                new AdminFrame()
                        .setVisible(true);

            } else {

                new MenuFrame()
                        .setVisible(true);
            }

        } catch(Exception ex) {

            ex.printStackTrace();
        }
    }
}