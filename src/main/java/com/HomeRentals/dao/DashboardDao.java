package com.homerental.dao;

import com.homerental.model.DashboardSummary;
import com.homerental.util.DatabaseConnection;
import java.sql.*;
import java.math.BigDecimal;

public class DashboardDao {
    
    private final UserDao userDao = new UserDao();
    private final PropertyDao propertyDao = new PropertyDao();
    private final ApplicationDao applicationDao = new ApplicationDao();
    
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        return DatabaseConnection.getConnection();
    }
    
    public DashboardSummary fetchDashboardStatistics() {
        DashboardSummary s = new DashboardSummary();
        try {
            String sql = "SELECT COALESCE(SUM(p.Amount), 0) FROM Payment p WHERE p.Payment_Status = 'PAID'";
            try (Connection conn = getConnection();
                 Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {
                if (rs.next()) s.setTotalRevenue(rs.getBigDecimal(1));
            }
            s.setActiveProperties(propertyDao.countActiveProperties());
            s.setTotalUsers(userDao.countTotalUsers());
            s.setActiveDealers(userDao.countActiveDealers());
            s.setPendingApplications(applicationDao.countPendingApplications());
            s.setPendingProperties(propertyDao.countPendingProperties());
        } catch (Exception e) {
            e.printStackTrace();
            s.setTotalRevenue(BigDecimal.ZERO);
            s.setActiveProperties(0);
            s.setTotalUsers(0);
            s.setActiveDealers(0);
            s.setPendingApplications(0);
            s.setPendingProperties(0);
        }
        return s;
    }
}