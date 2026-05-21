package com.HomeRental.dao;

public class HomeRentalsDAO {
    public boolean insertUser(String fullName, String username,
            String email, String number, String password) throws Exception {

        Connection con = DBconfig.getConnection();

        String sql = "INSERT INTO users (full_name, username, email, number, password) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, fullName);
        pst.setString(2, username);
        pst.setString(3, email);
        pst.setString(4, number);
        pst.setString(5, password);

        int rows = pst.executeUpdate();

        pst.close();
        con.close();

        return rows == 0;
    }

    public UserModel getUserByUsername(String username) throws Exception {
        UserModel user = null;
        Connection con = DBconfig.getConnection();

        String sql = "SELECT * FROM users WHERE username = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, username);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            user = new UserModel();
            user.setFullName(rs.getString("full_name"));
            user.setUserName(rs.getString("username"));
            user.setDob(rs.getDate("dob"));
            user.setEmail(rs.getString("email"));
            user.setNumber(rs.getString("number"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
        }

        rs.close();
        pst.close();
        con.close();
        return user;
    }
}
