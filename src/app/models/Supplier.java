package app.models;

public class Supplier {

    private int id;
    private String name;
    private String phone;
    private String email;

    public Supplier(int id,
                    String name,
                    String phone,
                    String email) {

        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // ДОБАВЬТЕ ЭТИ МЕТОДЫ:
    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}