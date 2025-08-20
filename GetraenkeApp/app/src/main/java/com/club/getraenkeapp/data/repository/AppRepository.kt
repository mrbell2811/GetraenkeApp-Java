package com.club.getraenkeapp.data.repository

import androidx.lifecycle.LiveData
import com.club.getraenkeapp.data.database.dao.ArchivedTransactionDao
import com.club.getraenkeapp.data.database.dao.BeverageDao
import com.club.getraenkeapp.data.database.dao.MemberDao
import com.club.getraenkeapp.data.database.dao.TransactionDao
import com.club.getraenkeapp.data.database.entities.ArchivedTransaction
import com.club.getraenkeapp.data.database.entities.Beverage
import com.club.getraenkeapp.data.database.entities.Member
import com.club.getraenkeapp.data.database.entities.Transaction
import com.club.getraenkeapp.data.database.entities.TransactionWithDetails
import java.text.SimpleDateFormat
import java.util.*

/**
 * Repository pattern implementation for GeträkeApp3.0
 * 
 * Single repository managing all data operations, injecting DAO instances via constructor,
 * exposing LiveData/Flow for UI layer consumption, and handling business logic like
 * transaction calculations.
 * 
 * PATTERN: Repository handles business logic like transaction validation and calculations
 */
class AppRepository(
    private val memberDao: MemberDao,
    private val beverageDao: BeverageDao,
    private val transactionDao: TransactionDao,
    private val archivedTransactionDao: ArchivedTransactionDao
) {
    
    // ========== Member Operations ==========
    
    fun getAllMembers(): LiveData<List<Member>> = memberDao.getAllMembers()
    
    suspend fun getMemberById(id: Long): Member? = memberDao.getMemberById(id)
    
    suspend fun getMemberByName(name: String): Member? = memberDao.getMemberByName(name)
    
    suspend fun insertMember(member: Member): Long = memberDao.insertMember(member)
    
    suspend fun updateMember(member: Member) = memberDao.updateMember(member)
    
    suspend fun deleteMember(member: Member): Boolean {
        // Check if member has transactions before deletion
        val transactionCount = memberDao.getTransactionCountForMember(member.id)
        return if (transactionCount > 0) {
            false // Cannot delete member with existing transactions
        } else {
            memberDao.deleteMember(member)
            true
        }
    }
    
    suspend fun updateMemberGridPosition(memberId: Long, position: Int) {
        memberDao.updateMemberGridPosition(memberId, position)
    }
    
    // ========== Beverage Operations ==========
    
    fun getActiveBeverages(): LiveData<List<Beverage>> = beverageDao.getActiveBeverages()
    
    fun getAllBeverages(): LiveData<List<Beverage>> = beverageDao.getAllBeverages()
    
    suspend fun getBeverageById(id: Long): Beverage? = beverageDao.getBeverageById(id)
    
    suspend fun getBeverageByName(name: String): Beverage? = beverageDao.getBeverageByName(name)
    
    suspend fun insertBeverage(beverage: Beverage): Long = beverageDao.insertBeverage(beverage)
    
    suspend fun updateBeverage(beverage: Beverage) = beverageDao.updateBeverage(beverage)
    
    suspend fun deleteBeverage(beverage: Beverage): Boolean {
        // Check if beverage has transactions before deletion
        val transactionCount = beverageDao.getTransactionCountForBeverage(beverage.id)
        return if (transactionCount > 0) {
            // Deactivate instead of delete if has transactions
            beverageDao.deactivateBeverage(beverage.id)
            true
        } else {
            beverageDao.deleteBeverage(beverage)
            true
        }
    }
    
    suspend fun activateBeverage(id: Long) = beverageDao.activateBeverage(id)
    
    suspend fun deactivateBeverage(id: Long) = beverageDao.deactivateBeverage(id)
    
    // ========== Transaction Operations ==========
    
    fun getAllTransactions(): LiveData<List<Transaction>> = transactionDao.getAllTransactions()
    
    fun getTransactionsWithDetails(): LiveData<List<TransactionWithDetails>> = 
        transactionDao.getTransactionsWithDetails()

    fun getConsumptionOverview(): LiveData<List<com.club.getraenkeapp.data.Consumption>> = 
        transactionDao.getConsumptionOverview()
    
    suspend fun getTransactionById(id: Long): Transaction? = transactionDao.getTransactionById(id)
    
    fun getTransactionsByMember(memberId: Long): LiveData<List<Transaction>> = 
        transactionDao.getTransactionsByMember(memberId)
    
    suspend fun insertTransaction(transaction: Transaction): Long {
        // PATTERN: Repository handles business logic like transaction calculations
        // Validate that total price matches quantity * unit price
        val calculatedTotal = transaction.quantity * transaction.unitPrice
        
        if (Math.abs(transaction.totalPrice - calculatedTotal) > 0.01) {
            throw IllegalArgumentException("Total price calculation mismatch")
        }
        
        // Validate quantity is positive
        if (transaction.quantity <= 0) {
            throw IllegalArgumentException("Quantity must be positive")
        }
        
        // Validate unit price is positive
        if (transaction.unitPrice <= 0) {
            throw IllegalArgumentException("Unit price must be positive")
        }
        
        return transactionDao.insertTransaction(transaction)
    }
    
    suspend fun updateTransaction(transaction: Transaction) = transactionDao.updateTransaction(transaction)
    
    suspend fun deleteTransaction(transaction: Transaction) = transactionDao.deleteTransaction(transaction)
    
    // ========== Export and Reporting Operations ==========
    
    suspend fun getTransactionsInDateRange(
        startTimestamp: Long, 
        endTimestamp: Long
    ): List<TransactionWithDetails> = 
        transactionDao.getTransactionsInDateRange(startTimestamp, endTimestamp)
    
    suspend fun getMemberTransactionsInDateRange(
        memberId: Long,
        startTimestamp: Long, 
        endTimestamp: Long
    ): List<TransactionWithDetails> = 
        transactionDao.getMemberTransactionsInDateRange(memberId, startTimestamp, endTimestamp)
    
    suspend fun getTotalRevenueInDateRange(
        startTimestamp: Long, 
        endTimestamp: Long
    ): Double = transactionDao.getTotalRevenueInDateRange(startTimestamp, endTimestamp) ?: 0.0
    
    suspend fun getTransactionCountInDateRange(
        startTimestamp: Long, 
        endTimestamp: Long
    ): Int = transactionDao.getTransactionCountInDateRange(startTimestamp, endTimestamp)
    
    // ========== Business Logic Helper Methods ==========
    
    /**
     * Create a transaction with automatic total calculation
     */
    suspend fun createTransaction(
        memberId: Long,
        beverageId: Long,
        quantity: Int
    ): Long {
        val beverage = getBeverageById(beverageId) 
            ?: throw IllegalArgumentException("Beverage not found")
        
        val member = getMemberById(memberId)
            ?: throw IllegalArgumentException("Member not found")
        
        val totalPrice = quantity * beverage.price
        
        val transaction = Transaction(
            memberId = memberId,
            beverageId = beverageId,
            quantity = quantity,
            unitPrice = beverage.price,
            totalPrice = totalPrice
        )
        
        return insertTransaction(transaction)
    }
    
    /**
     * Validate if member can be deleted (no transactions)
     */
    suspend fun canDeleteMember(memberId: Long): Boolean {
        return memberDao.getTransactionCountForMember(memberId) == 0
    }
    
    /**
     * Validate if beverage can be deleted (no transactions)
     */
    suspend fun canDeleteBeverage(beverageId: Long): Boolean {
        return beverageDao.getTransactionCountForBeverage(beverageId) == 0
    }
    
    /**
     * Get transaction count for member (for deletion validation)
     */
    suspend fun getTransactionCountForMember(memberId: Long): Int {
        return memberDao.getTransactionCountForMember(memberId)
    }
    
    /**
     * Get transaction count for beverage (for deletion validation)
     */
    suspend fun getTransactionCountForBeverage(beverageId: Long): Int {
        return beverageDao.getTransactionCountForBeverage(beverageId)
    }
    
    // ========== Archive Operations ==========
    
    /**
     * Get all archived transactions
     */
    fun getAllArchivedTransactions(): LiveData<List<ArchivedTransaction>> = archivedTransactionDao.getAllArchivedTransactions()
    
    /**
     * Archive current month transactions and clear them from active table
     */
    suspend fun performMonthlyArchive(): ArchiveResult {
        try {
            // Get current month date range
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.DAY_OF_MONTH, 1)
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            val monthStart = calendar.timeInMillis
            
            calendar.add(Calendar.MONTH, 1)
            calendar.add(Calendar.MILLISECOND, -1)
            val monthEnd = calendar.timeInMillis
            
            // Get all transactions for current month
            val currentTransactions = getTransactionsInDateRange(monthStart, monthEnd)
            
            if (currentTransactions.isEmpty()) {
                return ArchiveResult.Error("Keine Transaktionen zum Archivieren gefunden")
            }
            
            // Create archive period string (YYYY-MM)
            val archivePeriod = SimpleDateFormat("yyyy-MM", Locale.GERMAN).format(Date())
            
            // Convert to archived transactions
            val archivedTransactions = currentTransactions.map { transaction ->
                ArchivedTransaction(
                    originalTransactionId = transaction.id,
                    memberName = transaction.memberName,
                    beverageName = transaction.beverageName,
                    quantity = transaction.quantity,
                    unitPrice = transaction.unitPrice,
                    totalPrice = transaction.totalPrice,
                    transactionTimestamp = transaction.timestamp,
                    archivedTimestamp = System.currentTimeMillis(),
                    archivePeriod = archivePeriod,
                    archiveReason = "MONTHLY_CLOSING"
                )
            }
            
            // Insert archived transactions
            archivedTransactionDao.insertArchivedTransactions(archivedTransactions)
            
            // Delete original transactions (clear current month)
            currentTransactions.forEach { transaction ->
                transactionDao.deleteTransactionById(transaction.id)
            }
            
            return ArchiveResult.Success(
                archivedCount = archivedTransactions.size,
                archivePeriod = archivePeriod,
                message = "Erfolgreich ${archivedTransactions.size} Transaktionen archiviert und aus aktiven Daten entfernt"
            )
            
        } catch (e: Exception) {
            return ArchiveResult.Error("Archivierung fehlgeschlagen: ${e.message}")
        }
    }
    
    /**
     * Get archived transactions by period
     */
    suspend fun getArchivedTransactionsByPeriod(period: String): List<ArchivedTransaction> {
        return archivedTransactionDao.getTransactionsByArchivePeriod(period)
    }
    
    /**
     * Get all archive periods
     */
    suspend fun getArchivePeriods(): List<String> {
        return archivedTransactionDao.getArchivePeriods()
    }
    
    /**
     * Get last archive timestamp (when last monthly closing occurred)
     */
    suspend fun getLastArchiveTimestamp(): Long? {
        return archivedTransactionDao.getLastArchiveTimestamp()
    }
    
    /**
     * Get transactions since last archive (for current period statistics)
     */
    suspend fun getTransactionsSinceLastArchive(): List<TransactionWithDetails> {
        val lastArchiveTime = getLastArchiveTimestamp() ?: 0L
        val currentTime = System.currentTimeMillis()
        return getTransactionsInDateRange(lastArchiveTime, currentTime)
    }
    
    /**
     * Get member statistics since last archive
     */
    suspend fun getMemberStatisticsSinceLastArchive(memberName: String): MemberStatistics {
        val transactionsSinceArchive = getTransactionsSinceLastArchive()
        android.util.Log.d("GetraenkeApp", "Total transactions since archive: ${transactionsSinceArchive.size}")
        
        val memberTransactions = transactionsSinceArchive.filter { it.memberName == memberName }
        android.util.Log.d("GetraenkeApp", "Member '$memberName' transactions: ${memberTransactions.size}")
        
        val totalQuantity = memberTransactions.sumOf { it.quantity }
        val totalSpent = memberTransactions.sumOf { it.totalPrice }
        val beverageBreakdown = memberTransactions.groupBy { it.beverageName }
            .mapValues { (_, transactions) ->
                BeverageConsumption(
                    beverageName = transactions.first().beverageName,
                    quantity = transactions.sumOf { it.quantity },
                    totalPrice = transactions.sumOf { it.totalPrice }
                )
            }.values.sortedByDescending { it.quantity }
        
        android.util.Log.d("GetraenkeApp", "Beverage breakdown size: ${beverageBreakdown.size}")
        beverageBreakdown.forEach { consumption ->
            android.util.Log.d("GetraenkeApp", "Beverage: ${consumption.beverageName}, Quantity: ${consumption.quantity}")
        }
        
        return MemberStatistics(
            memberName = memberName,
            totalQuantity = totalQuantity,
            totalSpent = totalSpent,
            beverageBreakdown = beverageBreakdown,
            periodStart = getLastArchiveTimestamp() ?: 0L,
            transactionCount = memberTransactions.size
        )
    }
    
    /**
     * Check if beverage can be safely deleted (no active transactions, archived data preserved)
     */
    suspend fun canSafelyDeleteBeverage(beverageId: Long): Boolean {
        // Only check active transactions - archived transactions preserve data independently
        return beverageDao.getTransactionCountForBeverage(beverageId) == 0
    }
    
    /**
     * Check if member can be safely deleted (no active transactions, archived data preserved)
     */
    suspend fun canSafelyDeleteMember(memberId: Long): Boolean {
        // Only check active transactions - archived transactions preserve data independently
        return memberDao.getTransactionCountForMember(memberId) == 0
    }
    
    // ========== Data Classes ==========
    
    sealed class ArchiveResult {
        data class Success(
            val archivedCount: Int,
            val archivePeriod: String,
            val message: String
        ) : ArchiveResult()
        
        data class Error(val message: String) : ArchiveResult()
    }
    
    data class MemberStatistics(
        val memberName: String,
        val totalQuantity: Int,
        val totalSpent: Double,
        val beverageBreakdown: List<BeverageConsumption>,
        val periodStart: Long,
        val transactionCount: Int
    )
    
    data class BeverageConsumption(
        val beverageName: String,
        val quantity: Int,
        val totalPrice: Double
    )
}