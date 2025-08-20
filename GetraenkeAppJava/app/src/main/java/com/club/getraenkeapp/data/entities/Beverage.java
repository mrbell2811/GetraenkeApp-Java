package com.club.getraenkeapp.data.entities;

import java.util.Objects;

/**
 * Beverage entity representing available drinks in the database.
 * 
 * Each beverage has a unique ID, name, price, optional category for organization,
 * and active status to enable/disable without deletion.
 */
public class Beverage {
    
    public static final String TABLE_NAME = "beverages";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_ACTIVE = "active";
    
    // Create table SQL
    public static final String CREATE_TABLE = 
        "CREATE TABLE " + TABLE_NAME + " (" +
        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        COLUMN_NAME + " TEXT NOT NULL, " +
        COLUMN_PRICE + " REAL NOT NULL, " +
        COLUMN_CATEGORY + " TEXT, " +
        COLUMN_ACTIVE + " INTEGER DEFAULT 1" +
        ")";
    
    private long id;
    private String name;
    private double price;
    private String category;
    private boolean active;
    
    // Default constructor
    public Beverage() {
        this.active = true;
    }
    
    // Constructor without ID (for new beverages)
    public Beverage(String name, double price, String category) {
        this();
        this.name = name;
        this.price = price;
        this.category = category;
    }
    
    // Constructor without ID and category
    public Beverage(String name, double price) {
        this(name, price, null);
    }
    
    // Full constructor
    public Beverage(long id, String name, double price, String category, boolean active) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.active = active;
    }
    
    // Getters
    public long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getCategory() {
        return category;
    }
    
    public boolean isActive() {
        return active;
    }
    
    // Setters
    public void setId(long id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    /**
     * Get formatted price string for display
     */
    public String getFormattedPrice() {
        return String.format("%.2f €", price);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Beverage beverage = (Beverage) obj;
        return id == beverage.id && 
               Double.compare(beverage.price, price) == 0 &&
               active == beverage.active &&
               Objects.equals(name, beverage.name) &&
               Objects.equals(category, beverage.category);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, category, active);
    }
    
    @Override
    public String toString() {
        return "Beverage{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", price=" + price +
               ", category='" + category + '\'' +
               ", active=" + active +
               '}';
    }
}