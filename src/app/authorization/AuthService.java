package app.authorization;

import app.models.User;
import app.authorization.Role;
import app.database.UserDAO;

public class AuthService {

    private final UserDAO userDAO = new UserDAO();

    public User login(String login, String password) {
        // Используем правильный метод
        return userDAO.findByCredentials(login, password);   // <- исправлено
    }
}