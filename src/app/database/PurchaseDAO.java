package app.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PurchaseDAO {

    public void createPurchase(
            int supplierId,
            int productId,
            int quantity,
            double cost)
            throws Exception {

        Connection con =
                DatabaseConnection.getConnection();

        try {

            con.setAutoCommit(false);

            String purchaseSql =
                    """
                    INSERT INTO purchases
                    (supplier_id,total_amount)
                    VALUES(?,?)
                    RETURNING id
                    """;

            PreparedStatement purchasePs =
                    con.prepareStatement(
                            purchaseSql
                    );

            purchasePs.setInt(
                    1,
                    supplierId
            );

            purchasePs.setDouble(
                    2,
                    cost
            );

            ResultSet rs =
                    purchasePs.executeQuery();

            rs.next();

            int purchaseId =
                    rs.getInt("id");

            String itemSql =
                    """
                    INSERT INTO purchase_items
                    (purchase_id,
                     product_id,
                     quantity,
                     cost)
                    VALUES(?,?,?,?)
                    """;

            PreparedStatement itemPs =
                    con.prepareStatement(
                            itemSql
                    );

            itemPs.setInt(
                    1,
                    purchaseId
            );

            itemPs.setInt(
                    2,
                    productId
            );

            itemPs.setInt(
                    3,
                    quantity
            );

            itemPs.setDouble(
                    4,
                    cost
            );

            itemPs.executeUpdate();

            PreparedStatement stock =
                    con.prepareStatement(
                            """
                            UPDATE products
                            SET quantity =
                            quantity + ?
                            WHERE id = ?
                            """
                    );

            stock.setInt(
                    1,
                    quantity
            );

            stock.setInt(
                    2,
                    productId
            );

            stock.executeUpdate();

            con.commit();

        } catch(Exception e) {

            con.rollback();

            throw e;

        } finally {

            con.close();
        }
    }
}