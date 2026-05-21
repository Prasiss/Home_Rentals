package com.HomeRental.utils;

import java.util.regex.Pattern;

public class ValidationUtil {

    /**
     * Checks if a string is not null and not empty after trimming.
     * @param name Input string to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidString(String name) {
        return name != null && !name.trim().isEmpty();
    }

    /**
     * Validates Nepali phone number format (starts with 98 and has 10 digits).
     * @param phoneNumber Phone number to validate
     * @return true if valid, false otherwise
     */
    public static boolean validPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("^98\\d{8}$");
    }

    /**
     * Validates password strength (min 8 chars, 1 uppercase, 1 digit, 1 special character).
     * @param password Password to validate
     * @return true if valid, false otherwise
     */
    public static boolean validPassword(String password) {
        String passwordPattern = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password != null && password.matches(passwordPattern);
    }

    /**
     * Checks whether password and confirm password match.
     * @param password Password
     * @param confirmPassword Confirmation password
     * @return true if both match, false otherwise
     */
    public static boolean matchPassword(String password, String confirmPassword) {
        return password != null && password.equals(confirmPassword);
    }

    /**
     * Validates email format using regex pattern.
     * @param email Email address to validate
     * @return true if valid, false otherwise
     */
    public static boolean validEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email != null && Pattern.matches(emailRegex, email);
    }
}