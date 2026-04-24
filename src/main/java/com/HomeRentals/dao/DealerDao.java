package com.homerental.dao;

import com.homerental.model.Dealer;
import com.homerental.util.DatabaseConnection;
import java.sql.*;
import java.util.*;

public class DealerDao {
    
    private Connection obtainConnection() throws SQLException, ClassNotFoundException {
        return DatabaseConnection.getConnection();
    }
    
    public List<Dealer> findAllDealers() {
        List<Dealer> dealers = new ArrayList<>();
        String query = "SELECT d.dealer_id, d.user_id, u.full_name, d.company_name, " +
                      "u.email_address, u.phone_number, d.verification_status, " +
                      "(SELECT COUNT(*) FROM properties WHERE dealer_id = d.dealer_id) as property_count, " +
                      "COALESCE(d.average_rating, 0) as avg_rating, d.joined_date " +
                      "FROM dealers d JOIN users u ON d.user_id = u.user_id " +
                      "ORDER BY d.joined_date DESC";
        
        try (Connection conn = obtainConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                dealers.add(mapResultSetToDealer(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dealers;
    }
    
    public List<Dealer> findRecentDealers(int limit) {
        List<Dealer> dealers = new ArrayList<>();
        String query = "SELECT d.dealer_id, d.user_id, u.full_name, d.company_name, " +
                      "u.email_address, u.phone_number, d.verification_status, " +
                      "(SELECT COUNT(*) FROM properties WHERE dealer_id = d.dealer_id) as property_count, " +
                      "COALESCE(d.average_rating, 0) as avg_rating, d.joined_date " +
                      "FROM dealers d JOIN users u ON d.user_id = u.user_id " +
                      "ORDER BY d.joined_date DESC LIMIT ?";
        
        try (Connection conn = obtainConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, limit);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                dealers.add(mapResultSetToDealer(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dealers;
    }
    
    public boolean updateVerificationStatus(Integer dealerId, String newStatus) {
        String query = "UPDATE dealers SET verification_status = ? WHERE dealer_id = ?";
        
        try (Connection conn = obtainConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, newStatus);
            stmt.setInt(2, dealerId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int countActiveDealers() {
        String query = "SELECT COUNT(*) FROM dealers WHERE verification_status = 'VERIFIED'";
        
        try (Connection conn = obtainConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    private Dealer mapResultSetToDealer(ResultSet rs) throws SQLException {
        Dealer dealer = new Dealer();
        dealer.setDealerId(rs.getInt("dealer_id"));
        dealer.setUserId(rs.getInt("user_id"));
        dealer.setFullName(rs.getString("full_name"));
        dealer.setCompanyName(rs.getString("company_name"));
        dealer.setEmailAddress(rs.getString("email_address"));
        dealer.setPhoneNumber(rs.getString("phone_number"));
        dealer.setVerificationStatus(rs.getString("verification_status"));
        dealer.setPropertyCount(rs.getInt("property_count"));
        dealer.setAverageRating(rs.getBigDecimal("avg_rating"));
        dealer.setJoinedDate(rs.getTimestamp("joined_date"));
        return dealer;
    }
}