package com.homerental.dao;

import com.homerental.model.DealerApplication;
import com.homerental.util.DatabaseConnection;
import java.sql.*;
import java.util.*;

public class ApplicationDao {
    
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        return DatabaseConnection.getConnection();
    }
    
    public List<DealerApplication> findPendingApplications() {
        List<DealerApplication> list = new ArrayList<>();
        String sql = "SELECT a.Application_No, a.User_No, u.Full_Name as Applicant_Name, " +
                     "u.Email as Applicant_Email, u.Phone as Applicant_Phone, " +
                     "a.Company_Name, a.Business_License, a.Years_Experience, " +
                     "a.Properties_Count, a.Property_Types, a.About_Business, " +
                     "a.Application_Status, a.Submitted_Date " +
                     "FROM Dealer_Application a JOIN User u ON a.User_No = u.User_No " +
                     "WHERE a.Application_Status = 'PENDING' ORDER BY a.Submitted_Date DESC";
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                DealerApplication a = new DealerApplication();
                a.setApplicationNo(rs.getInt("Application_No"));
                a.setUserNo(rs.getInt("User_No"));
                a.setApplicantName(rs.getString("Applicant_Name"));
                a.setApplicantEmail(rs.getString("Applicant_Email"));
                a.setApplicantPhone(rs.getString("Applicant_Phone"));
                a.setCompanyName(rs.getString("Company_Name"));
                a.setBusinessLicense(rs.getString("Business_License"));
                a.setYearsExperience(rs.getInt("Years_Experience"));
                a.setPropertiesCount(rs.getInt("Properties_Count"));
                a.setPropertyTypes(rs.getString("Property_Types"));
                a.setAboutBusiness(rs.getString("About_Business"));
                a.setApplicationStatus(rs.getString("Application_Status"));
                a.setSubmittedDate(rs.getTimestamp("Submitted_Date"));
                list.add(a);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    
    public List<DealerApplication> findApplicationsByStatus(String status) {
        List<DealerApplication> list = new ArrayList<>();
        String sql = "SELECT a.Application_No, a.User_No, u.Full_Name as Applicant_Name, " +
                     "u.Email as Applicant_Email, u.Phone as Applicant_Phone, " +
                     "a.Company_Name, a.Business_License, a.Years_Experience, " +
                     "a.Properties_Count, a.Property_Types, a.About_Business, " +
                     "a.Application_Status, a.Submitted_Date " +
                     "FROM Dealer_Application a JOIN User u ON a.User_No = u.User_No " +
                     "WHERE a.Application_Status = ? ORDER BY a.Submitted_Date DESC";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DealerApplication a = new DealerApplication();
                a.setApplicationNo(rs.getInt("Application_No"));
                a.setUserNo(rs.getInt("User_No"));
                a.setApplicantName(rs.getString("Applicant_Name"));
                a.setApplicantEmail(rs.getString("Applicant_Email"));
                a.setApplicantPhone(rs.getString("Applicant_Phone"));
                a.setCompanyName(rs.getString("Company_Name"));
                a.setBusinessLicense(rs.getString("Business_License"));
                a.setYearsExperience(rs.getInt("Years_Experience"));
                a.setPropertiesCount(rs.getInt("Properties_Count"));
                a.setPropertyTypes(rs.getString("Property_Types"));
                a.setAboutBusiness(rs.getString("About_Business"));
                a.setApplicationStatus(rs.getString("Application_Status"));
                a.setSubmittedDate(rs.getTimestamp("Submitted_Date"));
                list.add(a);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    
    public boolean updateApplicationStatus(Integer applicationNo, String newStatus, Integer reviewerNo) {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            
            String sql = "UPDATE Dealer_Application SET Application_Status = ?, Reviewed_By = ?, Reviewed_Date = NOW() WHERE Application_No = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, newStatus);
                ps.setInt(2, reviewerNo);
                ps.setInt(3, applicationNo);
                boolean ok = ps.executeUpdate() > 0;
                
                if (ok && "APPROVED".equals(newStatus)) {
                    String getUid = "SELECT User_No FROM Dealer_Application WHERE Application_No = ?";
                    try (PreparedStatement ps2 = conn.prepareStatement(getUid)) {
                        ps2.setInt(1, applicationNo);
                        ResultSet rs = ps2.executeQuery();
                        if (rs.next()) {
                            int uid = rs.getInt("User_No");
                            try (PreparedStatement ps3 = conn.prepareStatement("UPDATE User SET Role = 'DEALER' WHERE User_No = ?")) {
                                ps3.setInt(1, uid);
                                ps3.executeUpdate();
                            }
                        }
                    }
                }
                conn.commit();
                return ok;
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (Exception e) { e.printStackTrace(); return false; }
        finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); conn.close(); } catch (SQLException e) {}
            }
        }
    }
    
    public int countPendingApplications() {
        String sql = "SELECT COUNT(*) FROM Dealer_Application WHERE Application_Status = 'PENDING'";
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }
}