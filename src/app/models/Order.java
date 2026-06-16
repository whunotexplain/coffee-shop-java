package app.models;

import java.time.LocalDateTime;

public class Order {

    private int id;
    private int userId;
    private double totalAmount;
    private String status;
    private LocalDateTime orderDate;

    public Order(int id,
                 int userId,
                 double totalAmount,
                 String status,
                 LocalDateTime orderDate) {

        this.id = id;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.orderDate = orderDate;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }
}