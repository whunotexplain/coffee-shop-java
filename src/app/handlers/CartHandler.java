package app.handlers;

import app.models.CartItem;
import app.models.Product;

import java.util.ArrayList;
import java.util.List;

public class CartHandler {

    private final List<CartItem> cart =
            new ArrayList<>();

    public void addProduct(Product product) {

        for(CartItem item : cart) {

            if(item.getProduct().getId()
                    == product.getId()) {

                return;
            }
        }

        cart.add(
                new CartItem(product, 1)
        );
    }

    public void removeProduct(
            int productId) {

        cart.removeIf(
                item ->
                        item.getProduct()
                                .getId()
                                == productId
        );
    }

    public List<CartItem> getCart() {

        return cart;
    }

    public void clearCart() {

        cart.clear();
    }

    public double getTotalPrice() {

        return cart.stream()
                .mapToDouble(
                        CartItem::getTotal
                )
                .sum();
    }
}