package com.homerental.dao;

import com.homerental.model.Property;
import com.homerental.util.DatabaseConnection;
import java.sql.*;
import java.math.BigDecimal;
import java.util.*;

public class PropertyDao {
    
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        return DatabaseConnection.getConnection();
    }
    
    public List<Property> findPendingProperties() {
        List<Property> list = new ArrayList<>();
        String sql = "SELECT p.Property_No, p.Title, p.Description, p.Price, p.Location, " +
                     "p.Availability, p.AverageRating, p.Dealer_No, u.Full_Name as Dealer_Name, " +
                     "p.Approval_Status, p.Date_Added " +
                     "FROM Property p JOIN User u ON p.Dealer_No = u.User_No " +
                     "WHERE p.Approval_Status = 'PENDING' ORDER BY p.Date_Added DESC";
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Property p = new Property();
                p.setPropertyNo(rs.getInt("Property_No"));
                p.setTitle(rs.getString("Title"));
                p.setDescription(rs.getString("Description"));
                p.setPrice(rs.getBigDecimal("Price"));
                p.setLocation(rs.getString("Location"));
                p.setAvailability(rs.getBoolean("Availability"));
                BigDecimal r = rs.getBigDecimal("AverageRating");
                p.setAverageRating(r != null ? r : BigDecimal.ZERO);
                p.setDealerNo(rs.getInt("Dealer_No"));
                p.setDealerName(rs.getString("Dealer_Name"));
                p.setApprovalStatus(rs.getString("Approval_Status"));
                Timestamp d = rs.getTimestamp("Date_Added");
                if (d != null) p.setDateAdded(d);
                list.add(p);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    
    public List<Property> findPropertiesByStatus(String status) {
        List<Property> list = new ArrayList<>();
        String sql = "SELECT p.Property_No, p.Title, p.Description, p.Price, p.Location, " +
                     "p.Availability, p.AverageRating, p.Dealer_No, u.Full_Name as Dealer_Name, " +
                     "p.Approval_Status, p.Date_Added " +
                     "FROM Property p JOIN User u ON p.Dealer_No = u.User_No " +
                     "WHERE p.Approval_Status = ? ORDER BY p.Date_Added DESC";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Property p = new Property();
                p.setPropertyNo(rs.getInt("Property_No"));
                p.setTitle(rs.getString("Title"));
                p.setDescription(rs.getString("Description"));
                p.setPrice(rs.getBigDecimal("Price"));
                p.setLocation(rs.getString("Location"));
                p.setAvailability(rs.getBoolean("Availability"));
                BigDecimal r = rs.getBigDecimal("AverageRating");
                p.setAverageRating(r != null ? r : BigDecimal.ZERO);
                p.setDealerNo(rs.getInt("Dealer_No"));
                p.setDealerName(rs.getString("Dealer_Name"));
                p.setApprovalStatus(rs.getString("Approval_Status"));
                Timestamp d = rs.getTimestamp("Date_Added");
                if (d != null) p.setDateAdded(d);
                list.add(p);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    
    public boolean updateApprovalStatus(Integer propertyNo, String newStatus) {
        String sql = "UPDATE Property SET Approval_Status = ? WHERE Property_No = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, propertyNo);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
    
    public int countActiveProperties() {
        String sql = "SELECT COUNT(*) FROM Property WHERE Approval_Status = 'APPROVED' AND Availability = TRUE";
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }
    
    public int countPendingProperties() {
        String sql = "SELECT COUNT(*) FROM Property WHERE Approval_Status = 'PENDING'";
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }
}