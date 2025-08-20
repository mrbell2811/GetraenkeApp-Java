package com.club.getraenkeapp.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.club.getraenkeapp.data.database.entities.Transaction
import com.club.getraenkeapp.data.database.entities.TransactionWithDetails

/**
 * Data Access Object for Transaction operations.
 * 
 * Provides comprehensive transaction queries including joins with Member and Beverage
 * for detailed reporting and export functionality.
 */
@Dao
interface TransactionDao {
    
    @Query("SELECT * FROM transactions ORDER BY timestamp DESC")
    fun getAllTransactions(): LiveData<List<Transaction>>
    
    @Query("SELECT * FROM transactions WHERE id = :id")
    suspend fun getTransactionById(id: Long): Transaction?
    
    @Query("SELECT * FROM transactions WHERE member_id = :memberId ORDER BY timestamp DESC")
    fun getTransactionsByMember(memberId: Long): LiveData<List<Transaction>>
    
    @Query("SELECT * FROM transactions WHERE beverage_id = :beverageId ORDER BY timestamp DESC")
    fun getTransactionsByBeverage(beverageId: Long): LiveData<List<Transaction>>
    
    /**
     * Get transactions with member and beverage details for display and export
     */
    @Query("""
        SELECT 
            t.id,
            m.name as memberName,
            b.name as beverageName,
            t.quantity,
            t.unit_price as unitPrice,
            t.total_price as totalPrice,
            t.timestamp
        FROM transactions t
        INNER JOIN members m ON t.member_id = m.id
        INNER JOIN beverages b ON t.beverage_id = b.id
        ORDER BY t.timestamp DESC
    """)
    fun getTransactionsWithDetails(): LiveData<List<TransactionWithDetails>>
    
    /**
     * Get transactions within date range for export
     */
    @Query("""
        SELECT 
            t.id,
            m.name as memberName,
            b.name as beverageName,
            t.quantity,
            t.unit_price as unitPrice,
            t.total_price as totalPrice,
            t.timestamp
        FROM transactions t
        INNER JOIN members m ON t.member_id = m.id
        INNER JOIN beverages b ON t.beverage_id = b.id
        WHERE t.timestamp BETWEEN :startTimestamp AND :endTimestamp
        ORDER BY t.timestamp ASC
    """)
    suspend fun getTransactionsInDateRange(
        startTimestamp: Long, 
        endTimestamp: Long
    ): List<TransactionWithDetails>
    
    /**
     * Get transactions for specific member within date range
     */
    @Query("""
        SELECT 
            t.id,
            m.name as memberName,
            b.name as beverageName,
            t.quantity,
            t.unit_price as unitPrice,
            t.total_price as totalPrice,
            t.timestamp
        FROM transactions t
        INNER JOIN members m ON t.member_id = m.id
        INNER JOIN beverages b ON t.beverage_id = b.id
        WHERE t.member_id = :memberId 
        AND t.timestamp BETWEEN :startTimestamp AND :endTimestamp
        ORDER BY t.timestamp ASC
    """)
    suspend fun getMemberTransactionsInDateRange(
        memberId: Long,
        startTimestamp: Long, 
        endTimestamp: Long
    ): List<TransactionWithDetails>
    
    @Insert
    suspend fun insertTransaction(transaction: Transaction): Long
    
    @Update
    suspend fun updateTransaction(transaction: Transaction)
    
    @Delete
    suspend fun deleteTransaction(transaction: Transaction)
    
    @Query("DELETE FROM transactions WHERE id = :id")
    suspend fun deleteTransactionById(id: Long)
    
    /**
     * Get total revenue for a date range
     */
    @Query("""
        SELECT SUM(total_price) FROM transactions 
        WHERE timestamp BETWEEN :startTimestamp AND :endTimestamp
    """)
    suspend fun getTotalRevenueInDateRange(
        startTimestamp: Long, 
        endTimestamp: Long
    ): Double?
    
    /**
     * Get transaction count for a date range
     */
    @Query("""
        SELECT COUNT(*) FROM transactions 
        WHERE timestamp BETWEEN :startTimestamp AND :endTimestamp
    """)
    suspend fun getTransactionCountInDateRange(
        startTimestamp: Long, 
        endTimestamp: Long
    ): Int
    
    /**
     * Get latest transaction timestamp for backup purposes
     */
    @Query("SELECT MAX(timestamp) FROM transactions")
    suspend fun getLatestTransactionTimestamp(): Long?

    @Query("""
        SELECT b.name as beverageName, SUM(t.quantity) as count
        FROM transactions t
        INNER JOIN beverages b ON t.beverage_id = b.id
        GROUP BY b.name
        ORDER BY count DESC
    """)
    fun getConsumptionOverview(): LiveData<List<com.club.getraenkeapp.data.Consumption>>
}