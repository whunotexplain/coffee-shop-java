package app.database;

import models.CartItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class OrderDAO {

    public void createOrder(
            int userId,
            List<CartItem> items)
            throws Exception {

        Connection con =
                DatabaseConnection.getConnection();

        try {

            con.setAutoCommit(false);

            double total =
                    items.stream()
                            .mapToDouble(
                                    CartItem::getTotal
                            )
                            .sum();

            String orderSql =
                    """
                    INSERT INTO orders
                    (user_id,total_amount,status)
                    VALUES(?,?,?)
                    RETURNING id
                    """;

            PreparedStatement orderPs =
                    con.prepareStatement(
                            orderSql
                    );

            orderPs.setInt(
                    1,
                    userId
            );

            orderPs.setDouble(
                    2,
                    total
            );

            orderPs.setString(
                    3,
                    "CREATED"
            );

            ResultSet rs =
                    orderPs.executeQuery();

            rs.next();

            int orderId =
                    rs.getInt("id");

            String itemSql =
                    """
                    INSERT INTO order_items
                    (order_id,
                     product_id,
                     quantity,
                     price)
                    VALUES(?,?,?,?)
                    """;

            for(CartItem item : items) {

                PreparedStatement ps =
                        con.prepareStatement(
                                itemSql
                        );

                ps.setInt(
                        1,
                        orderId
                );

                ps.setInt(
                        2,
                        item.getProduct()
                                .getId()
                );

                ps.setInt(
                        3,
                        item.getQuantity()
                );

                ps.setDouble(
                        4,
                        item.getProduct()
                                .getPrice()
                );

                ps.executeUpdate();

                PreparedStatement stock =
                        con.prepareStatement(
                                """
                                UPDATE products
                                SET quantity =
                                quantity - ?
                                WHERE id = ?
                                """
                        );

                stock.setInt(
                        1,
                        item.getQuantity()
                );

                stock.setInt(
                        2,
                        item.getProduct()
                                .getId()
                );

                stock.executeUpdate();
            }

            con.commit();

        } catch(Exception e) {

            con.rollback();

            throw e;

        } finally {

            con.close();
        }
    }
}