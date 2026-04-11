package com.HomeRentals.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconfig {
    private static final String URL = "jdbc:mysql://localhost:3306/HomeRentals";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    public static Connection getConnection() {

        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("âœ… Connected to DB");

        } catch (Exception e) {
            System.out.println("â�Œ DB Connection Failed");
            e.printStackTrace(); // ðŸ‘‰ THIS will show REAL problem
        }

        return conn;
    }

}
