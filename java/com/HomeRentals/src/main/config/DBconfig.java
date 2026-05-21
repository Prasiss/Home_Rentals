package com.HomeRental.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconfig {

    private static final String URL = "jdbc:mysql://localhost:3306/HomeRentals";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /**
     * Establishes and returns a connection to the MySQL database.
     * Loads the MySQL JDBC driver and connects using predefined credentials.
     *
     * @return Connection object if successful, otherwise null
     */
    public static Connection getConnection() {

        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println(" Connected to DB");

        } catch (Exception e) {
            System.out.println(" DB Connection Failed");
            e.printStackTrace();
        }

        return conn;
    }
}