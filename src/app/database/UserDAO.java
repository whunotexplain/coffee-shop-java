package app.database;

import authorization.Role;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public User findByCredentials(
            String username,
            String passwordHash) {

        String sql =
                """
                SELECT *
                FROM users
                WHERE username = ?
                AND password_hash = ?
                """;

        try(Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, passwordHash);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        Role.valueOf(
                                rs.getString("role")
                        )
                );
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}