package com.club.getraenkeapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Beverage entity representing available drinks in the database.
 * 
 * Each beverage has a unique ID, name, price, optional category for organization,
 * and active status to enable/disable without deletion.
 */
@Entity(tableName = "beverages")
data class Beverage(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    @ColumnInfo(name = "name")
    val name: String,
    
    @ColumnInfo(name = "price")
    val price: Double,
    
    @ColumnInfo(name = "category")
    val category: String? = null,
    
    @ColumnInfo(name = "active")
    val active: Boolean = true
)