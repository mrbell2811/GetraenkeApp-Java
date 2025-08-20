package com.club.getraenkeapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.club.getraenkeapp.data.database.dao.ArchivedTransactionDao
import com.club.getraenkeapp.data.database.dao.BeverageDao
import com.club.getraenkeapp.data.database.dao.MemberDao
import com.club.getraenkeapp.data.database.dao.TransactionDao
import com.club.getraenkeapp.data.database.entities.ArchivedTransaction
import com.club.getraenkeapp.data.database.entities.Beverage
import com.club.getraenkeapp.data.database.entities.Member
import com.club.getraenkeapp.data.database.entities.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Room Database for GeträkeApp3.0
 * 
 * Central database class that provides access to all DAOs and manages
 * database creation, versioning, and initial data seeding.
 * 
 * CRITICAL: SQLite database name should be consistent
 * CRITICAL: Room requires suspend functions for DAO operations
 */
@Database(
    entities = [Member::class, Beverage::class, Transaction::class, ArchivedTransaction::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun memberDao(): MemberDao
    abstract fun beverageDao(): BeverageDao
    abstract fun transactionDao(): TransactionDao
    abstract fun archivedTransactionDao(): ArchivedTransactionDao
    
    companion object {
        // Singleton prevents multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        private const val DATABASE_NAME = "getraenke_database"
        
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create archived_transactions table
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS `archived_transactions` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `original_transaction_id` INTEGER NOT NULL,
                        `member_name` TEXT NOT NULL,
                        `beverage_name` TEXT NOT NULL,
                        `quantity` INTEGER NOT NULL,
                        `unit_price` REAL NOT NULL,
                        `total_price` REAL NOT NULL,
                        `transaction_timestamp` INTEGER NOT NULL,
                        `archived_timestamp` INTEGER NOT NULL,
                        `archive_period` TEXT NOT NULL,
                        `archive_reason` TEXT NOT NULL
                    )
                """.trimIndent())
            }
        }
        
        fun getDatabase(context: Context): AppDatabase {
            // Return existing instance if available
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                .addCallback(DatabaseCallback())
                .addMigrations(MIGRATION_1_2)
                .build()
                INSTANCE = instance
                instance
            }
        }
        
        /**
         * Database callback to seed initial data
         */
        private class DatabaseCallback : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                
                // Seed initial data in background
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        populateDatabase(database)
                    }
                }
            }
        }
        
        /**
         * Populate database with initial test data for development
         */
        private suspend fun populateDatabase(database: AppDatabase) {
            val memberDao = database.memberDao()
            val beverageDao = database.beverageDao()
            
            // Insert sample members
            val sampleMembers = listOf(
                Member(name = "Anna Schmidt", gridPosition = 0),
                Member(name = "Max Mueller", gridPosition = 1),
                Member(name = "Lisa Weber", gridPosition = 2),
                Member(name = "Tom Fischer", gridPosition = 3),
                Member(name = "Sarah Klein", gridPosition = 4),
                Member(name = "David Bauer", gridPosition = 5),
                Member(name = "Julia Wolf", gridPosition = 6),
                Member(name = "Michael Koch", gridPosition = 7),
                Member(name = "Laura Richter", gridPosition = 8),
                Member(name = "Christian Neuer", gridPosition = 9)
            )
            
            sampleMembers.forEach { member ->
                memberDao.insertMember(member)
            }
            
            // Insert sample beverages
            val sampleBeverages = listOf(
                Beverage(name = "Bier 0,5L", price = 2.50, category = "Alkohol"),
                Beverage(name = "Weizen 0,5L", price = 2.80, category = "Alkohol"),
                Beverage(name = "Radler 0,5L", price = 2.30, category = "Alkohol"),
                Beverage(name = "Cola 0,33L", price = 1.50, category = "Softdrinks"),
                Beverage(name = "Sprite 0,33L", price = 1.50, category = "Softdrinks"),
                Beverage(name = "Apfelschorle 0,33L", price = 1.80, category = "Softdrinks"),
                Beverage(name = "Wasser 0,5L", price = 1.00, category = "Softdrinks"),
                Beverage(name = "Kaffee", price = 1.20, category = "Heissgetraenke"),
                Beverage(name = "Tee", price = 1.00, category = "Heissgetraenke")
            )
            
            sampleBeverages.forEach { beverage ->
                beverageDao.insertBeverage(beverage)
            }
        }
    }
}