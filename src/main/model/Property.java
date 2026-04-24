package com.homerental.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Property implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer propertyNo;
    private String title;
    private String description;
    private BigDecimal price;
    private String location;
    private Boolean availability;
    private BigDecimal averageRating;
    private Integer dealerNo;
    private String dealerName;
    private String approvalStatus;
    private Timestamp dateAdded;
    
    public Property() {}

    public Integer getPropertyNo() { return propertyNo; }
    public void setPropertyNo(Integer propertyNo) { this.propertyNo = propertyNo; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public Boolean getAvailability() { return availability; }
    public void setAvailability(Boolean availability) { this.availability = availability; }
    
    public BigDecimal getAverageRating() { return averageRating; }
    public void setAverageRating(BigDecimal averageRating) { this.averageRating = averageRating; }
    
    public Integer getDealerNo() { return dealerNo; }
    public void setDealerNo(Integer dealerNo) { this.dealerNo = dealerNo; }
    
    public String getDealerName() { return dealerName; }
    public void setDealerName(String dealerName) { this.dealerName = dealerName; }
    
    public String getApprovalStatus() { return approvalStatus; }
    public void setApprovalStatus(String approvalStatus) { this.approvalStatus = approvalStatus; }
    
    public Timestamp getDateAdded() { return dateAdded; }
    public void setDateAdded(Timestamp dateAdded) { this.dateAdded = dateAdded; }
}