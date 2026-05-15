package com.HomeRentals.model;

public class PropertyModel {
    private int    propertyId;
    private String title;
    private String location;
    private double pricePerMonth;
    private String description;
    private int    ownerId;
    private String ownerName;
    private String approvalStatus;
    private int    isAvailable;

    public PropertyModel() {}

    public int    getPropertyId()      { return propertyId; }
    public void   setPropertyId(int propertyId) { this.propertyId = propertyId; }

    public String getTitle()           { return title; }
    public void   setTitle(String t)   { this.title = t; }

    public String getLocation()        { return location; }
    public void   setLocation(String l){ this.location = l; }

    public double getPricePerMonth()   { return pricePerMonth; }
    public void   setPricePerMonth(double p) { this.pricePerMonth = p; }

    public String getDescription()     { return description; }
    public void   setDescription(String d) { this.description = d; }

    public int    getOwnerId()         { return ownerId; }
    public void   setOwnerId(int id)   { this.ownerId = id; }

    public String getOwnerName()       { return ownerName; }
    public void   setOwnerName(String n) { this.ownerName = n; }

    public String getApprovalStatus()  { return approvalStatus; }
    public void   setApprovalStatus(String s) { this.approvalStatus = s; }

    public int    getIsAvailable()     { return isAvailable; }
    public void   setIsAvailable(int a){ this.isAvailable = a; }
}
