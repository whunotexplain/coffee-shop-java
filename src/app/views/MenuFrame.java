package views;

import controllers.user.MenuController;
import models.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuFrame extends JFrame {

    private JList<String> productsList;

    public MenuFrame() {

        setTitle("Меню кофейни");
        setSize(700,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {

        MenuController controller =
                new MenuController();

        List<Product> products =
                controller.getMenu();

        DefaultListModel<String> model =
                new DefaultListModel<>();

        for(Product product : products) {

            model.addElement(
                    product.getId()
                            + " | "
                            + product.getName()
                            + " | "
                            + product.getPrice()
                            + " ₽"
            );
        }

        productsList =
                new JList<>(model);

        add(
                new JScrollPane(productsList),
                BorderLayout.CENTER
        );
    }
}