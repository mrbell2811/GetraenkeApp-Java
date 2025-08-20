package com.club.getraenkeapp.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.club.getraenkeapp.data.database.entities.ArchivedTransaction

/**
 * Data Access Object for ArchivedTransaction operations.
 * 
 * Provides queries for historical transaction data, monthly reports,
 * and archive period management.
 */
@Dao
interface ArchivedTransactionDao {
    
    @Query("SELECT * FROM archived_transactions ORDER BY transaction_timestamp DESC")
    fun getAllArchivedTransactions(): LiveData<List<ArchivedTransaction>>
    
    @Query("SELECT * FROM archived_transactions WHERE id = :id")
    suspend fun getArchivedTransactionById(id: Long): ArchivedTransaction?
    
    @Query("SELECT * FROM archived_transactions WHERE archive_period = :period ORDER BY transaction_timestamp ASC")
    suspend fun getTransactionsByArchivePeriod(period: String): List<ArchivedTransaction>
    
    @Query("SELECT * FROM archived_transactions WHERE member_name = :memberName ORDER BY transaction_timestamp DESC")
    suspend fun getArchivedTransactionsByMember(memberName: String): List<ArchivedTransaction>
    
    @Query("SELECT * FROM archived_transactions WHERE beverage_name = :beverageName ORDER BY transaction_timestamp DESC")
    suspend fun getArchivedTransactionsByBeverage(beverageName: String): List<ArchivedTransaction>
    
    /**
     * Get archived transactions within date range for historical reporting
     */
    @Query("""
        SELECT * FROM archived_transactions 
        WHERE transaction_timestamp BETWEEN :startTimestamp AND :endTimestamp
        ORDER BY transaction_timestamp ASC
    """)
    suspend fun getArchivedTransactionsInDateRange(
        startTimestamp: Long, 
        endTimestamp: Long
    ): List<ArchivedTransaction>
    
    /**
     * Get all distinct archive periods for historical navigation
     */
    @Query("SELECT DISTINCT archive_period FROM archived_transactions ORDER BY archive_period DESC")
    suspend fun getArchivePeriods(): List<String>
    
    /**
     * Get total revenue for an archived period
     */
    @Query("SELECT SUM(total_price) FROM archived_transactions WHERE archive_period = :period")
    suspend fun getTotalRevenueByPeriod(period: String): Double?
    
    /**
     * Get transaction count for an archived period
     */
    @Query("SELECT COUNT(*) FROM archived_transactions WHERE archive_period = :period")
    suspend fun getTransactionCountByPeriod(period: String): Int
    
    /**
     * Get most recent archive period (last monthly closing)
     */
    @Query("SELECT archive_period FROM archived_transactions ORDER BY archived_timestamp DESC LIMIT 1")
    suspend fun getLastArchivePeriod(): String?
    
    /**
     * Get most recent archive timestamp (when last monthly closing occurred)
     */
    @Query("SELECT MAX(archived_timestamp) FROM archived_transactions")
    suspend fun getLastArchiveTimestamp(): Long?
    
    @Insert
    suspend fun insertArchivedTransaction(transaction: ArchivedTransaction): Long
    
    @Insert
    suspend fun insertArchivedTransactions(transactions: List<ArchivedTransaction>): List<Long>
    
    @Update
    suspend fun updateArchivedTransaction(transaction: ArchivedTransaction)
    
    @Delete
    suspend fun deleteArchivedTransaction(transaction: ArchivedTransaction)
    
    @Query("DELETE FROM archived_transactions WHERE id = :id")
    suspend fun deleteArchivedTransactionById(id: Long)
    
    @Query("DELETE FROM archived_transactions WHERE archive_period = :period")
    suspend fun deleteArchivedTransactionsByPeriod(period: String)
}