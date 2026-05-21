package com.HomeRental.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    private static final int COST = 10;

    /**
     * Hashes a plain text password using BCrypt.
     * @param password Plain text password
     * @return Hashed password string
     */
    public static String getHashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(COST));
    }

    /**
     * Compares a plain password with a hashed password.
     * @param input Plain text password input
     * @param storedHash Stored hashed password
     * @return true if passwords match, false otherwise
     */
    public static boolean checkPassword(String input, String storedHash) {
        return BCrypt.checkpw(input, storedHash);
    }
}