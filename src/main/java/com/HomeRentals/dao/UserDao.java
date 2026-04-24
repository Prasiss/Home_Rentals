package com.homerental.dao;

import com.homerental.model.User;
import com.homerental.util.DatabaseConnection;
import java.sql.*;
import java.util.*;

public class UserDao {
    
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        return DatabaseConnection.getConnection();
    }
    
    // Authentication
    public User authenticateUser(String email, String password) {
        String sql = "SELECT User_No, Full_Name, Username, Email, Role, Phone FROM User WHERE Email = ? AND Password = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUserNo(rs.getInt("User_No"));
                u.setFullName(rs.getString("Full_Name"));
                u.setUsername(rs.getString("Username"));
                u.setEmail(rs.getString("Email"));
                u.setRole(rs.getString("Role"));
                u.setPhone(rs.getString("Phone"));
                return u;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
    
    // Get all users
    public List<User> findAllUsers() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT User_No, Full_Name, Username, Email, Role, Phone, " +
                     "(SELECT COUNT(*) FROM Booking WHERE User_No = u.User_No) as booking_count " +
                     "FROM User u WHERE Role = 'USER' ORDER BY User_No DESC";
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                User u = new User();
                u.setUserNo(rs.getInt("User_No"));
                u.setFullName(rs.getString("Full_Name"));
                u.setUsername(rs.getString("Username"));
                u.setEmail(rs.getString("Email"));
                u.setRole(rs.getString("Role"));
                u.setPhone(rs.getString("Phone"));
                u.setTotalBookings(rs.getInt("booking_count"));
                list.add(u);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    
    // Get recent users
    public List<User> findRecentUsers(int limit) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT User_No, Full_Name, Username, Email, Role, Phone, " +
                     "(SELECT COUNT(*) FROM Booking WHERE User_No = u.User_No) as booking_count " +
                     "FROM User u WHERE Role = 'USER' ORDER BY User_No DESC LIMIT ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setUserNo(rs.getInt("User_No"));
                u.setFullName(rs.getString("Full_Name"));
                u.setUsername(rs.getString("Username"));
                u.setEmail(rs.getString("Email"));
                u.setRole(rs.getString("Role"));
                u.setPhone(rs.getString("Phone"));
                u.setTotalBookings(rs.getInt("booking_count"));
                list.add(u);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    
    // Get user by ID
    public User findById(Integer userNo) {
        String sql = "SELECT User_No, Full_Name, Username, Email, Role, Phone FROM User WHERE User_No = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUserNo(rs.getInt("User_No"));
                u.setFullName(rs.getString("Full_Name"));
                u.setUsername(rs.getString("Username"));
                u.setEmail(rs.getString("Email"));
                u.setRole(rs.getString("Role"));
                u.setPhone(rs.getString("Phone"));
                return u;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
    
    // Suspend user
    public boolean suspendUser(Integer userNo) {
        String sql = "UPDATE User SET Role = 'SUSPENDED' WHERE User_No = ? AND Role = 'USER'";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userNo);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
    
    // Reactivate user
    public boolean reactivateUser(Integer userNo) {
        String sql = "UPDATE User SET Role = 'USER' WHERE User_No = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userNo);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
    
    // Delete user
    public boolean deleteUser(Integer userNo) {
        String sql = "DELETE FROM User WHERE User_No = ? AND Role = 'USER'";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userNo);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
    
    // Get all dealers
    public List<User> findAllDealers() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT User_No, Full_Name, Username, Email, Role, Phone, " +
                     "(SELECT COUNT(*) FROM Property WHERE Dealer_No = u.User_No) as property_count " +
                     "FROM User u WHERE Role = 'DEALER' ORDER BY User_No DESC";
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                User u = new User();
                u.setUserNo(rs.getInt("User_No"));
                u.setFullName(rs.getString("Full_Name"));
                u.setUsername(rs.getString("Username"));
                u.setEmail(rs.getString("Email"));
                u.setRole(rs.getString("Role"));
                u.setPhone(rs.getString("Phone"));
                u.setTotalBookings(rs.getInt("property_count"));
                list.add(u);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    
    // Suspend dealer
    public boolean suspendDealer(Integer userNo) {
        String sql = "UPDATE User SET Role = 'SUSPENDED' WHERE User_No = ? AND Role = 'DEALER'";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userNo);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
    
    // Reactivate dealer
    public boolean reactivateDealer(Integer userNo) {
        String sql = "UPDATE User SET Role = 'DEALER' WHERE User_No = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userNo);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
    
    // Delete dealer
    public boolean deleteDealer(Integer userNo) {
        String sql = "DELETE FROM User WHERE User_No = ? AND Role = 'DEALER'";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userNo);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
    
    // Count methods
    public int countTotalUsers() {
        String sql = "SELECT COUNT(*) FROM User WHERE Role = 'USER'";
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }
    
    public int countActiveDealers() {
        String sql = "SELECT COUNT(*) FROM User WHERE Role = 'DEALER'";
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }
}