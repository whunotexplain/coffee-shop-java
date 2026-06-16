package app.controllers.user;

import handlers.CartHandler;
import models.CartItem;
import models.Product;

import java.util.List;

public class CartController {

    private final CartHandler
            cartHandler =
            new CartHandler();

    public void addProduct(
            Product product) {

        cartHandler.addProduct(
                product
        );
    }

    public void removeProduct(
            int productId) {

        cartHandler.removeProduct(
                productId
        );
    }

    public List<CartItem> getCart() {

        return cartHandler.getCart();
    }

    public double getTotalPrice() {

        return cartHandler
                .getTotalPrice();
    }

    public void clearCart() {

        cartHandler.clearCart();
    }
}