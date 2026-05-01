package com.HomeRentals.service;

import com.HomeRentals.dao.HomeRentalsDAO;
import com.HomeRentals.model.UserModel;
import com.HomeRentals.utils.PasswordUtil;

public class LoginService {

    private HomeRentalsDAO dao;

    public LoginService() {
        dao = new HomeRentalsDAO();
    }

    /**
     * Returns the user if credentials match AND account is ACTIVE.
     * Returns null if credentials are wrong.
     * Throws PendingAccountException if account is awaiting admin approval.
     * Throws SuspendedAccountException if account is suspended/inactive.
     */
    public UserModel authenticate(String username, String password) throws Exception {
        UserModel user = dao.getUserByUsername(username);

        if (user == null) {
            return null; // user not found
        }

        // FIX: Use PasswordUtil.checkPassword() instead of plain .equals()
        // checkPassword() handles both hashed passwords (salt:hash format)
        // AND plain-text passwords (fallback for legacy/manually inserted accounts)
        if (!PasswordUtil.checkPassword(password, user.getPassword())) {
            return null; // wrong password
        }

        // Block login based on account status
        switch (user.getStatus()) {
            case "PENDING":
                throw new PendingAccountException("Your account is awaiting admin approval. Please check back later.");
            case "SUSPENDED":
                throw new SuspendedAccountException("Your account has been suspended. Contact support.");
            case "INACTIVE":
                throw new SuspendedAccountException("Your account is inactive. Contact support.");
            default:
                break;
        }

        return user;
    }

    public static class PendingAccountException extends Exception {
        public PendingAccountException(String message) { super(message); }
    }

    public static class SuspendedAccountException extends Exception {
        public SuspendedAccountException(String message) { super(message); }
    }
}
