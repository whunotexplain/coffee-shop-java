package app.handlers;

import database.PurchaseDAO;

public class PurchaseHandler {

    private final PurchaseDAO purchaseDAO =
            new PurchaseDAO();

    public void createPurchase(
            int supplierId,
            int productId,
            int quantity,
            double cost)
            throws Exception {

        if(quantity <= 0) {

            throw new Exception(
                    "Количество должно быть больше 0"
            );
        }

        if(cost <= 0) {

            throw new Exception(
                    "Стоимость должна быть больше 0"
            );
        }

        purchaseDAO.createPurchase(
                supplierId,
                productId,
                quantity,
                cost
        );
    }
}