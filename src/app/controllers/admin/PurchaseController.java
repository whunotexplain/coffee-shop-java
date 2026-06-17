package app.controllers.admin;

import app.handlers.PurchaseHandler;

public class PurchaseController {

    private final PurchaseHandler
            purchaseHandler =
            new PurchaseHandler();

    public void createPurchase(
            int supplierId,
            int productId,
            int quantity,
            double cost)
            throws Exception {

        purchaseHandler.createPurchase(
                supplierId,
                productId,
                quantity,
                cost
        );
    }
}