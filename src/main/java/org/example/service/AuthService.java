package org.example.service;

import org.example.dao.UserDao;
import org.example.model.User;

public class AuthService {

    private final UserDao userDao = new UserDao();
    private User currentUser;

    public boolean login(String username, String password) {
        User user = userDao.login(username, password);
        if (user != null) {
            currentUser = user;
            return true;
        }
        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
