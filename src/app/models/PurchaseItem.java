package app.models;

public class PurchaseItem {

    private int id;
    private int purchaseId;
    private int productId;
    private int quantity;
    private double cost;

    public PurchaseItem(int id,
                        int purchaseId,
                        int productId,
                        int quantity,
                        double cost) {

        this.id = id;
        this.purchaseId = purchaseId;
        this.productId = productId;
        this.quantity = quantity;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public int getProductId() {
        return productId;
    }
}