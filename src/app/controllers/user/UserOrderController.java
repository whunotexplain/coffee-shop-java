package app.controllers.user;

import handlers.OrderHandler;
import models.CartItem;

import java.util.List;

public class UserOrderController {

    private final OrderHandler
            orderHandler =
            new OrderHandler();

    public void createOrder(
            int userId,
            List<CartItem> items)
            throws Exception {

        orderHandler.createOrder(
                userId,
                items
        );
    }

    public double calculateTotal(
            List<CartItem> items) {

        return orderHandler
                .calculateTotal(
                        items
                );
    }
}