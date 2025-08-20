package com.club.getraenkeapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Transaction entity representing beverage purchases in the database.
 * 
 * Each transaction records a member purchasing a specific quantity of a beverage
 * at a particular price point, with timestamp for audit and export purposes.
 * 
 * Foreign keys ensure referential integrity with Member and Beverage entities.
 */
@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(
            entity = Member::class, 
            parentColumns = ["id"], 
            childColumns = ["member_id"],
            onDelete = ForeignKey.RESTRICT // Prevent member deletion with transactions
        ),
        ForeignKey(
            entity = Beverage::class, 
            parentColumns = ["id"], 
            childColumns = ["beverage_id"],
            onDelete = ForeignKey.RESTRICT // Prevent beverage deletion with transactions
        )
    ]
)
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    @ColumnInfo(name = "member_id")
    val memberId: Long,
    
    @ColumnInfo(name = "beverage_id") 
    val beverageId: Long,
    
    @ColumnInfo(name = "quantity")
    val quantity: Int,
    
    @ColumnInfo(name = "unit_price")
    val unitPrice: Double,
    
    @ColumnInfo(name = "total_price")
    val totalPrice: Double,
    
    @ColumnInfo(name = "timestamp")
    val timestamp: Long = System.currentTimeMillis()
)