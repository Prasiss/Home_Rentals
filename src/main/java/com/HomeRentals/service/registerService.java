package com.HomeRentals.service;

import com.HomeRentals.dao.HomeRentalsDAO;

public class RegisterService {

    private HomeRentalsDAO dao;

    public RegisterService() {
        dao = new HomeRentalsDAO();
    }

    public boolean addUser(String fullName, String username, String email,
                           String number, String password) {
        try {
            return dao.insertUser(fullName, username, email, number, password);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}