package app.models;

import java.time.LocalDateTime;

public class Purchase {

    private int id;
    private int supplierId;
    private double totalAmount;
    private LocalDateTime purchaseDate;

    public Purchase(int id,
                    int supplierId,
                    double totalAmount,
                    LocalDateTime purchaseDate) {

        this.id = id;
        this.supplierId = supplierId;
        this.totalAmount = totalAmount;
        this.purchaseDate = purchaseDate;
    }

    public int getId() {
        return id;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}