package app.views;

import app.controllers.user.CartController;
import app.models.CartItem;
import app.models.Product;

import javax.swing.*;
import java.awt.*;

public class CartFrame extends JFrame {

    private final CartController cartController;
    private DefaultListModel<String> model;   // <-- стало полем
    private JLabel totalLabel;                // <-- стало полем

    public CartFrame(CartController cartController) {
        this.cartController = cartController;

        setTitle("Корзина");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        try {
            initComponents();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Ошибка загрузки корзины: " + e.getMessage(),
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
            add(new JLabel("Не удалось загрузить корзину", SwingConstants.CENTER));
        }
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // Список товаров в корзине
        model = new DefaultListModel<>();          // <-- теперь поле
        JList<String> list = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(list);
        add(scrollPane, BorderLayout.CENTER);

        // Нижняя панель с итогом и кнопками
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));

        // Итоговая сумма
        totalLabel = new JLabel("Итого: 0.0 ₽", SwingConstants.CENTER);  // <-- теперь поле
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        bottomPanel.add(totalLabel, BorderLayout.CENTER);

        // Кнопки управления
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton clearButton = new JButton("Очистить корзину");
        clearButton.addActionListener(e -> {
            cartController.clearCart();
            updateCart();                        // <-- без параметров
        });
        buttonPanel.add(clearButton);

        // Новая кнопка "Оформить заказ"
        JButton orderButton = new JButton("Оформить заказ");
        orderButton.setBackground(new Color(50, 200, 50));
        orderButton.setForeground(Color.WHITE);
        orderButton.setFont(new Font("Arial", Font.BOLD, 14));
        orderButton.addActionListener(e -> placeOrder());
        buttonPanel.add(orderButton);

        JButton closeButton = new JButton("Закрыть");
        closeButton.addActionListener(e -> dispose());
        buttonPanel.add(closeButton);

        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

        // Загружаем данные корзины
        updateCart();                            // <-- без параметров
    }

    // Теперь метод без параметров, использует поля класса
    private void updateCart() {
        model.clear();

        try {
            if (cartController == null) {
                model.addElement("Ошибка: контроллер корзины не инициализирован");
                totalLabel.setText("Итого: 0.0 ₽");
                return;
            }

            java.util.List<CartItem> cartItems = cartController.getCart();
            if (cartItems == null || cartItems.isEmpty()) {
                model.addElement("Корзина пуста");
                totalLabel.setText("Итого: 0.0 ₽");
                return;
            }

            double total = 0.0;
            for (CartItem item : cartItems) {
                if (item == null) continue;

                Product product = item.getProduct();
                String productName = (product != null) ? product.getName() : "Товар без названия";
                int quantity = item.getQuantity();
                double itemTotal = item.getTotal();

                model.addElement(productName + " x " + quantity + " = " + itemTotal + " ₽");
                total += itemTotal;
            }

            totalLabel.setText("Итого: " + total + " ₽");

        } catch (Exception e) {
            e.printStackTrace();
            model.addElement("Ошибка при загрузке корзины: " + e.getMessage());
            totalLabel.setText("Итого: 0.0 ₽");
        }
    }

    private void placeOrder() {
        try {
            var currentUser = app.authorization.SessionManager.getCurrentUser();
            if (currentUser == null) {
                JOptionPane.showMessageDialog(this,
                        "Пожалуйста, войдите в систему",
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            var items = cartController.getCart();
            if (items == null || items.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Корзина пуста",
                        "Ошибка",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Оформить заказ на сумму " + cartController.getTotalPrice() + " ₽?",
                    "Подтверждение",
                    JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            app.database.OrderDAO orderDAO = new app.database.OrderDAO();
            orderDAO.createOrder(currentUser.getId(), items);

            JOptionPane.showMessageDialog(this,
                    "Заказ успешно оформлен! 🎉",
                    "Успех",
                    JOptionPane.INFORMATION_MESSAGE);

            cartController.clearCart();
            updateCart();   // <-- теперь работает

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Ошибка при оформлении заказа: " + e.getMessage(),
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}