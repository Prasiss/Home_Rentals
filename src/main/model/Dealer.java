package com.homerental.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Dealer implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer dealerId;
    private Integer userId;
    private String fullName;
    private String companyName;
    private String emailAddress;
    private String phoneNumber;
    private String verificationStatus;
    private Integer propertyCount;
    private BigDecimal averageRating;
    private Timestamp joinedDate;
    
    public Dealer() {}
    
    public Integer getDealerId() { return dealerId; }
    public void setDealerId(Integer dealerId) { this.dealerId = dealerId; }
    
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    
    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    public String getVerificationStatus() { return verificationStatus; }
    public void setVerificationStatus(String verificationStatus) { this.verificationStatus = verificationStatus; }
    
    public Integer getPropertyCount() { return propertyCount; }
    public void setPropertyCount(Integer propertyCount) { this.propertyCount = propertyCount; }
    
    public BigDecimal getAverageRating() { return averageRating; }
    public void setAverageRating(BigDecimal averageRating) { this.averageRating = averageRating; }
    
    public Timestamp getJoinedDate() { return joinedDate; }
    public void setJoinedDate(Timestamp joinedDate) { this.joinedDate = joinedDate; }
}