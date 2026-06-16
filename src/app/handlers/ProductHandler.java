package app.handlers;

import database.ProductDAO;
import models.Product;

import java.util.List;

public class ProductHandler {

    private final ProductDAO productDAO =
            new ProductDAO();

    public List<Product> getAllProducts() {

        return productDAO.getAllProducts();
    }

    public void addProduct(Product product)
            throws Exception {

        if(product.getPrice() <= 0) {
            throw new Exception(
                    "Цена должна быть больше 0"
            );
        }

        productDAO.addProduct(product);
    }

    public void deleteProduct(int productId)
            throws Exception {

        productDAO.deleteProduct(productId);
    }

    public void addStock(
            int productId,
            int quantity)
            throws Exception {

        if(quantity <= 0) {
            throw new Exception(
                    "Количество должно быть больше 0"
            );
        }

        productDAO.updateStock(
                productId,
                quantity
        );
    }
}