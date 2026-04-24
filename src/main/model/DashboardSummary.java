package com.homerental.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class DashboardSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private BigDecimal totalRevenue;
    private Integer activeProperties;
    private Integer totalUsers;
    private Integer activeDealers;
    private Integer pendingApplications;
    private Integer pendingProperties;
    
    public DashboardSummary() {}

    public BigDecimal getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(BigDecimal totalRevenue) { this.totalRevenue = totalRevenue; }
    
    public Integer getActiveProperties() { return activeProperties; }
    public void setActiveProperties(Integer activeProperties) { this.activeProperties = activeProperties; }
    
    public Integer getTotalUsers() { return totalUsers; }
    public void setTotalUsers(Integer totalUsers) { this.totalUsers = totalUsers; }
    
    public Integer getActiveDealers() { return activeDealers; }
    public void setActiveDealers(Integer activeDealers) { this.activeDealers = activeDealers; }
    
    public Integer getPendingApplications() { return pendingApplications; }
    public void setPendingApplications(Integer pendingApplications) { this.pendingApplications = pendingApplications; }
    
    public Integer getPendingProperties() { return pendingProperties; }
    public void setPendingProperties(Integer pendingProperties) { this.pendingProperties = pendingProperties; }
}