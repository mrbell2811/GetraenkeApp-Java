package com.club.getraenkeapp.data.database.dao;

/**
 * Data Access Object for ArchivedTransaction operations.
 *
 * Provides queries for historical transaction data, monthly reports,
 * and archive period management.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u000e\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00110\u0010H\'J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u00112\u0006\u0010\u0016\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u00112\u0006\u0010\u0018\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ$\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u00112\u0006\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u001cJ\u0010\u0010\u001d\u001a\u0004\u0018\u00010\rH\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\tH\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0018\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010!\u001a\u00020\"2\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00050\u00112\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010$\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\"\u0010%\u001a\b\u0012\u0004\u0012\u00020\t0\u00112\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00050\u0011H\u00a7@\u00a2\u0006\u0002\u0010\'J\u0016\u0010(\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006)"}, d2 = {"Lcom/club/getraenkeapp/data/database/dao/ArchivedTransactionDao;", "", "deleteArchivedTransaction", "", "transaction", "Lcom/club/getraenkeapp/data/database/entities/ArchivedTransaction;", "(Lcom/club/getraenkeapp/data/database/entities/ArchivedTransaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteArchivedTransactionById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteArchivedTransactionsByPeriod", "period", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllArchivedTransactions", "Landroidx/lifecycle/LiveData;", "", "getArchivePeriods", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getArchivedTransactionById", "getArchivedTransactionsByBeverage", "beverageName", "getArchivedTransactionsByMember", "memberName", "getArchivedTransactionsInDateRange", "startTimestamp", "endTimestamp", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLastArchivePeriod", "getLastArchiveTimestamp", "getTotalRevenueByPeriod", "", "getTransactionCountByPeriod", "", "getTransactionsByArchivePeriod", "insertArchivedTransaction", "insertArchivedTransactions", "transactions", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateArchivedTransaction", "app_debug"})
@androidx.room.Dao()
public abstract interface ArchivedTransactionDao {
    
    @androidx.room.Query(value = "SELECT * FROM archived_transactions ORDER BY transaction_timestamp DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.club.getraenkeapp.data.database.entities.ArchivedTransaction>> getAllArchivedTransactions();
    
    @androidx.room.Query(value = "SELECT * FROM archived_transactions WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getArchivedTransactionById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.club.getraenkeapp.data.database.entities.ArchivedTransaction> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM archived_transactions WHERE archive_period = :period ORDER BY transaction_timestamp ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTransactionsByArchivePeriod(@org.jetbrains.annotations.NotNull()
    java.lang.String period, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.club.getraenkeapp.data.database.entities.ArchivedTransaction>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM archived_transactions WHERE member_name = :memberName ORDER BY transaction_timestamp DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getArchivedTransactionsByMember(@org.jetbrains.annotations.NotNull()
    java.lang.String memberName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.club.getraenkeapp.data.database.entities.ArchivedTransaction>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM archived_transactions WHERE beverage_name = :beverageName ORDER BY transaction_timestamp DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getArchivedTransactionsByBeverage(@org.jetbrains.annotations.NotNull()
    java.lang.String beverageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.club.getraenkeapp.data.database.entities.ArchivedTransaction>> $completion);
    
    /**
     * Get archived transactions within date range for historical reporting
     */
    @androidx.room.Query(value = "\n        SELECT * FROM archived_transactions \n        WHERE transaction_timestamp BETWEEN :startTimestamp AND :endTimestamp\n        ORDER BY transaction_timestamp ASC\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getArchivedTransactionsInDateRange(long startTimestamp, long endTimestamp, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.club.getraenkeapp.data.database.entities.ArchivedTransaction>> $completion);
    
    /**
     * Get all distinct archive periods for historical navigation
     */
    @androidx.room.Query(value = "SELECT DISTINCT archive_period FROM archived_transactions ORDER BY archive_period DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getArchivePeriods(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion);
    
    /**
     * Get total revenue for an archived period
     */
    @androidx.room.Query(value = "SELECT SUM(total_price) FROM archived_transactions WHERE archive_period = :period")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTotalRevenueByPeriod(@org.jetbrains.annotations.NotNull()
    java.lang.String period, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    /**
     * Get transaction count for an archived period
     */
    @androidx.room.Query(value = "SELECT COUNT(*) FROM archived_transactions WHERE archive_period = :period")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTransactionCountByPeriod(@org.jetbrains.annotations.NotNull()
    java.lang.String period, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    /**
     * Get most recent archive period (last monthly closing)
     */
    @androidx.room.Query(value = "SELECT archive_period FROM archived_transactions ORDER BY archived_timestamp DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLastArchivePeriod(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion);
    
    /**
     * Get most recent archive timestamp (when last monthly closing occurred)
     */
    @androidx.room.Query(value = "SELECT MAX(archived_timestamp) FROM archived_transactions")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLastArchiveTimestamp(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertArchivedTransaction(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.entities.ArchivedTransaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertArchivedTransactions(@org.jetbrains.annotations.NotNull()
    java.util.List<com.club.getraenkeapp.data.database.entities.ArchivedTransaction> transactions, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.Long>> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateArchivedTransaction(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.entities.ArchivedTransaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteArchivedTransaction(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.entities.ArchivedTransaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM archived_transactions WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteArchivedTransactionById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM archived_transactions WHERE archive_period = :period")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteArchivedTransactionsByPeriod(@org.jetbrains.annotations.NotNull()
    java.lang.String period, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}