package com.HomeRental.service;

import com.HomeRental.utils.PasswordUtil;
import com.HomeRental.model.UserModel;
import com.HomeRental.dao.HomeRentalDAO;

public class LoginService {

    /**
     * Authenticates user by username and password.
     * Checks user existence and verifies hashed password.
     *
     * @param username Username entered by user
     * @param password Plain text password entered by user
     * @return UserModel if authentication succeeds, otherwise null
     */
    public UserModel authenticate(String username, String password) {

        if (username == null || username.trim().isEmpty()) {
            return null;
        }
        if (password == null || password.trim().isEmpty()) {
            return null;
        }

        HomeRentalDAO dao = new HomeRentalDAO();

        try {
            UserModel user = dao.getUserByUsername(username);

            if (user == null) {
                System.out.println("User not found in DB");
                return null;
            }

            boolean isMatch = PasswordUtil.checkPassword(password, user.getPassword());

            if (!isMatch) {
                return null;
            }

            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Checks whether a user account is active.
     *
     * @param userId User ID
     * @return true if active, false otherwise
     */
    public boolean isActive(int userId) {
        HomeRentalDAO dao = new HomeRentalDAO();
        return dao.isActive(userId);
    }
}