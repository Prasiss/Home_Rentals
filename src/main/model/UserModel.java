package com.HomeRental.model;

public class UserModel {

    private int userId;
    private String fullName;
    private String userName;
    private String email;
    private String number;
    private String password;
    private String role;
    private int isApproved;
    private String address;
    private String profileImage;

    public UserModel() {}

    /**
     * Gets user ID.
     */
    public int getUserId() { return userId; }

    /**
     * Sets user ID.
     */
    public void setUserId(int userId) { this.userId = userId; }

    /**
     * Gets full name.
     */
    public String getFullName() { return fullName; }

    /**
     * Sets full name.
     */
    public void setFullName(String fullName) { this.fullName = fullName; }

    /**
     * Gets username.
     */
    public String getUserName() { return userName; }

    /**
     * Sets username.
     */
    public void setUserName(String userName) { this.userName = userName; }

    /**
     * Gets email.
     */
    public String getEmail() { return email; }

    /**
     * Sets email.
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Gets phone number.
     */
    public String getNumber() { return number; }

    /**
     * Sets phone number.
     */
    public void setNumber(String number) { this.number = number; }

    /**
     * Gets password.
     */
    public String getPassword() { return password; }

    /**
     * Sets password.
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Gets role (ADMIN/USER/DEALER).
     */
    public String getRole() { return role; }

    /**
     * Sets role.
     */
    public void setRole(String role) { this.role = role; }

    /**
     * Gets approval status as integer (0/1).
     */
    public int getIsApproved() { return isApproved; }

    /**
     * Sets approval status.
     */
    public void setIsApproved(int isApproved) { this.isApproved = isApproved; }

    /**
     * Checks if user is approved.
     */
    public boolean isApproved() { return isApproved == 1; }

    /**
     * Gets address.
     */
    public String getAddress() { return address; }

    /**
     * Sets address.
     */
    public void setAddress(String address) { this.address = address; }

    /**
     * Gets profile image filename/path.
     */
    public String getProfileImage() { return profileImage; }

    /**
     * Sets profile image filename/path.
     */
    public void setProfileImage(String profileImage) { this.profileImage = profileImage; }
}