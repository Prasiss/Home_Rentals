package com.HomeRentals.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {

    private static final SecureRandom random = new SecureRandom();

    public static String hashPassword(String plainPassword) {
        try {
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            String saltStr = Base64.getEncoder().encodeToString(salt);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashed = md.digest(plainPassword.getBytes());
            String hashStr = Base64.getEncoder().encodeToString(hashed);

            return saltStr + ":" + hashStr;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean checkPassword(String plainPassword, String storedHash) {
        // FIX: Guard against null — happens when the DB row has no password set
        if (plainPassword == null || storedHash == null) {
            return false;
        }

        try {
            String[] parts = storedHash.split(":");

            if (parts.length != 2) {
                // Plain-text fallback (e.g. manually inserted admin password)
                return plainPassword.equals(storedHash);
            }

            byte[] salt = Base64.getDecoder().decode(parts[0]);
            String originalHash = parts[1];

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashed = md.digest(plainPassword.getBytes());
            String newHash = Base64.getEncoder().encodeToString(hashed);

            return newHash.equals(originalHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }
}
