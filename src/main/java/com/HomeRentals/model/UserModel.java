package com.HomeRental.model;

public class UserModel {

    private int    userId;
    private String fullName;
    private String userName;
    private String email;
    private String number;
    private String password;
    private String role;
    private int    isApproved;
    private String profileImage; 

    public UserModel() {}

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public int getIsApproved() { return isApproved; }
    public void setIsApproved(int isApproved) { this.isApproved = isApproved; }

    public boolean isApproved() { return isApproved == 1; }

    public String getProfileImage() { return profileImage; }
    public void setProfileImage(String profileImage) { this.profileImage = profileImage; }
}