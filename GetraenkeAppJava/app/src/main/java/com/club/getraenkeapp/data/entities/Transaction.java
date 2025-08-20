package com.club.getraenkeapp.data.entities;

import java.util.Objects;

/**
 * Transaction entity representing beverage purchases in the database.
 * 
 * Each transaction records a member purchasing a specific quantity of a beverage
 * at a particular price point, with timestamp for audit and export purposes.
 */
public class Transaction {
    
    public static final String TABLE_NAME = "transactions";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MEMBER_ID = "member_id";
    public static final String COLUMN_BEVERAGE_ID = "beverage_id";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_UNIT_PRICE = "unit_price";
    public static final String COLUMN_TOTAL_PRICE = "total_price";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    
    // Create table SQL with foreign key constraints
    public static final String CREATE_TABLE = 
        "CREATE TABLE " + TABLE_NAME + " (" +
        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        COLUMN_MEMBER_ID + " INTEGER NOT NULL, " +
        COLUMN_BEVERAGE_ID + " INTEGER NOT NULL, " +
        COLUMN_QUANTITY + " INTEGER NOT NULL, " +
        COLUMN_UNIT_PRICE + " REAL NOT NULL, " +
        COLUMN_TOTAL_PRICE + " REAL NOT NULL, " +
        COLUMN_TIMESTAMP + " INTEGER DEFAULT " + System.currentTimeMillis() + ", " +
        "FOREIGN KEY (" + COLUMN_MEMBER_ID + ") REFERENCES " + Member.TABLE_NAME + "(" + Member.COLUMN_ID + ") ON DELETE RESTRICT, " +
        "FOREIGN KEY (" + COLUMN_BEVERAGE_ID + ") REFERENCES " + Beverage.TABLE_NAME + "(" + Beverage.COLUMN_ID + ") ON DELETE RESTRICT" +
        ")";
    
    private long id;
    private long memberId;
    private long beverageId;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private long timestamp;
    
    // Default constructor
    public Transaction() {
        this.timestamp = System.currentTimeMillis();
    }
    
    // Constructor without ID (for new transactions)
    public Transaction(long memberId, long beverageId, int quantity, double unitPrice) {
        this();
        this.memberId = memberId;
        this.beverageId = beverageId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = quantity * unitPrice;
    }
    
    // Full constructor
    public Transaction(long id, long memberId, long beverageId, int quantity, 
                      double unitPrice, double totalPrice, long timestamp) {
        this.id = id;
        this.memberId = memberId;
        this.beverageId = beverageId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.timestamp = timestamp;
    }
    
    // Getters
    public long getId() {
        return id;
    }
    
    public long getMemberId() {
        return memberId;
    }
    
    public long getBeverageId() {
        return beverageId;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public double getUnitPrice() {
        return unitPrice;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    // Setters
    public void setId(long id) {
        this.id = id;
    }
    
    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }
    
    public void setBeverageId(long beverageId) {
        this.beverageId = beverageId;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        // Recalculate total when quantity changes
        this.totalPrice = quantity * unitPrice;
    }
    
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
        // Recalculate total when unit price changes
        this.totalPrice = quantity * unitPrice;
    }
    
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    /**
     * Get formatted total price string for display
     */
    public String getFormattedTotalPrice() {
        return String.format("%.2f €", totalPrice);
    }
    
    /**
     * Get formatted unit price string for display
     */
    public String getFormattedUnitPrice() {
        return String.format("%.2f €", unitPrice);
    }
    
    /**
     * Calculate and update total price based on quantity and unit price
     */
    public void calculateTotalPrice() {
        this.totalPrice = quantity * unitPrice;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Transaction that = (Transaction) obj;
        return id == that.id && 
               memberId == that.memberId &&
               beverageId == that.beverageId &&
               quantity == that.quantity &&
               Double.compare(that.unitPrice, unitPrice) == 0 &&
               Double.compare(that.totalPrice, totalPrice) == 0 &&
               timestamp == that.timestamp;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, memberId, beverageId, quantity, unitPrice, totalPrice, timestamp);
    }
    
    @Override
    public String toString() {
        return "Transaction{" +
               "id=" + id +
               ", memberId=" + memberId +
               ", beverageId=" + beverageId +
               ", quantity=" + quantity +
               ", unitPrice=" + unitPrice +
               ", totalPrice=" + totalPrice +
               ", timestamp=" + timestamp +
               '}';
    }
}