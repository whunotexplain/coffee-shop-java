package app.controllers.user;

import app.handlers.ProductHandler;
import app.models.Product;

import java.util.List;

public class MenuController {

    private final ProductHandler
            productHandler =
            new ProductHandler();

    public List<Product> getMenu() {

        return productHandler
                .getAllProducts();
    }
}