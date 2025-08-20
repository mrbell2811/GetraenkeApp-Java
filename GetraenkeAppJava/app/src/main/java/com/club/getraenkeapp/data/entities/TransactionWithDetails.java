package com.club.getraenkeapp.data.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * TransactionWithDetails entity for joined queries that include member and beverage information.
 * 
 * This class represents a transaction with the associated member name and beverage name,
 * useful for display purposes and CSV export functionality.
 */
public class TransactionWithDetails {
    
    private long transactionId;
    private long memberId;
    private String memberName;
    private long beverageId;
    private String beverageName;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private long timestamp;
    
    // Default constructor
    public TransactionWithDetails() {}
    
    // Full constructor
    public TransactionWithDetails(long transactionId, long memberId, String memberName,
                                 long beverageId, String beverageName, int quantity,
                                 double unitPrice, double totalPrice, long timestamp) {
        this.transactionId = transactionId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.beverageId = beverageId;
        this.beverageName = beverageName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.timestamp = timestamp;
    }
    
    // Constructor from Transaction and names
    public TransactionWithDetails(Transaction transaction, String memberName, String beverageName) {
        this.transactionId = transaction.getId();
        this.memberId = transaction.getMemberId();
        this.memberName = memberName;
        this.beverageId = transaction.getBeverageId();
        this.beverageName = beverageName;
        this.quantity = transaction.getQuantity();
        this.unitPrice = transaction.getUnitPrice();
        this.totalPrice = transaction.getTotalPrice();
        this.timestamp = transaction.getTimestamp();
    }
    
    // Getters
    public long getTransactionId() {
        return transactionId;
    }
    
    public long getMemberId() {
        return memberId;
    }
    
    public String getMemberName() {
        return memberName;
    }
    
    public long getBeverageId() {
        return beverageId;
    }
    
    public String getBeverageName() {
        return beverageName;
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
    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }
    
    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }
    
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    
    public void setBeverageId(long beverageId) {
        this.beverageId = beverageId;
    }
    
    public void setBeverageName(String beverageName) {
        this.beverageName = beverageName;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    // Utility methods for display and export
    
    /**
     * Get formatted date for display
     */
    public String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
    
    /**
     * Get formatted time for display
     */
    public String getFormattedTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
    
    /**
     * Get formatted date and time for display
     */
    public String getFormattedDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
    
    /**
     * Get formatted total price for display
     */
    public String getFormattedTotalPrice() {
        return String.format("%.2f €", totalPrice);
    }
    
    /**
     * Get formatted unit price for display
     */
    public String getFormattedUnitPrice() {
        return String.format("%.2f €", unitPrice);
    }
    
    /**
     * Get CSV formatted date for export (YYYY-MM-DD)
     */
    public String getCsvDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
    
    /**
     * Get CSV formatted time for export (HH:mm:ss)
     */
    public String getCsvTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
    
    /**
     * Convert to CSV line for export
     */
    public String toCsvLine() {
        return String.format("%s,%s,%s,%s,%d,%.2f,%.2f",
            getCsvDate(),
            getCsvTime(),
            escapeCsvField(memberName),
            escapeCsvField(beverageName),
            quantity,
            unitPrice,
            totalPrice
        );
    }
    
    /**
     * Escape CSV field to handle commas and quotes
     */
    private String escapeCsvField(String field) {
        if (field == null) {
            return "";
        }
        if (field.contains(",") || field.contains("\"") || field.contains("\n")) {
            return "\"" + field.replace("\"", "\"\"") + "\"";
        }
        return field;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TransactionWithDetails that = (TransactionWithDetails) obj;
        return transactionId == that.transactionId &&
               memberId == that.memberId &&
               beverageId == that.beverageId &&
               quantity == that.quantity &&
               Double.compare(that.unitPrice, unitPrice) == 0 &&
               Double.compare(that.totalPrice, totalPrice) == 0 &&
               timestamp == that.timestamp &&
               Objects.equals(memberName, that.memberName) &&
               Objects.equals(beverageName, that.beverageName);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(transactionId, memberId, memberName, beverageId, 
                          beverageName, quantity, unitPrice, totalPrice, timestamp);
    }
    
    @Override
    public String toString() {
        return "TransactionWithDetails{" +
               "transactionId=" + transactionId +
               ", memberName='" + memberName + '\'' +
               ", beverageName='" + beverageName + '\'' +
               ", quantity=" + quantity +
               ", totalPrice=" + totalPrice +
               ", timestamp=" + getFormattedDateTime() +
               '}';
    }
}