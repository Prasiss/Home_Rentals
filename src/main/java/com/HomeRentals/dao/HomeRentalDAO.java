package com.HomeRental.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.HomeRental.config.DBconfig;
import com.HomeRental.model.HomeModel;
import com.HomeRental.model.PropertyModel;
import com.HomeRental.model.UserModel;

public class HomeRentalDAO {

    // ==================== INSERT OPERATIONS ====================

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

    public boolean rejectDealerRequest(int userId) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE users SET is_approved = 1 WHERE user_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, userId);
        int rows = pst.executeUpdate();
        pst.close();
        con.close();
        return rows > 0;
    }

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

    public boolean deleteUser(int userId) throws Exception {
        return deactivateUser(userId);
    }

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
        try {
            user.setProfileImage(rs.getString("profile_image"));
        } catch (SQLException e) {
            user.setProfileImage(null);
        }
        return user;
    }

    private HomeModel mapHome(ResultSet rs) throws SQLException {
        HomeModel home = new HomeModel();
        home.setId(rs.getInt("property_id"));
        home.setName(rs.getString("title"));
        home.setLocation(rs.getString("location"));
        home.setPrice(rs.getDouble("price_per_month"));
        home.setDescription(rs.getString("description"));
        return home;
    }

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
    
    public int getTotalBookingsCount() throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "SELECT COUNT(*) FROM booking";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        int count = 0;
        if (rs.next()) count = rs.getInt(1);
        rs.close(); st.close(); con.close();
        return count;
    }

    public int getCompletedBookingsCount() throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "SELECT COUNT(*) FROM booking WHERE booking_status = 'COMPLETED'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        int count = 0;
        if (rs.next()) count = rs.getInt(1);
        rs.close(); st.close(); con.close();
        return count;
    }

    public int getTotalRegularUsersCount() throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "SELECT COUNT(*) FROM users WHERE role = 'USER'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        int count = 0;
        if (rs.next()) count = rs.getInt(1);
        rs.close(); st.close(); con.close();
        return count;
    }

    public List<String[]> getMonthlyRevenue() throws Exception {
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
    }

    public List<String[]> getMonthlyUserRegistrations() throws Exception {
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
    }

    public List<String[]> getMonthlyPropertyRegistrations() throws Exception {
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
    }

    public List<String[]> getMonthlyBookingCount() throws Exception {
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

  
    /**
     * Returns booking status counts as List of String[]{ status, count, pct }.
     * Percentage is relative to the status with the highest count.
     */
    public List<String[]> getBookingStatusBreakdown() throws Exception {
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
    }

    /**
     * Returns property approval counts as List of String[]{ status, count, pct }.
     */
    public List<String[]> getPropertyStatusBreakdown() throws Exception {
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
    }

    public List<String[]> getTopLocations() throws Exception {
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
    }

}