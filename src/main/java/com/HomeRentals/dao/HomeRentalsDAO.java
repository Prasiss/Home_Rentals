package com.HomeRentals.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.HomeRentals.config.DBconfig;
import com.HomeRentals.model.HomeModel;
import com.HomeRentals.model.PropertyModel;
import com.HomeRentals.model.UserModel;

public class HomeRentalsDAO {

    // ==================== INSERT OPERATIONS ====================

    /**
     * Insert a new user
     * DB columns: full_name, username, email, phone_number, password
     */
    public boolean insertUser(String fullName, String username, String email,
                              String phone, String password) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "INSERT INTO users (full_name, username, email, phone_number, password) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, fullName);
        pst.setString(2, username);
        pst.setString(3, email);
        pst.setString(4, phone);
        pst.setString(5, password);

        int rows = pst.executeUpdate();
        pst.close();
        con.close();

        return rows > 0;
    }

    /**
     * Insert a new property
     */
    public boolean insertProperty(String title, String location, double pricePerMonth,
                                  String description, int ownerId) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "INSERT INTO property (title, location, price_per_month, description, owner_id, is_available) VALUES (?, ?, ?, ?, ?, 1)";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, title);
        pst.setString(2, location);
        pst.setDouble(3, pricePerMonth);
        pst.setString(4, description);
        pst.setInt(5, ownerId);

        int rows = pst.executeUpdate();
        pst.close();
        con.close();

        return rows > 0;
    }

    // ==================== READ OPERATIONS ====================

    /**
     * Get user by username
     */
    public UserModel getUserByUsername(String username) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "SELECT * FROM users WHERE username = ?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, username);

        ResultSet rs = pst.executeQuery();

        UserModel user = null;
        if (rs.next()) {
            user = mapUser(rs);
        }

        rs.close();
        pst.close();
        con.close();

        return user;
    }

    /**
     * Get user by ID
     */
    public UserModel getUserById(int userId) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "SELECT * FROM users WHERE user_id = ?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, userId);

        ResultSet rs = pst.executeQuery();

        UserModel user = null;
        if (rs.next()) {
            user = mapUser(rs);
        }

        rs.close();
        pst.close();
        con.close();

        return user;
    }

    /**
     * Get all regular users (role = 'USER')
     */
    public List<UserModel> getAllUsers() throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "SELECT * FROM users WHERE role = 'USER' ORDER BY user_id DESC";

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        List<UserModel> users = new ArrayList<>();
        while (rs.next()) {
            users.add(mapUser(rs));
        }

        rs.close();
        st.close();
        con.close();

        return users;
    }

    /**
     * Get all dealers (role = 'DEALER')
     */
    public List<UserModel> getAllDealers() throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "SELECT * FROM users WHERE role = 'DEALER' ORDER BY user_id DESC";

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        List<UserModel> dealers = new ArrayList<>();
        while (rs.next()) {
            dealers.add(mapUser(rs));
        }

        rs.close();
        st.close();
        con.close();

        return dealers;
    }

    /**
     * Get pending dealer requests (role = 'USER', is_approved = 0 — waiting for dealer upgrade)
     * Adjust this query if you add a dealer_request column later.
     */
    public List<UserModel> getPendingDealerRequests() throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "SELECT * FROM users WHERE role = 'USER' AND is_approved = 0 ORDER BY user_id DESC";

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        List<UserModel> users = new ArrayList<>();
        while (rs.next()) {
            users.add(mapUser(rs));
        }

        rs.close();
        st.close();
        con.close();

        return users;
    }

    /**
     * Get home/property by ID
     */
    public HomeModel getHomeById(int propertyId) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "SELECT * FROM property WHERE property_id = ?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, propertyId);

        ResultSet rs = pst.executeQuery();

        HomeModel home = null;
        if (rs.next()) {
            home = mapHome(rs);
        }

        rs.close();
        pst.close();
        con.close();

        return home;
    }

    /**
     * Get all available homes
     */
    public List<HomeModel> getAllHomes() throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "SELECT * FROM property WHERE is_available = 1";

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        List<HomeModel> homes = new ArrayList<>();
        while (rs.next()) {
            homes.add(mapHome(rs));
        }

        rs.close();
        st.close();
        con.close();

        return homes;
    }

    /**
     * Get top 3 available homes
     */
    public List<HomeModel> getTop3Homes() throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "SELECT * FROM property WHERE is_available = 1 LIMIT 3";

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        List<HomeModel> homes = new ArrayList<>();
        while (rs.next()) {
            homes.add(mapHome(rs));
        }

        rs.close();
        st.close();
        con.close();

        return homes;
    }

    /**
     * Get owner of a property
     */
    public UserModel getOwnerByPropertyId(int propertyId) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "SELECT u.* FROM users u JOIN property p ON u.user_id = p.owner_id WHERE p.property_id = ?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, propertyId);

        ResultSet rs = pst.executeQuery();

        UserModel owner = null;
        if (rs.next()) {
            owner = mapUser(rs);
        }

        rs.close();
        pst.close();
        con.close();

        return owner;
    }

    /**
     * Get pending properties (approval_status = 'PENDING')
     */
    public List<String> getPendingProperties() throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "SELECT * FROM property WHERE approval_status = 'PENDING'";

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        List<String> properties = new ArrayList<>();
        while (rs.next()) {
            String prop = "ID: " + rs.getInt("property_id")
                    + ", Title: " + rs.getString("title")
                    + ", Location: " + rs.getString("location");
            properties.add(prop);
        }

        rs.close();
        st.close();
        con.close();

        return properties;
    }

    // ==================== UPDATE OPERATIONS ====================

    /**
     * Update user basic info
     */
    public int updateUser(int userId, String fullName, String email, String phone) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE users SET full_name = ?, email = ?, phone_number = ? WHERE user_id = ?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, fullName);
        pst.setString(2, email);
        pst.setString(3, phone);
        pst.setInt(4, userId);

        int rows = pst.executeUpdate();
        pst.close();
        con.close();

        return rows;
    }

    /**
     * Update user password
     */
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

    /**
     * Update admin profile (full_name, email, phone_number)
     */
    public boolean updateAdminProfile(int adminId, String fullName, String email, String phone) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE users SET full_name = ?, email = ?, phone_number = ? WHERE user_id = ?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, fullName);
        pst.setString(2, email);
        pst.setString(3, phone);
        pst.setInt(4, adminId);

        int rows = pst.executeUpdate();
        pst.close();
        con.close();

        return rows > 0;
    }

    /**
     * Update property status (PENDING / APPROVED / REJECTED)
     */
    public boolean updatePropertyStatus(int propertyId, String status) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE property SET approval_status = ? WHERE property_id = ?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, status);
        pst.setInt(2, propertyId);

        int rows = pst.executeUpdate();
        pst.close();
        con.close();

        return rows > 0;
    }

    /**
     * Approve user — sets is_approved = 1
     */
    public boolean approveUser(int userId) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE users SET is_approved = 1 WHERE user_id = ?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, userId);

        int rows = pst.executeUpdate();
        pst.close();
        con.close();

        return rows > 0;
    }

    /**
     * Approve dealer — sets is_approved = 1 WHERE role = 'DEALER'
     */
    public boolean approveDealer(int userId) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE users SET is_approved = 1 WHERE user_id = ? AND role = 'DEALER'";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, userId);

        int rows = pst.executeUpdate();
        pst.close();
        con.close();

        return rows > 0;
    }

    /**
     * Approve dealer request — upgrades role to DEALER and approves
     */
    public boolean approveDealerRequest(int userId) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE users SET role = 'DEALER', is_approved = 1 WHERE user_id = ?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, userId);

        int rows = pst.executeUpdate();
        pst.close();
        con.close();

        return rows > 0;
    }

    /**
     * Reject dealer request — resets is_approved = 0, keeps role as USER
     */
    public boolean rejectDealerRequest(int userId) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE users SET is_approved = 0 WHERE user_id = ?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, userId);

        int rows = pst.executeUpdate();
        pst.close();
        con.close();

        return rows > 0;
    }

    /**
     * Submit dealer request — marks user as pending review
     * NOTE: your current DB has no dealer_request column.
     * This sets is_approved = 0 to signal "pending".
     * Add a dealer_request VARCHAR column to users if you need full tracking.
     */
    public boolean submitDealerRequest(int userId, String companyName, String yearsExperience) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE users SET is_approved = 0 WHERE user_id = ?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, userId);

        int rows = pst.executeUpdate();
        pst.close();
        con.close();

        return rows > 0;
    }

    /**
     * Deactivate (soft delete) user — sets is_approved = 0
     */
    public boolean deactivateUser(int userId) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE users SET is_approved = 0 WHERE user_id = ?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, userId);

        int rows = pst.executeUpdate();
        pst.close();
        con.close();

        return rows > 0;
    }

    /**
     * Deactivate dealer — sets is_approved = 0 WHERE role = 'DEALER'
     */
    public boolean deactivateDealer(int userId) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE users SET is_approved = 0 WHERE user_id = ? AND role = 'DEALER'";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, userId);

        int rows = pst.executeUpdate();
        pst.close();
        con.close();

        return rows > 0;
    }

    // ==================== DELETE OPERATIONS ====================

    /**
     * Soft delete user (sets is_approved = 0)
     */
    public boolean deleteUser(int userId) throws Exception {
        return deactivateUser(userId);
    }

    /**
     * Soft delete property (sets is_available = 0)
     */
    public boolean deleteProperty(int propertyId) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE property SET is_available = 0 WHERE property_id = ?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, propertyId);

        int rows = pst.executeUpdate();
        pst.close();
        con.close();

        return rows > 0;
    }

    // ==================== DASHBOARD / COUNT OPERATIONS ====================

    public long getTotalRevenue() throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "SELECT COALESCE(SUM(total_price), 0) FROM booking WHERE booking_status = 'COMPLETED'";

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        long revenue = 0;
        if (rs.next()) revenue = rs.getLong(1);

        rs.close(); st.close(); con.close();
        return revenue;
    }

    public int getActivePropertiesCount() throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "SELECT COUNT(*) FROM property WHERE is_available = 1 AND approval_status = 'APPROVED'";

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        int count = 0;
        if (rs.next()) count = rs.getInt(1);

        rs.close(); st.close(); con.close();
        return count;
    }

    public int getTotalUsersCount() throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "SELECT COUNT(*) FROM users WHERE role = 'USER'";

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        int count = 0;
        if (rs.next()) count = rs.getInt(1);

        rs.close(); st.close(); con.close();
        return count;
    }

    public int getActiveDealersCount() throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "SELECT COUNT(*) FROM users WHERE role = 'DEALER' AND is_approved = 1";

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        int count = 0;
        if (rs.next()) count = rs.getInt(1);

        rs.close(); st.close(); con.close();
        return count;
    }

    public int getPendingUsersCount() throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "SELECT COUNT(*) FROM users WHERE is_approved = 0";

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        int count = 0;
        if (rs.next()) count = rs.getInt(1);

        rs.close(); st.close(); con.close();
        return count;
    }

    public int getPendingPropertiesCount() throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "SELECT COUNT(*) FROM property WHERE approval_status = 'PENDING'";

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        int count = 0;
        if (rs.next()) count = rs.getInt(1);

        rs.close(); st.close(); con.close();
        return count;
    }

    /**
     * Check if user is approved
     */
    public boolean isActive(int userId) {
        boolean isApproved = false;
        try {
            Connection con = DBconfig.getConnection();
            String sql = "SELECT is_approved FROM users WHERE user_id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                isApproved = rs.getBoolean("is_approved");
            }

            rs.close(); ps.close(); con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isApproved;
    }

    // ==================== PRIVATE HELPERS ====================

    /**
     * Maps a ResultSet row to a UserModel using actual DB column names
     */
    private UserModel mapUser(ResultSet rs) throws SQLException {
        UserModel user = new UserModel();
        user.setUserId(rs.getInt("user_id"));
        user.setFullName(rs.getString("full_name"));
        user.setUserName(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setNumber(rs.getString("phone_number"));   // DB col = phone_number
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));              // DB col = role (string)
        user.setIsApproved(rs.getInt("is_approved"));   // DB col = is_approved (0/1)
        return user;
    }

    /**
     * Maps a ResultSet row to a HomeModel
     */
    private HomeModel mapHome(ResultSet rs) throws SQLException {
        HomeModel home = new HomeModel();
        home.setId(rs.getInt("property_id"));
        home.setName(rs.getString("title"));
        home.setLocation(rs.getString("location"));
        home.setPrice(rs.getDouble("price_per_month"));
        home.setDescription(rs.getString("description"));
        return home;
    }

    /**
     * Get pending properties as PropertyModel objects (joins with users for owner name)
     */
    public List<PropertyModel> getPendingPropertiesAsModel() throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "SELECT p.*, u.full_name AS owner_name FROM property p " +
                     "LEFT JOIN users u ON p.owner_id = u.user_id " +
                     "WHERE p.approval_status = 'PENDING' ORDER BY p.property_id DESC";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        List<PropertyModel> list = new ArrayList<>();
        while (rs.next()) { list.add(mapProperty(rs)); }
        rs.close(); st.close(); con.close();
        return list;
    }

    /**
     * Get all properties filtered by status, joined with owner name
     */
    public List<PropertyModel> getPropertiesByStatus(String status) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "SELECT p.*, u.full_name AS owner_name FROM property p " +
                     "LEFT JOIN users u ON p.owner_id = u.user_id " +
                     "WHERE p.approval_status = ? ORDER BY p.property_id DESC";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, status.toUpperCase());
        ResultSet rs = pst.executeQuery();
        List<PropertyModel> list = new ArrayList<>();
        while (rs.next()) { list.add(mapProperty(rs)); }
        rs.close(); pst.close(); con.close();
        return list;
    }

    private PropertyModel mapProperty(ResultSet rs) throws java.sql.SQLException {
        PropertyModel p = new PropertyModel();
        p.setPropertyId(rs.getInt("property_id"));
        p.setTitle(rs.getString("title"));
        p.setLocation(rs.getString("location"));
        p.setPricePerMonth(rs.getDouble("price_per_month"));
        p.setDescription(rs.getString("description"));
        p.setOwnerId(rs.getInt("owner_id"));
        p.setApprovalStatus(rs.getString("approval_status"));
        p.setIsAvailable(rs.getInt("is_available"));
        try { p.setOwnerName(rs.getString("owner_name")); } catch (Exception ignored) {}
        return p;
    }


    /**
     * Search properties by title or location (case-insensitive)
     */
    public List<HomeModel> searchProperty(String keyword) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "SELECT * FROM property WHERE is_available = 1 AND " +
                     "(title LIKE ? OR location LIKE ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        String pattern = "%" + keyword + "%";
        pst.setString(1, pattern);
        pst.setString(2, pattern);
        ResultSet rs = pst.executeQuery();
        List<HomeModel> homes = new ArrayList<>();
        while (rs.next()) { homes.add(mapHome(rs)); }
        rs.close(); pst.close(); con.close();
        return homes;
    }

}
