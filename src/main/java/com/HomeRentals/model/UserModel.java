package com.HomeRentals.model;

public class UserModel {

    private int    userId;
    private String fullName;
    private String userName;       // DB col: username
    private String email;
    private String number;         // DB col: phone_number
    private String password;
    private String role;           // DB col: role  — 'ADMIN' | 'DEALER' | 'USER'
    private int    isApproved;     // DB col: is_approved — 0 or 1

    public UserModel() {}

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    /** Maps to DB column: phone_number */
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    /** Role string: 'ADMIN', 'DEALER', or 'USER' */
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    /** Maps to DB column: is_approved (1 = approved, 0 = pending/inactive) */
    public int getIsApproved() { return isApproved; }
    public void setIsApproved(int isApproved) { this.isApproved = isApproved; }

    /** Convenience: returns true if is_approved = 1 */
    public boolean isApproved() { return isApproved == 1; }
}
