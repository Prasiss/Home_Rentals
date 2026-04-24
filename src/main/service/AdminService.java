package com.homerental.service;

import com.homerental.dao.*;
import com.homerental.model.*;
import java.util.*;

public class AdminService {
    
    private final UserDao userDao = new UserDao();
    private final PropertyDao propertyDao = new PropertyDao();
    private final ApplicationDao applicationDao = new ApplicationDao();
    private final DashboardDao dashboardDao = new DashboardDao();
    
    // Dashboard
    public DashboardSummary getDashboardSummary() { return dashboardDao.fetchDashboardStatistics(); }
    
    // Users
    public List<User> getAllUsers() { return userDao.findAllUsers(); }
    public List<User> getRecentUsers() { return userDao.findRecentUsers(5); }
    public User getUserById(Integer id) { return userDao.findById(id); }
    public boolean suspendUser(Integer id) { return userDao.suspendUser(id); }
    public boolean reactivateUser(Integer id) { return userDao.reactivateUser(id); }
    public boolean deleteUser(Integer id) { return userDao.deleteUser(id); }
    
    // Dealers
    public List<User> getAllDealers() { return userDao.findAllDealers(); }
    public List<User> getRecentDealers() {
        List<User> all = userDao.findAllDealers();
        List<User> recent = new ArrayList<>();
        for (int i = 0; i < Math.min(4, all.size()); i++) recent.add(all.get(i));
        return recent;
    }
    public boolean suspendDealer(Integer id) { return userDao.suspendDealer(id); }
    public boolean reactivateDealer(Integer id) { return userDao.reactivateDealer(id); }
    public boolean deleteDealer(Integer id) { return userDao.deleteDealer(id); }
    
    // Properties
    public List<Property> getPendingProperties() { return propertyDao.findPendingProperties(); }
    public List<Property> getPropertiesByStatus(String status) { return propertyDao.findPropertiesByStatus(status); }
    public boolean approveProperty(Integer id) { return propertyDao.updateApprovalStatus(id, "APPROVED"); }
    public boolean rejectProperty(Integer id) { return propertyDao.updateApprovalStatus(id, "REJECTED"); }
    
    // Applications
    public List<DealerApplication> getPendingApplications() { return applicationDao.findPendingApplications(); }
    public List<DealerApplication> getApplicationsByStatus(String status) { return applicationDao.findApplicationsByStatus(status); }
    public boolean approveApplication(Integer appId, Integer reviewerId) { return applicationDao.updateApplicationStatus(appId, "APPROVED", reviewerId); }
    public boolean rejectApplication(Integer appId, Integer reviewerId) { return applicationDao.updateApplicationStatus(appId, "REJECTED", reviewerId); }
}