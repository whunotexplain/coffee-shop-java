package app.views;

import app.controllers.admin.AdminDashboardController;

import javax.swing.*;
import java.awt.*;

public class AdminFrame extends JFrame {

    public AdminFrame() {

        setTitle("Панель администратора");
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {

        AdminDashboardController dashboard =
                new AdminDashboardController();

        JPanel panel =
                new JPanel(
                        new GridLayout(3,1)
                );

        panel.add(
                new JLabel(
                        "Пользователей: "
                                + dashboard.getUsersCount()
                )
        );

        panel.add(
                new JLabel(
                        "Товаров: "
                                + dashboard.getProductsCount()
                )
        );

        panel.add(
                new JLabel(
                        "Заказов: "
                                + dashboard.getOrdersCount()
                )
        );

        add(panel);
    }
}