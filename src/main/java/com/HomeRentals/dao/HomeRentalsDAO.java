package com.HomeRentals.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.HomeRentals.config.DBconfig;
import com.HomeRentals.model.UserModel;

public class HomeRentalsDAO {

    // ==================== AUTH ====================

    public UserModel getUserByUsername(String username) throws Exception {
        UserModel user = null;
        Connection con = DBconfig.getConnection();
        String sql = "SELECT * FROM users WHERE username = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, username);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            user = mapUser(rs);
        }

        rs.close();
        pst.close();
        con.close();
        return user;
    }

    public boolean insertUser(String fullName, String username,
            String email, String number, String password) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "INSERT INTO users (full_name, username, email, number, password, status) " +
                     "VALUES (?, ?, ?, ?, ?, 'PENDING')";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, fullName);
        pst.setString(2, username);
        pst.setString(3, email);
        pst.setString(4, number);
        pst.setString(5, password);
        int rows = pst.executeUpdate();
        pst.close();
        con.close();
        return rows > 0;
    }

    // ==================== DASHBOARD STATS ====================

    public Map<String, Object> getDashboardStats() throws Exception {
        Map<String, Object> stats = new HashMap<>();
        Connection con = DBconfig.getConnection();
        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(
            "SELECT COALESCE(SUM(total_price), 0) FROM booking WHERE booking_status IN ('CONFIRMED', 'COMPLETED')");
        if (rs.next()) stats.put("totalRevenue", rs.getDouble(1));
        rs.close();

        rs = st.executeQuery("SELECT COUNT(*) FROM property WHERE approval_status = 'APPROVED' AND is_available = TRUE");
        if (rs.next()) stats.put("activeProperties", rs.getInt(1));
        rs.close();

        rs = st.executeQuery("SELECT COUNT(*) FROM users WHERE role_id = 3");
        if (rs.next()) stats.put("totalUsers", rs.getInt(1));
        rs.close();

        rs = st.executeQuery("SELECT COUNT(*) FROM users WHERE role_id = 2 AND status = 'ACTIVE'");
        if (rs.next()) stats.put("activeDealers", rs.getInt(1));
        rs.close();

        rs = st.executeQuery("SELECT COUNT(*) FROM users WHERE status = 'PENDING'");
        if (rs.next()) stats.put("pendingUsers", rs.getInt(1));
        rs.close();

        rs = st.executeQuery("SELECT COUNT(*) FROM property WHERE approval_status = 'PENDING'");
        if (rs.next()) stats.put("pendingProperties", rs.getInt(1));
        rs.close();

        rs = st.executeQuery("SELECT COUNT(*) FROM users WHERE dealer_request = 'PENDING'");
        if (rs.next()) stats.put("pendingDealerRequests", rs.getInt(1));
        rs.close();

        st.close();
        con.close();
        return stats;
    }

    // ==================== USER MANAGEMENT ====================

    public List<UserModel> getAllUsers() throws Exception {
        List<UserModel> list = new ArrayList<>();
        Connection con = DBconfig.getConnection();
        String sql = "SELECT u.*, " +
                     "(SELECT COUNT(*) FROM booking WHERE user_id = u.user_id) as booking_count " +
                     "FROM users u WHERE u.role_id = 3 ORDER BY u.user_id DESC";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            UserModel u = mapUser(rs);
            u.setTotalBookings(rs.getInt("booking_count"));
            list.add(u);
        }

        rs.close();
        st.close();
        con.close();
        return list;
    }

    public List<UserModel> getRecentUsers(int limit) throws Exception {
        List<UserModel> list = new ArrayList<>();
        Connection con = DBconfig.getConnection();
        String sql = "SELECT u.*, " +
                     "(SELECT COUNT(*) FROM booking WHERE user_id = u.user_id) as booking_count " +
                     "FROM users u WHERE u.role_id = 3 ORDER BY u.user_id DESC LIMIT ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, limit);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            UserModel u = mapUser(rs);
            u.setTotalBookings(rs.getInt("booking_count"));
            list.add(u);
        }

        rs.close();
        pst.close();
        con.close();
        return list;
    }

    public boolean approveUser(int userId) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE users SET status = 'ACTIVE' WHERE user_id = ? AND status = 'PENDING'";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, userId);
        int rows = pst.executeUpdate();
        pst.close();
        con.close();
        return rows > 0;
    }

    public boolean softDeleteUser(int userId) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE users SET status = 'INACTIVE' WHERE user_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, userId);
        int rows = pst.executeUpdate();
        pst.close();
        con.close();
        return rows > 0;
    }

    // ==================== DEALER MANAGEMENT ====================

    public List<UserModel> getAllDealers() throws Exception {
        List<UserModel> list = new ArrayList<>();
        Connection con = DBconfig.getConnection();
        String sql = "SELECT u.*, " +
                     "(SELECT COUNT(*) FROM property WHERE owner_id = u.user_id) as property_count " +
                     "FROM users u WHERE u.role_id = 2 ORDER BY u.user_id DESC";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            UserModel u = mapUser(rs);
            u.setPropertyCount(rs.getInt("property_count"));
            list.add(u);
        }

        rs.close();
        st.close();
        con.close();
        return list;
    }

    public List<UserModel> getPendingDealerRequests() throws Exception {
        List<UserModel> list = new ArrayList<>();
        Connection con = DBconfig.getConnection();
        String sql = "SELECT u.* FROM users u " +
                     "WHERE u.role_id = 3 AND u.dealer_request = 'PENDING' " +
                     "ORDER BY u.user_id DESC";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            list.add(mapUser(rs));
        }

        rs.close();
        st.close();
        con.close();
        return list;
    }

    public boolean approveDealerRequest(int userId) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE users SET role = 'DEALER', role_id = 2, dealer_request = 'NONE' " +
                     "WHERE user_id = ? AND dealer_request = 'PENDING'";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, userId);
        int rows = pst.executeUpdate();
        pst.close();
        con.close();
        return rows > 0;
    }

    public boolean rejectDealerRequest(int userId) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE users SET dealer_request = 'REJECTED' " +
                     "WHERE user_id = ? AND dealer_request = 'PENDING'";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, userId);
        int rows = pst.executeUpdate();
        pst.close();
        con.close();
        return rows > 0;
    }

    public boolean approveDealer(int userId) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE users SET status = 'ACTIVE' WHERE user_id = ? AND role_id = 2 AND status = 'PENDING'";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, userId);
        int rows = pst.executeUpdate();
        pst.close();
        con.close();
        return rows > 0;
    }

    public boolean softDeleteDealer(int userId) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE users SET status = 'INACTIVE' WHERE user_id = ? AND role_id = 2";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, userId);
        int rows = pst.executeUpdate();
        pst.close();
        con.close();
        return rows > 0;
    }

    // ==================== PROPERTY APPROVALS ====================

    public List<Map<String, Object>> getPendingProperties() throws Exception {
        List<Map<String, Object>> list = new ArrayList<>();
        Connection con = DBconfig.getConnection();
        String sql = "SELECT p.*, u.full_name as owner_name, c.category_name " +
                     "FROM property p " +
                     "JOIN users u ON p.owner_id = u.user_id " +
                     "JOIN category c ON p.category_id = c.category_id " +
                     "WHERE p.approval_status = 'PENDING' ORDER BY p.created_at DESC";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            list.add(mapProperty(rs));
        }

        rs.close();
        st.close();
        con.close();
        return list;
    }

    public List<Map<String, Object>> getPropertiesByStatus(String status) throws Exception {
        List<Map<String, Object>> list = new ArrayList<>();
        Connection con = DBconfig.getConnection();
        String sql = "SELECT p.*, u.full_name as owner_name, c.category_name " +
                     "FROM property p " +
                     "JOIN users u ON p.owner_id = u.user_id " +
                     "JOIN category c ON p.category_id = c.category_id " +
                     "WHERE p.approval_status = ? ORDER BY p.created_at DESC";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, status.toUpperCase());
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            list.add(mapProperty(rs));
        }

        rs.close();
        pst.close();
        con.close();
        return list;
    }

    public boolean updatePropertyStatus(int propertyId, String status, int approvedBy) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE property SET approval_status = ?, approved_by = ? WHERE property_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, status);
        pst.setInt(2, approvedBy);
        pst.setInt(3, propertyId);
        int rows = pst.executeUpdate();
        pst.close();
        con.close();
        return rows > 0;
    }

    // ==================== ADMIN PROFILE ====================

    public UserModel getAdminProfile(int adminId) throws Exception {
        UserModel u = null;
        Connection con = DBconfig.getConnection();
        String sql = "SELECT * FROM users WHERE user_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, adminId);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            u = mapUser(rs);
        }

        rs.close();
        pst.close();
        con.close();
        return u;
    }

    public boolean updateAdminProfile(int adminId, String fullName, String email,
                                       String phone, String address, String profileImage) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE users SET full_name = ?, email = ?, number = ?, " +
                     "address = ?, profile_image = ? WHERE user_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, fullName);
        pst.setString(2, email);
        pst.setString(3, phone);
        pst.setString(4, address);
        pst.setString(5, profileImage);
        pst.setInt(6, adminId);
        int rows = pst.executeUpdate();
        pst.close();
        con.close();
        return rows > 0;
    }

    public boolean updatePassword(int userId, String newPassword) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE users SET password = ? WHERE user_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, newPassword);
        pst.setInt(2, userId);
        int rows = pst.executeUpdate();
        pst.close();
        con.close();
        return rows > 0;
    }

    // ==================== PRIVATE HELPERS ====================

    private UserModel mapUser(ResultSet rs) throws Exception {
        UserModel u = new UserModel();
        u.setUserNo(rs.getInt("user_id"));
        u.setFullName(rs.getString("full_name"));
        u.setUsername(rs.getString("username"));
        u.setEmail(rs.getString("email"));
        u.setPhone(rs.getString("number"));
        u.setPassword(rs.getString("password")); // ← THIS LINE WAS MISSING — root cause of all password bugs
        u.setRole(rs.getString("role"));
        u.setStatus(rs.getString("status"));
        u.setProfileImage(rs.getString("profile_image"));
        try {
            u.setAddress(rs.getString("address"));
        } catch (Exception e) {
            u.setAddress(null);
        }
        return u;
    }

    private Map<String, Object> mapProperty(ResultSet rs) throws Exception {
        Map<String, Object> prop = new HashMap<>();
        prop.put("propertyId", rs.getInt("property_id"));
        prop.put("title", rs.getString("title"));
        prop.put("location", rs.getString("location"));
        prop.put("pricePerMonth", rs.getDouble("price_per_month"));
        prop.put("ownerName", rs.getString("owner_name"));
        prop.put("categoryName", rs.getString("category_name"));
        prop.put("approvalStatus", rs.getString("approval_status"));
        return prop;
    }
}
