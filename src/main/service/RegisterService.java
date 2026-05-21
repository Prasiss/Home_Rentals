package com.HomeRental.service;

import com.HomeRental.dao.HomeRentalDAO;
import com.HomeRental.utils.PasswordUtil;

public class RegisterService {

    HomeRentalDAO dao = new HomeRentalDAO();

    /**
     * Registers a user without profile image.
     *
     * @param fullName Full name of user
     * @param username Username
     * @param email Email address
     * @param number Phone number
     * @param password Plain text password
     * @return true if registration is successful
     * @throws Exception if DAO operation fails
     */
    public boolean addUser(String fullName, String username,
                           String email, String number, String password) throws Exception {
        return addUser(fullName, username, email, number, password, null);
    }

    /**
     * Registers a user with optional profile image key.
     * Password is hashed before storing in database.
     *
     * @param fullName Full name of user
     * @param username Username
     * @param email Email address
     * @param number Phone number
     * @param password Plain text password
     * @param profileImageKey Profile image filename (optional)
     * @return true if registration is successful
     * @throws Exception if DAO operation fails
     */
    public boolean addUser(String fullName, String username,
                           String email, String number,
                           String password, String profileImageKey) throws Exception {

        password = PasswordUtil.getHashPassword(password);
        return dao.insertUser(fullName, username, email, number, password);
    }
}