package app.handlers;

import app.database.OrderDAO;
import app.models.CartItem;

import java.util.List;

public class OrderHandler {

    private final OrderDAO orderDAO =
            new OrderDAO();

    public void createOrder(
            int userId,
            List<CartItem> items)
            throws Exception {

        if(items.isEmpty()) {

            throw new Exception(
                    "Корзина пустая"
            );
        }

        orderDAO.createOrder(
                userId,
                items
        );
    }

    public double calculateTotal(
            List<CartItem> items) {

        return items.stream()
                .mapToDouble(
                        CartItem::getTotal
                )
                .sum();
    }
}