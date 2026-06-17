package app.database;

import app.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<Product> getAllProducts() {

        List<Product> products =
                new ArrayList<>();

        String sql =
                "SELECT * FROM products ORDER BY id";

        try(Connection con =
                    DatabaseConnection.getConnection();

            Statement st =
                    con.createStatement();

            ResultSet rs =
                    st.executeQuery(sql)) {

            while(rs.next()) {

                products.add(
                        new Product(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getDouble("price"),
                                rs.getInt("quantity"),
                                rs.getInt("category_id")
                        )
                );
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    public void addProduct(Product product)
            throws Exception {

        String sql =
                """
                INSERT INTO products
                (name, description, price,
                 quantity, category_id)
                VALUES (?, ?, ?, ?, ?)
                """;

        try(Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql)) {

            ps.setString(
                    1,
                    product.getName()
            );

            ps.setString(
                    2,
                    product.getDescription()
            );

            ps.setDouble(
                    3,
                    product.getPrice()
            );

            ps.setInt(
                    4,
                    product.getQuantity()
            );

            ps.setInt(
                    5,
                    product.getCategoryId()
            );

            ps.executeUpdate();
        }
    }

    public void deleteProduct(int id)
            throws Exception {

        String sql =
                "DELETE FROM products WHERE id=?";

        try(Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql)) {

            ps.setInt(1,id);

            ps.executeUpdate();
        }
    }

    public void updateStock(
            int productId,
            int quantity)
            throws Exception {

        String sql =
                """
                UPDATE products
                SET quantity = quantity + ?
                WHERE id = ?
                """;

        try(Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setInt(2, productId);

            ps.executeUpdate();
        }
    }
}