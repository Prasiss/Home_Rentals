package com.HomeRentals.service;

import com.HomeRentals.dao.HomeRentalsDAO;
import com.HomeRentals.model.User;

public class LoginService {

    private HomeRentalsDAO dao;

    public LoginService() {
        dao = new HomeRentalsDAO();
    }

    public User authenticate(String username, String password) {
        try {
            User user = dao.getUserByUsername(username);
            if (user != null && password.equals(user.getPassword())) {
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}