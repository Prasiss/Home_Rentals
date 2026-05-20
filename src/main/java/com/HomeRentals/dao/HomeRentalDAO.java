package com.HomeRental.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.HomeRental.config.DBconfig;
import com.HomeRental.model.HomeModel;
import com.HomeRental.model.PropertyModel;
import com.HomeRental.model.UserModel;

public class HomeRentalDAO {
	/**
	 * Inserts a new user into the system with default role USER.
	 * @param fullName Full name of the user
	 * @param username Username of the user
	 * @param email Email address of the user
	 * @param phone Phone number of the user
	 * @param password Hashed password
	 * @return true if user is successfully inserted
	 * @throws Exception if database operation fails
	 */
    public boolean insertUser(String fullName, String username, String email,
                              String phone, String password) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "INSERT INTO users (full_name, username, email, phone_number, password, role, is_approved) "
                   + "VALUES (?, ?, ?, ?, ?, 'USER', 0)";

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
     * Retrieves homes below or equal to a given price.
     * @param price Maximum price filter
     * @return List of HomeModel matching the condition
     */
    public List<HomeModel> getHomesBelowPrice(int price) {
        List<HomeModel> homes = new ArrayList<>();
        String query = "SELECT * FROM property WHERE price <= ?";
        try (Connection conn = DBconfig.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, price);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                HomeModel home = new HomeModel();
                home.setId(rs.getInt("property_id"));
                home.setName(rs.getString("property_name"));
                home.setLocation(rs.getString("location"));
                home.setPrice(rs.getDouble("price"));
                home.setDescription(rs.getString("description"));

                homes.add(home);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return homes;
    }
    /**
     * Inserts a new property into the database.
     * @param title Property title
     * @param location Property location
     * @param pricePerMonth Monthly rental price
     * @param description Property description
     * @param ownerId Owner user ID
     * @return true if inserted successfully
     * @throws Exception if database operation fails
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

    /**
     * Retrieves a user by username.
     * @param username Username to search
     * @return UserModel object if found, otherwise null
     * @throws Exception if database operation fails
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
     * Retrieves a user by user ID.
     * @param userId User ID
     * @return UserModel object if found, otherwise null
     * @throws Exception if database operation fails
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
     * Retrieves all users with role USER.
     * @return List of UserModel objects
     * @throws Exception if database operation fails
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
     * Retrieves all users with role DEALER.
     * @return List of UserModel objects
     * @throws Exception if database operation fails
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
     * Get pending dealer requests (role = 'USER', is_approved = 0 - waiting for dealer upgrade)
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
     * Retrieves home by property ID.
     * @param propertyId Property ID
     * @return HomeModel object if found, otherwise null
     * @throws Exception if database operation fails
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
     * Retrieves all available homes.
     * @return List of HomeModel objects
     * @throws Exception if database operation fails
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
     * Retrieves home by property ID.
     * @param propertyId Property ID
     * @return HomeModel object if found, otherwise null
     * @throws Exception if database operation fails
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
     * Searches properties by keyword in title or location.
     * @param keyword Search keyword
     * @return List of matching HomeModel objects
     * @throws Exception if database operation fails
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
     * Update user profile — includes address
     */
    public int updateUserProfile(int userId, String fullName, String email, String phone, String address) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE users SET full_name = ?, email = ?, phone_number = ?, address = ? WHERE user_id = ?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, fullName);
        pst.setString(2, email);
        pst.setString(3, phone);
        pst.setString(4, address);
        pst.setInt(5, userId);

        int rows = pst.executeUpdate();
        pst.close();
        con.close();

        return rows;
    }

    /**
     * Update profile image filename for a user
     */
    public boolean updateProfileImage(int userId, String fileName) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE users SET profile_image = ? WHERE user_id = ?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, fileName);
        pst.setInt(2, userId);

        int rows = pst.executeUpdate();
        pst.close();
        con.close();

        return rows > 0;
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
    public boolean updateAdminProfile(int adminId, String fullName, String email,
            String phone, String image) throws Exception {
    		Connection con = DBconfig.getConnection();
    		PreparedStatement pst;
    		if (image != null) {
    			String sql = "UPDATE users SET full_name = ?, email = ?, phone_number = ?, profile_image = ? WHERE user_id = ?";
    			pst = con.prepareStatement(sql);
    			pst.setString(1, fullName);
    			pst.setString(2, email);
    			pst.setString(3, phone);
    			pst.setString(4, image);
    			pst.setInt(5, adminId);
    		} else {
    			String sql = "UPDATE users SET full_name = ?, email = ?, phone_number = ? WHERE user_id = ?";
    			pst = con.prepareStatement(sql);
    			pst.setString(1, fullName);
    			pst.setString(2, email);
    			pst.setString(3, phone);
    			pst.setInt(4, adminId);
    		}
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
     * Approve user - sets is_approved = 1
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
     * Approve dealer - sets is_approved = 1 WHERE role = 'DEALER'
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
     * Approve dealer request - upgrades role to DEALER and approves
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
     * Reject dealer request - resets is_approved = 0, keeps role as USER
     */
    public boolean rejectDealerRequest(int userId) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE users SET is_approved = -1 WHERE user_id = ?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, userId);

        int rows = pst.executeUpdate();
        pst.close();
        con.close();

        return rows > 0;
    }

    /**
     * Submit dealer request - marks user as pending review
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
     * Deactivate (soft delete) user - sets is_approved = 0
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
     * Deactivate dealer - sets is_approved = 0 WHERE role = 'DEALER'
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
        try {
            Connection con = DBconfig.getConnection();
            String sql = "SELECT COALESCE(SUM(total_price), 0) FROM booking WHERE booking_status = 'COMPLETED'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            long revenue = 0;
            if (rs.next()) revenue = rs.getLong(1);
            rs.close(); st.close(); con.close();
            return revenue;
        } catch (Exception e) { return 0; }
    }

    public int getActivePropertiesCount() throws Exception {
        try {
            Connection con = DBconfig.getConnection();
            String sql = "SELECT COUNT(*) FROM property WHERE is_available = 1 AND approval_status = 'APPROVED'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            int count = 0;
            if (rs.next()) count = rs.getInt(1);
            rs.close(); st.close(); con.close();
            return count;
        } catch (Exception e) { return 0; }
    }

    public int getTotalUsersCount() throws Exception {
        try {
            Connection con = DBconfig.getConnection();
            String sql = "SELECT COUNT(*) FROM users WHERE role = 'USER'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            int count = 0;
            if (rs.next()) count = rs.getInt(1);
            rs.close(); st.close(); con.close();
            return count;
        } catch (Exception e) { return 0; }
    }

    public int getActiveDealersCount() throws Exception {
        try {
            Connection con = DBconfig.getConnection();
            String sql = "SELECT COUNT(*) FROM users WHERE role = 'DEALER' AND is_approved = 1";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            int count = 0;
            if (rs.next()) count = rs.getInt(1);
            rs.close(); st.close(); con.close();
            return count;
        } catch (Exception e) { return 0; }
    }

    public int getPendingUsersCount() throws Exception {
        try {
            Connection con = DBconfig.getConnection();
            String sql = "SELECT COUNT(*) FROM users WHERE is_approved = 0";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            int count = 0;
            if (rs.next()) count = rs.getInt(1);
            rs.close(); st.close(); con.close();
            return count;
        } catch (Exception e) { return 0; }
    }

    public int getPendingPropertiesCount() throws Exception {
        try {
            Connection con = DBconfig.getConnection();
            String sql = "SELECT COUNT(*) FROM property WHERE approval_status = 'PENDING'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            int count = 0;
            if (rs.next()) count = rs.getInt(1);
            rs.close(); st.close(); con.close();
            return count;
        } catch (Exception e) { return 0; }
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
        user.setNumber(rs.getString("phone_number"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));
        user.setIsApproved(rs.getInt("is_approved"));
        try { user.setAddress(rs.getString("address")); } catch (Exception ignored) {}
        try { user.setProfileImage(rs.getString("profile_image")); } catch (Exception ignored) {}
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

    // ==================== DEALER - DASHBOARD STATS ====================

    public java.util.Map<String, Object> getDealerStats(int dealerId) throws Exception {
        java.util.Map<String, Object> stats = new java.util.HashMap<>();
        Connection con = DBconfig.getConnection();
        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(
            "SELECT COUNT(*) FROM property WHERE owner_id = " + dealerId);
        if (rs.next()) stats.put("totalProperties", rs.getInt(1));
        rs.close();

        rs = st.executeQuery(
            "SELECT COUNT(*) FROM property WHERE owner_id = " + dealerId
            + " AND approval_status = 'APPROVED' AND is_available = TRUE");
        if (rs.next()) stats.put("activeProperties", rs.getInt(1));
        rs.close();

        rs = st.executeQuery(
            "SELECT COUNT(*) FROM property WHERE owner_id = " + dealerId
            + " AND approval_status = 'PENDING'");
        if (rs.next()) stats.put("pendingProperties", rs.getInt(1));
        rs.close();

        rs = st.executeQuery(
            "SELECT COUNT(*) FROM booking b "
            + "JOIN property p ON b.property_id = p.property_id "
            + "WHERE p.owner_id = " + dealerId);
        if (rs.next()) stats.put("totalBookings", rs.getInt(1));
        rs.close();

        rs = st.executeQuery(
            "SELECT COUNT(*) FROM booking b "
            + "JOIN property p ON b.property_id = p.property_id "
            + "WHERE p.owner_id = " + dealerId
            + " AND b.booking_status IN ('CONFIRMED','COMPLETED')");
        if (rs.next()) stats.put("confirmedBookings", rs.getInt(1));
        rs.close();

        rs = st.executeQuery(
            "SELECT COALESCE(SUM(b.total_price),0) FROM booking b "
            + "JOIN property p ON b.property_id = p.property_id "
            + "WHERE p.owner_id = " + dealerId
            + " AND b.booking_status IN ('CONFIRMED','COMPLETED')");
        if (rs.next()) stats.put("totalRevenue", rs.getDouble(1));
        rs.close();

        st.close();
        con.close();
        return stats;
    }

    // ==================== DEALER - PROPERTIES ====================

    public java.util.List<java.util.Map<String, Object>> getAllDealerProperties(int dealerId) throws Exception {
        java.util.List<java.util.Map<String, Object>> list = new java.util.ArrayList<>();
        Connection con = DBconfig.getConnection();
        PreparedStatement pst = con.prepareStatement(
            "SELECT p.* FROM property p "
            + "WHERE p.owner_id = ? ORDER BY p.property_id DESC");
        pst.setInt(1, dealerId);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) { list.add(mapDealerProperty(rs)); }
        rs.close(); pst.close(); con.close();
        return list;
    }

    public java.util.List<java.util.Map<String, Object>> getDealerPropertiesByStatus(int dealerId, String status) throws Exception {
        java.util.List<java.util.Map<String, Object>> list = new java.util.ArrayList<>();
        Connection con = DBconfig.getConnection();
        PreparedStatement pst = con.prepareStatement(
            "SELECT p.* FROM property p "
            + "WHERE p.owner_id = ? AND p.approval_status = ? ORDER BY p.property_id DESC");
        pst.setInt(1, dealerId);
        pst.setString(2, status.toUpperCase());
        ResultSet rs = pst.executeQuery();
        while (rs.next()) { list.add(mapDealerProperty(rs)); }
        rs.close(); pst.close(); con.close();
        return list;
    }

    public java.util.List<java.util.Map<String, Object>> getRecentDealerProperties(int dealerId, int limit) throws Exception {
        java.util.List<java.util.Map<String, Object>> list = new java.util.ArrayList<>();
        Connection con = DBconfig.getConnection();
        PreparedStatement pst = con.prepareStatement(
            "SELECT p.property_id, p.title, p.location, p.price_per_month, p.approval_status "
            + "FROM property p WHERE p.owner_id = ? ORDER BY p.property_id DESC LIMIT ?");
        pst.setInt(1, dealerId);
        pst.setInt(2, limit);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            java.util.Map<String, Object> p = new java.util.HashMap<>();
            p.put("propertyId",     rs.getInt("property_id"));
            p.put("title",          rs.getString("title"));
            p.put("location",       rs.getString("location"));
            p.put("pricePerMonth",  rs.getDouble("price_per_month"));
            p.put("approvalStatus", rs.getString("approval_status"));
            list.add(p);
        }
        rs.close(); pst.close(); con.close();
        return list;
    }

    public java.util.Map<String, Object> getDealerPropertyById(int propertyId, int dealerId) throws Exception {
        Connection con = DBconfig.getConnection();
        PreparedStatement pst = con.prepareStatement(
            "SELECT p.* FROM property p "
            + "WHERE p.property_id = ? AND p.owner_id = ?");
        pst.setInt(1, propertyId);
        pst.setInt(2, dealerId);
        ResultSet rs = pst.executeQuery();
        java.util.Map<String, Object> p = null;
        if (rs.next()) { p = mapDealerProperty(rs); }
        rs.close(); pst.close(); con.close();
        return p;
    }

    // =====================================================================
    // FIX: changed available_from -> created_at in INSERT and UPDATE queries
    // =====================================================================

    public boolean insertDealerProperty(int ownerId, String title, String location,
            String description, double pricePerMonth,
            int categoryId, String availableFrom) throws Exception {
        Connection con = DBconfig.getConnection();
        PreparedStatement pst = con.prepareStatement(
            "INSERT INTO property "
            + "(owner_id, title, description, location, price_per_month, "
            + " category_id, created_at, is_available, approval_status) "
            + "VALUES (?,?,?,?,?,?,?,FALSE,'PENDING')");
        pst.setInt(1, ownerId);
        pst.setString(2, title);
        pst.setString(3, description);
        pst.setString(4, location);
        pst.setDouble(5, pricePerMonth);
        pst.setInt(6, categoryId);
        pst.setString(7, availableFrom);   // value from form, stored in created_at column
        int rows = pst.executeUpdate();
        pst.close(); con.close();
        return rows > 0;
    }

    public boolean updateDealerProperty(int propertyId, int ownerId, String title,
            String location, String description,
            double pricePerMonth, int categoryId, String availableFrom) throws Exception {
        Connection con = DBconfig.getConnection();
        PreparedStatement pst = con.prepareStatement(
            "UPDATE property SET title=?, location=?, description=?, "
            + "price_per_month=?, category_id=?, "
            + "created_at=?, approval_status='PENDING' "
            + "WHERE property_id=? AND owner_id=?");
        pst.setString(1, title);
        pst.setString(2, location);
        pst.setString(3, description);
        pst.setDouble(4, pricePerMonth);
        pst.setInt(5, categoryId);
        pst.setString(6, availableFrom);   // value from form, stored in created_at column
        pst.setInt(7, propertyId);
        pst.setInt(8, ownerId);
        int rows = pst.executeUpdate();
        pst.close(); con.close();
        return rows > 0;
    }

    public boolean deactivateDealerProperty(int propertyId, int dealerId) throws Exception {
        Connection con = DBconfig.getConnection();
        PreparedStatement pst = con.prepareStatement(
            "UPDATE property SET is_available = FALSE WHERE property_id = ? AND owner_id = ?");
        pst.setInt(1, propertyId); pst.setInt(2, dealerId);
        int rows = pst.executeUpdate();
        pst.close(); con.close();
        return rows > 0;
    }

    // ==================== DEALER - CATEGORIES ====================

    public java.util.List<java.util.Map<String, Object>> getAllCategories() throws Exception {
        java.util.List<java.util.Map<String, Object>> list = new java.util.ArrayList<>();
        Connection con = DBconfig.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(
            "SELECT category_id, category_name FROM category ORDER BY category_name");
        while (rs.next()) {
            java.util.Map<String, Object> cat = new java.util.HashMap<>();
            cat.put("categoryId",   rs.getInt("category_id"));
            cat.put("categoryName", rs.getString("category_name"));
            list.add(cat);
        }
        rs.close(); st.close(); con.close();
        return list;
    }

    // ==================== DEALER - BOOKINGS ====================

    public java.util.List<java.util.Map<String, Object>> getRecentDealerBookings(int dealerId, int limit) throws Exception {
        java.util.List<java.util.Map<String, Object>> list = new java.util.ArrayList<>();
        Connection con = DBconfig.getConnection();
        PreparedStatement pst = con.prepareStatement(
            "SELECT b.booking_id, p.title AS property_title, "
            + "u.full_name AS tenant_name, b.total_price, b.booking_status "
            + "FROM booking b "
            + "JOIN property p ON b.property_id = p.property_id "
            + "JOIN users u ON b.user_id = u.user_id "
            + "WHERE p.owner_id = ? ORDER BY b.booking_id DESC LIMIT ?");
        pst.setInt(1, dealerId); pst.setInt(2, limit);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            java.util.Map<String, Object> bk = new java.util.HashMap<>();
            bk.put("bookingId",     rs.getInt("booking_id"));
            bk.put("propertyTitle", rs.getString("property_title"));
            bk.put("tenantName",    rs.getString("tenant_name"));
            bk.put("totalPrice",    rs.getDouble("total_price"));
            bk.put("status",        rs.getString("booking_status"));
            list.add(bk);
        }
        rs.close(); pst.close(); con.close();
        return list;
    }

    // ==================== DEALER - PRIVATE HELPER ====================

    private java.util.Map<String, Object> mapDealerProperty(ResultSet rs) throws SQLException {
        java.util.Map<String, Object> p = new java.util.HashMap<>();
        p.put("propertyId",     rs.getInt("property_id"));
        p.put("title",          rs.getString("title"));
        p.put("location",       rs.getString("location"));
        p.put("description",    rs.getString("description"));
        p.put("pricePerMonth",  rs.getDouble("price_per_month"));
        p.put("categoryId",     rs.getInt("category_id"));
        p.put("availableFrom",  rs.getDate("created_at"));   // FIX: read from created_at column
        p.put("approvalStatus", rs.getString("approval_status"));
        p.put("isAvailable",    rs.getBoolean("is_available"));
        return p;
    }
	public boolean saveMessage(String fullName, String email, String messageText) throws Exception {
	    	
	    String sql ="INSERT INTO contact_message (full_name, email, message_text) VALUES (?, ?, ?)";
	   	Connection conn = DBconfig.getConnection();
	   	PreparedStatement pst = conn.prepareStatement(sql); 
	    	
	    pst.setString(1, fullName);
	    pst.setString(2, email);
	   	pst.setString(3, messageText);
	   	int rows = pst.executeUpdate();
	    pst.close();
	    conn.close();
	    return rows >0;
	    }
	public boolean addToWishlist(int userId, int propertyId) throws Exception {

        String sql = "INSERT INTO wishlist (user_id, property_id) VALUES (?, ?)";

        Connection conn = DBconfig.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, userId);
        pst.setInt(2, propertyId);

        int rows = pst.executeUpdate();
        pst.close();
        conn.close();
        return rows > 0;
    }
    public boolean removeFromWishlist(int userId, int propertyId) throws Exception {
        String sql = "DELETE FROM wishlist WHERE user_id = ? AND property_id = ?";
        Connection conn = DBconfig.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, userId);
        pst.setInt(2, propertyId);
        int rows = pst.executeUpdate();
        pst.close();
        conn.close();
        return rows > 0;
    }
    public List<HomeModel> getWishlistByUser(int userId) throws Exception {
        List<HomeModel> wishlist = new ArrayList<>();
        String sql =
            "SELECT p.property_id, p.name, p.location, p.price " +
            "FROM wishlist w " +
            "JOIN property p ON w.property_id = p.property_id " +
            "WHERE w.user_id = ? " +
            "ORDER BY w.created_at DESC";
        Connection conn = DBconfig.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, userId);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            HomeModel home = new HomeModel();
            home.setId(rs.getInt("property_id"));
            home.setName(rs.getString("name"));
            home.setLocation(rs.getString("location"));
            home.setPrice(rs.getDouble("price"));
            wishlist.add(home);
        }
        rs.close();
        pst.close();
        conn.close();
        return wishlist;
    }
    
    public int getTotalBookingsCount() throws Exception {
        try {
            Connection con = DBconfig.getConnection();
            String sql = "SELECT COUNT(*) FROM booking";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            int count = 0;
            if (rs.next()) count = rs.getInt(1);
            rs.close(); st.close(); con.close();
            return count;
        } catch (Exception e) { return 0; }
    }

    public int getCompletedBookingsCount() throws Exception {
        try {
            Connection con = DBconfig.getConnection();
            String sql = "SELECT COUNT(*) FROM booking WHERE booking_status = 'COMPLETED'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            int count = 0;
            if (rs.next()) count = rs.getInt(1);
            rs.close(); st.close(); con.close();
            return count;
        } catch (Exception e) { return 0; }
    }

    public int getTotalRegularUsersCount() throws Exception {
        try {
            Connection con = DBconfig.getConnection();
            String sql = "SELECT COUNT(*) FROM users WHERE role = 'USER'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            int count = 0;
            if (rs.next()) count = rs.getInt(1);
            rs.close(); st.close(); con.close();
            return count;
        } catch (Exception e) { return 0; }
    }

    public List<String[]> getMonthlyRevenue() throws Exception {
        try {
            Connection con = DBconfig.getConnection();
            String sql =
                "SELECT DATE_FORMAT(created_at, '%b %y') AS lbl, " +
                "       YEAR(created_at) AS yr, " +
                "       MONTH(created_at) AS mo, " +
                "       COALESCE(SUM(total_price), 0) AS val " +
                "FROM booking " +
                "WHERE booking_status = 'COMPLETED' " +
                "  AND created_at >= DATE_SUB(NOW(), INTERVAL 12 MONTH) " +
                "GROUP BY yr, mo, lbl " +
                "ORDER BY yr ASC, mo ASC";
            return buildSeries(con, sql);
        } catch (Exception e) { return new ArrayList<>(); }
    }

    public List<String[]> getMonthlyUserRegistrations() throws Exception {
        try {
            Connection con = DBconfig.getConnection();
            String sql =
                "SELECT DATE_FORMAT(created_at, '%b %y') AS lbl, " +
                "       YEAR(created_at) AS yr, " +
                "       MONTH(created_at) AS mo, " +
                "       COUNT(*) AS val " +
                "FROM users " +
                "WHERE created_at >= DATE_SUB(NOW(), INTERVAL 12 MONTH) " +
                "GROUP BY yr, mo, lbl " +
                "ORDER BY yr ASC, mo ASC";
            return buildSeries(con, sql);
        } catch (Exception e) { return new ArrayList<>(); }
    }

    public List<String[]> getMonthlyPropertyRegistrations() throws Exception {
        try {
            Connection con = DBconfig.getConnection();
            String sql =
                "SELECT DATE_FORMAT(created_at, '%b %y') AS lbl, " +
                "       YEAR(created_at) AS yr, " +
                "       MONTH(created_at) AS mo, " +
                "       COUNT(*) AS val " +
                "FROM property " +
                "WHERE created_at >= DATE_SUB(NOW(), INTERVAL 12 MONTH) " +
                "GROUP BY yr, mo, lbl " +
                "ORDER BY yr ASC, mo ASC";
            return buildSeries(con, sql);
        } catch (Exception e) { return new ArrayList<>(); }
    }

    public List<String[]> getMonthlyBookingCount() throws Exception {
        try {
            Connection con = DBconfig.getConnection();
            String sql =
                "SELECT DATE_FORMAT(created_at, '%b %y') AS lbl, " +
                "       YEAR(created_at) AS yr, " +
                "       MONTH(created_at) AS mo, " +
                "       COUNT(*) AS val " +
                "FROM booking " +
                "WHERE created_at >= DATE_SUB(NOW(), INTERVAL 12 MONTH) " +
                "GROUP BY yr, mo, lbl " +
                "ORDER BY yr ASC, mo ASC";
            return buildSeries(con, sql);
        } catch (Exception e) { return new ArrayList<>(); }
    }

    private List<String[]> buildSeries(Connection con, String sql) throws Exception {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        List<String[]> raw = new ArrayList<>();
        long maxVal = 1;
        while (rs.next()) {
            String lbl = rs.getString("lbl");
            long   val = rs.getLong("val");
            if (val > maxVal) maxVal = val;
            raw.add(new String[]{ lbl, String.valueOf(val), "0" });
        }
        rs.close(); st.close(); con.close();
        for (String[] row : raw) {
            long v = Long.parseLong(row[1]);
            int pct = (int) Math.round((double) v * 100 / maxVal);
            if (pct < 4 && v > 0) pct = 4;
            row[2] = String.valueOf(pct);
        }
        return raw;
    }

    public List<String[]> getBookingStatusBreakdown() throws Exception {
        try {
            Connection con = DBconfig.getConnection();
            String sql =
                "SELECT booking_status, COUNT(*) AS cnt " +
                "FROM booking " +
                "GROUP BY booking_status " +
                "ORDER BY cnt DESC";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            List<String[]> rows = new ArrayList<>();
            int total = 0;
            while (rs.next()) {
                int cnt = rs.getInt("cnt");
                total += cnt;
                rows.add(new String[]{ rs.getString("booking_status"), String.valueOf(cnt), "0" });
            }
            rs.close(); st.close(); con.close();
            for (String[] row : rows) {
                int v   = Integer.parseInt(row[1]);
                int pct = total > 0 ? (int) Math.round((double) v * 100 / total) : 0;
                if (pct < 4 && v > 0) pct = 4;
                row[2] = String.valueOf(pct);
            }
            return rows;
        } catch (Exception e) { return new ArrayList<>(); }
    }

    public List<String[]> getPropertyStatusBreakdown() throws Exception {
        try {
            Connection con = DBconfig.getConnection();
            String sql =
                "SELECT approval_status, COUNT(*) AS cnt " +
                "FROM property " +
                "GROUP BY approval_status " +
                "ORDER BY cnt DESC";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            List<String[]> rows = new ArrayList<>();
            int total = 0;
            while (rs.next()) {
                int cnt = rs.getInt("cnt");
                total += cnt;
                rows.add(new String[]{ rs.getString("approval_status"), String.valueOf(cnt), "0" });
            }
            rs.close(); st.close(); con.close();
            for (String[] row : rows) {
                int v   = Integer.parseInt(row[1]);
                int pct = total > 0 ? (int) Math.round((double) v * 100 / total) : 0;
                if (pct < 4 && v > 0) pct = 4;
                row[2] = String.valueOf(pct);
            }
            return rows;
        } catch (Exception e) { return new ArrayList<>(); }
    }

    public List<String[]> getTopLocations() throws Exception {
        try {
            Connection con = DBconfig.getConnection();
            String sql =
                "SELECT location, COUNT(*) AS cnt " +
                "FROM property " +
                "WHERE approval_status = 'APPROVED' " +
                "GROUP BY location " +
                "ORDER BY cnt DESC " +
                "LIMIT 5";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            List<String[]> raw = new ArrayList<>();
            int maxCnt = 1;
            while (rs.next()) {
                int cnt = rs.getInt("cnt");
                if (cnt > maxCnt) maxCnt = cnt;
                raw.add(new String[]{ rs.getString("location"), String.valueOf(cnt), "0" });
            }
            rs.close(); st.close(); con.close();
            for (String[] row : raw) {
                int v = Integer.parseInt(row[1]);
                int pct = (int) Math.round((double) v * 100 / maxCnt);
                if (pct < 4 && v > 0) pct = 4;
                row[2] = String.valueOf(pct);
            }
            return raw;
        } catch (Exception e) { return new ArrayList<>(); }
    }
}