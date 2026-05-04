package com.HomeRentals.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int userId;
    private String fullName;
    private String username;
    private String email;
    private String password;
    private String role;
    private String phone;
    private String profileImage;
    private String address;
    private String status;
    private Timestamp createdAt;
    private int totalBookings;
    private int propertyCount;
    private int roleId;
    private String roleName;
    private String dealerRequest;  
    private String companyName;
    private String yearsExperience;
    private String propertiesCount;
    private String aboutBusiness;

    public User() {}

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getProfileImage() { return profileImage; }
    public void setProfileImage(String profileImage) { this.profileImage = profileImage; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    public int getTotalBookings() { return totalBookings; }
    public void setTotalBookings(int totalBookings) { this.totalBookings = totalBookings; }
    public int getPropertyCount() { return propertyCount; }
    public void setPropertyCount(int propertyCount) { this.propertyCount = propertyCount; }
    public int getRoleId() { return roleId; }
    public void setRoleId(int roleId) { this.roleId = roleId; }
    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }
    public String getDealerRequest() { return dealerRequest; }
    public void setDealerRequest(String dealerRequest) { this.dealerRequest = dealerRequest; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getYearsExperience() { return yearsExperience; }
    public void setYearsExperience(String yearsExperience) { this.yearsExperience = yearsExperience; }

    public String getPropertiesCount() { return propertiesCount; }
    public void setPropertiesCount(String propertiesCount) { this.propertiesCount = propertiesCount; }

    public String getAboutBusiness() { return aboutBusiness; }
    public void setAboutBusiness(String aboutBusiness) { this.aboutBusiness = aboutBusiness; }
}

	// Booking Model

class Booking implements Serializable {
    private static final long serialVersionUID = 1L;

    private int bookingId;
    private int userId;
    private int propertyId;
    private Date checkInDate;
    private Date checkOutDate;
    private double totalPrice;
    private String bookingStatus;
    private String propertyTitle;

    public Booking() {}

    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getPropertyId() { return propertyId; }
    public void setPropertyId(int propertyId) { this.propertyId = propertyId; }
    public Date getCheckInDate() { return checkInDate; }
    public void setCheckInDate(Date checkInDate) { this.checkInDate = checkInDate; }
    public Date getCheckOutDate() { return checkOutDate; }
    public void setCheckOutDate(Date checkOutDate) { this.checkOutDate = checkOutDate; }
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public String getBookingStatus() { return bookingStatus; }
    public void setBookingStatus(String bookingStatus) { this.bookingStatus = bookingStatus; }
    public String getPropertyTitle() { return propertyTitle; }
    public void setPropertyTitle(String propertyTitle) { this.propertyTitle = propertyTitle; }
}

		//Property Model

class Property implements Serializable {
    private static final long serialVersionUID = 1L;

    private int propertyId;
    private int ownerId;
    private String title;
    private String description;
    private String location;
    private double pricePerMonth;
    private String propertyType;
    private Date availableFrom;
    private boolean isAvailable;
    private int categoryId;
    private String approvalStatus;
    private int approvedBy;
    private String categoryName;

    public Property() {}

    public int getPropertyId() { return propertyId; }
    public void setPropertyId(int propertyId) { this.propertyId = propertyId; }
    public int getOwnerId() { return ownerId; }
    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public double getPricePerMonth() { return pricePerMonth; }
    public void setPricePerMonth(double pricePerMonth) { this.pricePerMonth = pricePerMonth; }
    public String getPropertyType() { return propertyType; }
    public void setPropertyType(String propertyType) { this.propertyType = propertyType; }
    public Date getAvailableFrom() { return availableFrom; }
    public void setAvailableFrom(Date availableFrom) { this.availableFrom = availableFrom; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean isAvailable) { this.isAvailable = isAvailable; }
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public String getApprovalStatus() { return approvalStatus; }
    public void setApprovalStatus(String approvalStatus) { this.approvalStatus = approvalStatus; }
    public int getApprovedBy() { return approvedBy; }
    public void setApprovedBy(int approvedBy) { this.approvedBy = approvedBy; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    
    
}

// Message Model

class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private int messageId;
    private int senderId;
    private int receiverId;
    private String messageText;
    private String senderName;

    public Message() {}

    public int getMessageId() { return messageId; }
    public void setMessageId(int messageId) { this.messageId = messageId; }
    public int getSenderId() { return senderId; }
    public void setSenderId(int senderId) { this.senderId = senderId; }
    public int getReceiverId() { return receiverId; }
    public void setReceiverId(int receiverId) { this.receiverId = receiverId; }
    public String getMessageText() { return messageText; }
    public void setMessageText(String messageText) { this.messageText = messageText; }
    public String getSenderName() { return senderName; }
    public void setSenderName(String senderName) { this.senderName = senderName; }
}

		//Wishlist Model

class Wishlist implements Serializable {
    private static final long serialVersionUID = 1L;

    private int userId;
    private int propertyId;

    public Wishlist() {}

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getPropertyId() { return propertyId; }
    public void setPropertyId(int propertyId) { this.propertyId = propertyId; }
}