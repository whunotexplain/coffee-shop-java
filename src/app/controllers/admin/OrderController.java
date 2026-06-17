package app.controllers.admin;

import app.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderController {

    public List<String> getOrders() {

        List<String> orders =
                new ArrayList<>();

        try(Connection con =
                    DatabaseConnection.getConnection();

            Statement st =
                    con.createStatement();

            ResultSet rs =
                    st.executeQuery(
                            """
                            SELECT *
                            FROM orders
                            ORDER BY id DESC
                            """
                    )) {

            while(rs.next()) {

                orders.add(
                        "Заказ #" +
                                rs.getInt("id") +
                                " | Пользователь: " +
                                rs.getInt("user_id") +
                                " | Сумма: " +
                                rs.getDouble("total_amount") +
                                " | Статус: " +
                                rs.getString("status")
                );
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return orders;
    }
}