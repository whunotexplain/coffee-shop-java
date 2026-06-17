package app.database;

import app.authorization.Role;
import app.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public User findByCredentials(String username, String passwordHash) {
        String sql = """
        SELECT *
        FROM users
        WHERE username = ?
        AND password_hash = ?
        """;

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, passwordHash);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String roleStr = rs.getString("role");
                if (roleStr == null || roleStr.isBlank()) {
                    return null;
                }

                Role role = Role.USER; // <-- ИНИЦИАЛИЗАЦИЯ ПО УМОЛЧАНИЮ
                try {
                    role = Role.valueOf(roleStr.toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.err.println("Неизвестная роль: " + roleStr);
                    // role остаётся USER
                }

                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        role  // <-- теперь точно инициализирована
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}