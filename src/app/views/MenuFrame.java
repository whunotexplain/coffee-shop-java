package app.views;

import app.authorization.SessionManager;
import app.controllers.user.MenuController;
import app.controllers.user.CartController;
import app.models.User;
import app.models.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuFrame extends JFrame {

    private final MenuController menuController = new MenuController();
    private final CartController cartController = new CartController(); // <-- добавлен контроллер корзины

    public MenuFrame() {
        setTitle("Меню пользователя");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        User currentUser = SessionManager.getCurrentUser();
        String welcomeText = (currentUser != null)
                ? "Добро пожаловать, " + currentUser.getUsername() + "!"
                : "Добро пожаловать!";

        setLayout(new BorderLayout(10, 10));

        // Верхняя панель с приветствием и кнопкой выхода
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel(welcomeText, SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(welcomeLabel, BorderLayout.CENTER);

        JButton logoutButton = new JButton("Выйти");
        logoutButton.addActionListener(e -> {
            SessionManager.logout();
            dispose();
            new LoginFrame().setVisible(true);
        });
        topPanel.add(logoutButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // Центральная панель с товарами (загружаем из БД)
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        try {
            // Загружаем список товаров из базы
            List<Product> products = menuController.getMenu();
            if (products == null || products.isEmpty()) {
                JLabel emptyLabel = new JLabel("Меню пусто. Добавьте товары через панель администратора.", SwingConstants.CENTER);
                centerPanel.add(emptyLabel);
            } else {
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.insets = new Insets(5, 5, 5, 5);

                for (Product product : products) {
                    JButton productButton = new JButton(product.getName() + " - " + product.getPrice() + " ₽");

                    // РЕАЛЬНАЯ ЛОГИКА ДОБАВЛЕНИЯ В КОРЗИНУ
                    productButton.addActionListener(e -> {
                        cartController.addProduct(product);
                        JOptionPane.showMessageDialog(this,
                                "Товар '" + product.getName() + "' добавлен в корзину!",
                                "Успешно",
                                JOptionPane.INFORMATION_MESSAGE);
                    });

                    centerPanel.add(productButton, gbc);
                    gbc.gridy++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JLabel errorLabel = new JLabel("Не удалось загрузить меню: " + e.getMessage(), SwingConstants.CENTER);
            errorLabel.setForeground(Color.RED);
            centerPanel.add(errorLabel);
        }

        JScrollPane scrollPane = new JScrollPane(centerPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Нижняя панель с корзиной
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cartButton = new JButton("Перейти в корзину");

        // ПЕРЕДАЁМ ТОТ ЖЕ КОНТРОЛЛЕР КОРЗИНЫ
        cartButton.addActionListener(e -> {
            new CartFrame(cartController).setVisible(true);
        });

        bottomPanel.add(cartButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}