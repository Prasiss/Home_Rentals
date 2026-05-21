package com.HomeRental.model;

public class PropertyModel {

    private int propertyId;
    private String title;
    private String location;
    private double pricePerMonth;
    private String description;
    private int ownerId;
    private String ownerName;
    private String approvalStatus;
    private int isAvailable;

    public PropertyModel() {}

    /**
     * Gets property ID.
     */
    public int getPropertyId() { return propertyId; }

    /**
     * Sets property ID.
     */
    public void setPropertyId(int propertyId) { this.propertyId = propertyId; }

    /**
     * Gets property title.
     */
    public String getTitle() { return title; }

    /**
     * Sets property title.
     */
    public void setTitle(String t) { this.title = t; }

    /**
     * Gets location.
     */
    public String getLocation() { return location; }

    /**
     * Sets location.
     */
    public void setLocation(String l) { this.location = l; }

    /**
     * Gets monthly price.
     */
    public double getPricePerMonth() { return pricePerMonth; }

    /**
     * Sets monthly price.
     */
    public void setPricePerMonth(double p) { this.pricePerMonth = p; }

    /**
     * Gets description.
     */
    public String getDescription() { return description; }

    /**
     * Sets description.
     */
    public void setDescription(String d) { this.description = d; }

    /**
     * Gets owner ID.
     */
    public int getOwnerId() { return ownerId; }

    /**
     * Sets owner ID.
     */
    public void setOwnerId(int id) { this.ownerId = id; }

    /**
     * Gets owner name.
     */
    public String getOwnerName() { return ownerName; }

    /**
     * Sets owner name.
     */
    public void setOwnerName(String n) { this.ownerName = n; }

    /**
     * Gets approval status.
     */
    public String getApprovalStatus() { return approvalStatus; }

    /**
     * Sets approval status.
     */
    public void setApprovalStatus(String s) { this.approvalStatus = s; }

    /**
     * Gets availability status (0/1).
     */
    public int getIsAvailable() { return isAvailable; }

    /**
     * Sets availability status.
     */
    public void setIsAvailable(int a) { this.isAvailable = a; }
}