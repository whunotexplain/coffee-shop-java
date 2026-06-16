package app.controllers.admin;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminDashboardController {

    public int getProductsCount() {

        return count("products");
    }

    public int getOrdersCount() {

        return count("orders");
    }

    public int getUsersCount() {

        return count("users");
    }

    private int count(
            String table) {

        try(Connection con =
                    DatabaseConnection.getConnection();

            Statement st =
                    con.createStatement();

            ResultSet rs =
                    st.executeQuery(
                            "SELECT COUNT(*) FROM "
                                    + table
                    )) {

            rs.next();

            return rs.getInt(1);

        } catch(Exception e) {

            e.printStackTrace();
        }

        return 0;
    }
}