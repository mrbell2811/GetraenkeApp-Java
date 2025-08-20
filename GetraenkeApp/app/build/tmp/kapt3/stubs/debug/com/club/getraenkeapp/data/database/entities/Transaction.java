package com.club.getraenkeapp.data.database.entities;

/**
 * Transaction entity representing beverage purchases in the database.
 *
 * Each transaction records a member purchasing a specific quantity of a beverage
 * at a particular price point, with timestamp for audit and export purposes.
 *
 * Foreign keys ensure referential integrity with Member and Beverage entities.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001BA\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001c\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003JO\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\"\u001a\u00020\u0007H\u00d6\u0001J\t\u0010#\u001a\u00020$H\u00d6\u0001R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u000b\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0016\u0010\n\u001a\u00020\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015\u00a8\u0006%"}, d2 = {"Lcom/club/getraenkeapp/data/database/entities/Transaction;", "", "id", "", "memberId", "beverageId", "quantity", "", "unitPrice", "", "totalPrice", "timestamp", "(JJJIDDJ)V", "getBeverageId", "()J", "getId", "getMemberId", "getQuantity", "()I", "getTimestamp", "getTotalPrice", "()D", "getUnitPrice", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "", "app_debug"})
@androidx.room.Entity(tableName = "transactions", foreignKeys = {@androidx.room.ForeignKey(entity = com.club.getraenkeapp.data.database.entities.Member.class, parentColumns = {"id"}, childColumns = {"member_id"}, onDelete = 2), @androidx.room.ForeignKey(entity = com.club.getraenkeapp.data.database.entities.Beverage.class, parentColumns = {"id"}, childColumns = {"beverage_id"}, onDelete = 2)})
public final class Transaction {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final long id = 0L;
    @androidx.room.ColumnInfo(name = "member_id")
    private final long memberId = 0L;
    @androidx.room.ColumnInfo(name = "beverage_id")
    private final long beverageId = 0L;
    @androidx.room.ColumnInfo(name = "quantity")
    private final int quantity = 0;
    @androidx.room.ColumnInfo(name = "unit_price")
    private final double unitPrice = 0.0;
    @androidx.room.ColumnInfo(name = "total_price")
    private final double totalPrice = 0.0;
    @androidx.room.ColumnInfo(name = "timestamp")
    private final long timestamp = 0L;
    
    public Transaction(long id, long memberId, long beverageId, int quantity, double unitPrice, double totalPrice, long timestamp) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    public final long getMemberId() {
        return 0L;
    }
    
    public final long getBeverageId() {
        return 0L;
    }
    
    public final int getQuantity() {
        return 0;
    }
    
    public final double getUnitPrice() {
        return 0.0;
    }
    
    public final double getTotalPrice() {
        return 0.0;
    }
    
    public final long getTimestamp() {
        return 0L;
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final long component2() {
        return 0L;
    }
    
    public final long component3() {
        return 0L;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final double component5() {
        return 0.0;
    }
    
    public final double component6() {
        return 0.0;
    }
    
    public final long component7() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.club.getraenkeapp.data.database.entities.Transaction copy(long id, long memberId, long beverageId, int quantity, double unitPrice, double totalPrice, long timestamp) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}