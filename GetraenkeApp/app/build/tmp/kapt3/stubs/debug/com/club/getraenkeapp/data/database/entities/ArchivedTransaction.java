package com.club.getraenkeapp.data.database.entities;

/**
 * Archived transaction entity for storing historical transaction data after monthly closing.
 *
 * This entity stores complete transaction information including member and beverage names
 * as strings (not foreign keys) since members and beverages can be modified or deleted
 * after archival.
 *
 * Each archived transaction represents a complete snapshot of the transaction at the time
 * of monthly closing, ensuring data integrity for historical reporting and billing.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bc\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0011J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0006H\u00c6\u0003J\t\u0010#\u001a\u00020\u0006H\u00c6\u0003J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u0006H\u00c6\u0003J\t\u0010&\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\'\u001a\u00020\tH\u00c6\u0003J\t\u0010(\u001a\u00020\u000bH\u00c6\u0003J\t\u0010)\u001a\u00020\u000bH\u00c6\u0003J\t\u0010*\u001a\u00020\u0003H\u00c6\u0003J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003Jw\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00062\b\b\u0002\u0010\u0010\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00100\u001a\u00020\tH\u00d6\u0001J\t\u00101\u001a\u00020\u0006H\u00d6\u0001R\u0016\u0010\u000f\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0010\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0016\u0010\u000e\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0016\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010\r\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0016R\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001e\u00a8\u00062"}, d2 = {"Lcom/club/getraenkeapp/data/database/entities/ArchivedTransaction;", "", "id", "", "originalTransactionId", "memberName", "", "beverageName", "quantity", "", "unitPrice", "", "totalPrice", "transactionTimestamp", "archivedTimestamp", "archivePeriod", "archiveReason", "(JJLjava/lang/String;Ljava/lang/String;IDDJJLjava/lang/String;Ljava/lang/String;)V", "getArchivePeriod", "()Ljava/lang/String;", "getArchiveReason", "getArchivedTimestamp", "()J", "getBeverageName", "getId", "getMemberName", "getOriginalTransactionId", "getQuantity", "()I", "getTotalPrice", "()D", "getTransactionTimestamp", "getUnitPrice", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
@androidx.room.Entity(tableName = "archived_transactions")
public final class ArchivedTransaction {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final long id = 0L;
    @androidx.room.ColumnInfo(name = "original_transaction_id")
    private final long originalTransactionId = 0L;
    @androidx.room.ColumnInfo(name = "member_name")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String memberName = null;
    @androidx.room.ColumnInfo(name = "beverage_name")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String beverageName = null;
    @androidx.room.ColumnInfo(name = "quantity")
    private final int quantity = 0;
    @androidx.room.ColumnInfo(name = "unit_price")
    private final double unitPrice = 0.0;
    @androidx.room.ColumnInfo(name = "total_price")
    private final double totalPrice = 0.0;
    @androidx.room.ColumnInfo(name = "transaction_timestamp")
    private final long transactionTimestamp = 0L;
    @androidx.room.ColumnInfo(name = "archived_timestamp")
    private final long archivedTimestamp = 0L;
    @androidx.room.ColumnInfo(name = "archive_period")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String archivePeriod = null;
    @androidx.room.ColumnInfo(name = "archive_reason")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String archiveReason = null;
    
    public ArchivedTransaction(long id, long originalTransactionId, @org.jetbrains.annotations.NotNull()
    java.lang.String memberName, @org.jetbrains.annotations.NotNull()
    java.lang.String beverageName, int quantity, double unitPrice, double totalPrice, long transactionTimestamp, long archivedTimestamp, @org.jetbrains.annotations.NotNull()
    java.lang.String archivePeriod, @org.jetbrains.annotations.NotNull()
    java.lang.String archiveReason) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    public final long getOriginalTransactionId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMemberName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBeverageName() {
        return null;
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
    
    public final long getTransactionTimestamp() {
        return 0L;
    }
    
    public final long getArchivedTimestamp() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getArchivePeriod() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getArchiveReason() {
        return null;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    public final long component2() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    public final int component5() {
        return 0;
    }
    
    public final double component6() {
        return 0.0;
    }
    
    public final double component7() {
        return 0.0;
    }
    
    public final long component8() {
        return 0L;
    }
    
    public final long component9() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.club.getraenkeapp.data.database.entities.ArchivedTransaction copy(long id, long originalTransactionId, @org.jetbrains.annotations.NotNull()
    java.lang.String memberName, @org.jetbrains.annotations.NotNull()
    java.lang.String beverageName, int quantity, double unitPrice, double totalPrice, long transactionTimestamp, long archivedTimestamp, @org.jetbrains.annotations.NotNull()
    java.lang.String archivePeriod, @org.jetbrains.annotations.NotNull()
    java.lang.String archiveReason) {
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