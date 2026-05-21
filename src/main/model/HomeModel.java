package com.HomeRental.model;

public class HomeModel {

    private int id;
    private String name;
    private String location;
    private double price;
    private String description;

    public HomeModel() {}

    public HomeModel(int id, String name, String location, double price, String description) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.price = price;
        this.description = description;
    }

    /**
     * Gets home ID.
     */
    public int getId() { return id; }

    /**
     * Sets home ID.
     */
    public void setId(int id) { this.id = id; }

    /**
     * Gets home name.
     */
    public String getName() { return name; }

    /**
     * Sets home name.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Gets location.
     */
    public String getLocation() { return location; }

    /**
     * Sets location.
     */
    public void setLocation(String location) { this.location = location; }

    /**
     * Gets price.
     */
    public double getPrice() { return price; }

    /**
     * Sets price.
     */
    public void setPrice(double price) { this.price = price; }

    /**
     * Gets description.
     */
    public String getDescription() { return description; }

    /**
     * Sets description.
     */
    public void setDescription(String description) { this.description = description; }
}