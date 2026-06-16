package app.controllers.admin;

import handlers.ProductHandler;
import models.Product;

import java.util.List;

public class ProductController {

    private final ProductHandler productHandler =
            new ProductHandler();

    public List<Product> getProducts() {

        return productHandler.getAllProducts();
    }

    public void addProduct(
            String name,
            String description,
            double price,
            int quantity,
            int categoryId)
            throws Exception {

        Product product =
                new Product(
                        0,
                        name,
                        description,
                        price,
                        quantity,
                        categoryId
                );

        productHandler.addProduct(product);
    }

    public void deleteProduct(
            int productId)
            throws Exception {

        productHandler.deleteProduct(productId);
    }

    public void addStock(
            int productId,
            int quantity)
            throws Exception {

        productHandler.addStock(
                productId,
                quantity
        );
    }
}