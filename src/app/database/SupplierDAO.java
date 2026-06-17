package app.database;

import app.models.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {

    public List<Supplier> getAllSuppliers() {

        List<Supplier> suppliers =
                new ArrayList<>();

        String sql =
                "SELECT * FROM suppliers";

        try(Connection con =
                    DatabaseConnection.getConnection();

            Statement st =
                    con.createStatement();

            ResultSet rs =
                    st.executeQuery(sql)) {

            while(rs.next()) {

                suppliers.add(
                        new Supplier(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("phone"),
                                rs.getString("email")
                        )
                );
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return suppliers;
    }

    public void addSupplier(
            Supplier supplier)
            throws Exception {

        String sql =
                """
                INSERT INTO suppliers
                (name, phone, email)
                VALUES(?,?,?)
                """;

        try(Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql)) {

            ps.setString(
                    1,
                    supplier.getName()
            );

            ps.setString(
                    2,
                    supplier.getPhone()
            );

            ps.setString(
                    3,
                    supplier.getEmail()
            );

            ps.executeUpdate();
        }
    }
}