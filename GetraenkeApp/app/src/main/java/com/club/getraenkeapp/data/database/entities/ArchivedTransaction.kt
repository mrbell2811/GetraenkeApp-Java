package com.club.getraenkeapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
@Entity(tableName = "archived_transactions")
data class ArchivedTransaction(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    @ColumnInfo(name = "original_transaction_id")
    val originalTransactionId: Long,
    
    @ColumnInfo(name = "member_name")
    val memberName: String,
    
    @ColumnInfo(name = "beverage_name")
    val beverageName: String,
    
    @ColumnInfo(name = "quantity")
    val quantity: Int,
    
    @ColumnInfo(name = "unit_price")
    val unitPrice: Double,
    
    @ColumnInfo(name = "total_price")
    val totalPrice: Double,
    
    @ColumnInfo(name = "transaction_timestamp")
    val transactionTimestamp: Long,
    
    @ColumnInfo(name = "archived_timestamp")
    val archivedTimestamp: Long = System.currentTimeMillis(),
    
    @ColumnInfo(name = "archive_period")
    val archivePeriod: String, // e.g., "2025-08" for August 2025
    
    @ColumnInfo(name = "archive_reason")
    val archiveReason: String = "MONTHLY_CLOSING"
)