package com.HomeRentals.utils;

public class ValidationUtil {

    public static boolean isValidString(String str) {
        return str != null && !str.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        if (!isValidString(email)) return false;
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean isValidNumber(String number) {
        if (!isValidString(number)) return false;
        return number.matches("[0-9]+");
    }
}