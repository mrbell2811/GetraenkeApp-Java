package com.club.getraenkeapp.data.database.entities

/**
 * Data class representing a transaction with member and beverage details.
 * 
 * This is used for display purposes and CSV export where we need the actual
 * names rather than just foreign key IDs.
 */
data class TransactionWithDetails(
    val id: Long,
    val memberName: String,
    val beverageName: String,
    val quantity: Int,
    val unitPrice: Double,
    val totalPrice: Double,
    val timestamp: Long
)