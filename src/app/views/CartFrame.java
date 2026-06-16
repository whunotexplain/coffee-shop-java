package views;

import controllers.user.CartController;
import models.CartItem;

import javax.swing.*;
import java.awt.*;

public class CartFrame extends JFrame {

    private final CartController cartController;

    public CartFrame(
            CartController cartController) {

        this.cartController =
                cartController;

        setTitle("Корзина");
        setSize(500,400);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {

        DefaultListModel<String> model =
                new DefaultListModel<>();

        for(CartItem item :
                cartController.getCart()) {

            model.addElement(
                    item.getProduct()
                            .getName()
                            + " x "
                            + item.getQuantity()
                            + " = "
                            + item.getTotal()
            );
        }

        JList<String> list =
                new JList<>(model);

        JLabel total =
                new JLabel(
                        "Итого: "
                                + cartController
                                .getTotalPrice()
                );

        add(
                new JScrollPane(list),
                BorderLayout.CENTER
        );

        add(
                total,
                BorderLayout.SOUTH
        );
    }
}