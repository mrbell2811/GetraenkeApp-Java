package com.club.getraenkeapp.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.club.getraenkeapp.data.database.entities.Beverage

/**
 * Data Access Object for Beverage operations.
 * 
 * Provides CRUD operations for beverages with filtering for active items
 * and reactive LiveData queries for UI updates.
 */
@Dao
interface BeverageDao {
    
    @Query("SELECT * FROM beverages WHERE active = 1 ORDER BY category ASC, name ASC")
    fun getActiveBeverages(): LiveData<List<Beverage>>
    
    @Query("SELECT * FROM beverages ORDER BY category ASC, name ASC")
    fun getAllBeverages(): LiveData<List<Beverage>>
    
    @Query("SELECT * FROM beverages WHERE id = :id")
    suspend fun getBeverageById(id: Long): Beverage?
    
    @Query("SELECT * FROM beverages WHERE name = :name LIMIT 1")
    suspend fun getBeverageByName(name: String): Beverage?
    
    @Query("SELECT * FROM beverages WHERE category = :category AND active = 1 ORDER BY name ASC")
    fun getBeveragesByCategory(category: String): LiveData<List<Beverage>>
    
    @Query("SELECT DISTINCT category FROM beverages WHERE category IS NOT NULL ORDER BY category ASC")
    suspend fun getAllCategories(): List<String>
    
    @Insert
    suspend fun insertBeverage(beverage: Beverage): Long
    
    @Update
    suspend fun updateBeverage(beverage: Beverage)
    
    @Delete
    suspend fun deleteBeverage(beverage: Beverage)
    
    @Query("DELETE FROM beverages WHERE id = :id")
    suspend fun deleteBeverageById(id: Long)
    
    /**
     * Deactivate beverage instead of deleting (safer for data integrity)
     */
    @Query("UPDATE beverages SET active = 0 WHERE id = :id")
    suspend fun deactivateBeverage(id: Long)
    
    /**
     * Reactivate beverage
     */
    @Query("UPDATE beverages SET active = 1 WHERE id = :id")
    suspend fun activateBeverage(id: Long)
    
    /**
     * Check if beverage has any transactions before allowing deletion
     */
    @Query("SELECT COUNT(*) FROM transactions WHERE beverage_id = :beverageId")
    suspend fun getTransactionCountForBeverage(beverageId: Long): Int
}