package com.club.getraenkeapp.data.database.dao;

/**
 * Data Access Object for Transaction operations.
 *
 * Provides comprehensive transaction queries including joins with Member and Beverage
 * for detailed reporting and export functionality.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH\'J\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\r0\fH\'J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\tH\u00a7@\u00a2\u0006\u0002\u0010\u0011J,\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\r2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u0017J \u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u001aJ\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u001e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u001aJ\u001c\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\u001f\u001a\u00020\tH\'J\u001c\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\u0014\u001a\u00020\tH\'J$\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00130\r2\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u001aJ\u0014\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\r0\fH\'J\u0016\u0010#\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010$\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006%"}, d2 = {"Lcom/club/getraenkeapp/data/database/dao/TransactionDao;", "", "deleteTransaction", "", "transaction", "Lcom/club/getraenkeapp/data/database/entities/Transaction;", "(Lcom/club/getraenkeapp/data/database/entities/Transaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTransactionById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllTransactions", "Landroidx/lifecycle/LiveData;", "", "getConsumptionOverview", "Lcom/club/getraenkeapp/data/Consumption;", "getLatestTransactionTimestamp", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMemberTransactionsInDateRange", "Lcom/club/getraenkeapp/data/database/entities/TransactionWithDetails;", "memberId", "startTimestamp", "endTimestamp", "(JJJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTotalRevenueInDateRange", "", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTransactionById", "getTransactionCountInDateRange", "", "getTransactionsByBeverage", "beverageId", "getTransactionsByMember", "getTransactionsInDateRange", "getTransactionsWithDetails", "insertTransaction", "updateTransaction", "app_debug"})
@androidx.room.Dao()
public abstract interface TransactionDao {
    
    @androidx.room.Query(value = "SELECT * FROM transactions ORDER BY timestamp DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.club.getraenkeapp.data.database.entities.Transaction>> getAllTransactions();
    
    @androidx.room.Query(value = "SELECT * FROM transactions WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTransactionById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.club.getraenkeapp.data.database.entities.Transaction> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM transactions WHERE member_id = :memberId ORDER BY timestamp DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.club.getraenkeapp.data.database.entities.Transaction>> getTransactionsByMember(long memberId);
    
    @androidx.room.Query(value = "SELECT * FROM transactions WHERE beverage_id = :beverageId ORDER BY timestamp DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.club.getraenkeapp.data.database.entities.Transaction>> getTransactionsByBeverage(long beverageId);
    
    /**
     * Get transactions with member and beverage details for display and export
     */
    @androidx.room.Query(value = "\n        SELECT \n            t.id,\n            m.name as memberName,\n            b.name as beverageName,\n            t.quantity,\n            t.unit_price as unitPrice,\n            t.total_price as totalPrice,\n            t.timestamp\n        FROM transactions t\n        INNER JOIN members m ON t.member_id = m.id\n        INNER JOIN beverages b ON t.beverage_id = b.id\n        ORDER BY t.timestamp DESC\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.club.getraenkeapp.data.database.entities.TransactionWithDetails>> getTransactionsWithDetails();
    
    /**
     * Get transactions within date range for export
     */
    @androidx.room.Query(value = "\n        SELECT \n            t.id,\n            m.name as memberName,\n            b.name as beverageName,\n            t.quantity,\n            t.unit_price as unitPrice,\n            t.total_price as totalPrice,\n            t.timestamp\n        FROM transactions t\n        INNER JOIN members m ON t.member_id = m.id\n        INNER JOIN beverages b ON t.beverage_id = b.id\n        WHERE t.timestamp BETWEEN :startTimestamp AND :endTimestamp\n        ORDER BY t.timestamp ASC\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTransactionsInDateRange(long startTimestamp, long endTimestamp, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.club.getraenkeapp.data.database.entities.TransactionWithDetails>> $completion);
    
    /**
     * Get transactions for specific member within date range
     */
    @androidx.room.Query(value = "\n        SELECT \n            t.id,\n            m.name as memberName,\n            b.name as beverageName,\n            t.quantity,\n            t.unit_price as unitPrice,\n            t.total_price as totalPrice,\n            t.timestamp\n        FROM transactions t\n        INNER JOIN members m ON t.member_id = m.id\n        INNER JOIN beverages b ON t.beverage_id = b.id\n        WHERE t.member_id = :memberId \n        AND t.timestamp BETWEEN :startTimestamp AND :endTimestamp\n        ORDER BY t.timestamp ASC\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMemberTransactionsInDateRange(long memberId, long startTimestamp, long endTimestamp, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.club.getraenkeapp.data.database.entities.TransactionWithDetails>> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertTransaction(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.entities.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateTransaction(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.entities.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteTransaction(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.entities.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM transactions WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteTransactionById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Get total revenue for a date range
     */
    @androidx.room.Query(value = "\n        SELECT SUM(total_price) FROM transactions \n        WHERE timestamp BETWEEN :startTimestamp AND :endTimestamp\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTotalRevenueInDateRange(long startTimestamp, long endTimestamp, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    /**
     * Get transaction count for a date range
     */
    @androidx.room.Query(value = "\n        SELECT COUNT(*) FROM transactions \n        WHERE timestamp BETWEEN :startTimestamp AND :endTimestamp\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTransactionCountInDateRange(long startTimestamp, long endTimestamp, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    /**
     * Get latest transaction timestamp for backup purposes
     */
    @androidx.room.Query(value = "SELECT MAX(timestamp) FROM transactions")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLatestTransactionTimestamp(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "\n        SELECT b.name as beverageName, SUM(t.quantity) as count\n        FROM transactions t\n        INNER JOIN beverages b ON t.beverage_id = b.id\n        GROUP BY b.name\n        ORDER BY count DESC\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.club.getraenkeapp.data.Consumption>> getConsumptionOverview();
}