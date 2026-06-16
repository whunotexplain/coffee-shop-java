package app.models;

public class Product {

    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private int categoryId;

    public Product(int id,
                   String name,
                   String description,
                   double price,
                   int quantity,
                   int categoryId) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCategoryId() {
        return categoryId;
    }
}