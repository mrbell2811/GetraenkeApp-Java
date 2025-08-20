package com.club.getraenkeapp.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.club.getraenkeapp.data.database.AppDatabase
import com.club.getraenkeapp.data.repository.AppRepository
import com.club.getraenkeapp.utils.ExportUtils
import kotlinx.coroutines.runBlocking
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Service for automatic backup functionality with configurable intervals.
 * 
 * Uses WorkManager for reliable background execution that respects system
 * battery optimization and doze mode constraints.
 */
class AutoBackupService : Service() {
    
    companion object {
        private const val TAG = "AutoBackupService"
        private const val WORK_NAME = "auto_backup_work"
        
        /**
         * Start automatic backup with configured interval
         */
        fun startAutoBackup(context: Context) {
            val config = runBlocking { 
                ExportUtils.getExportConfiguration(context) 
            }
            
            if (!config.autoBackupEnabled) {
                Log.d(TAG, "Auto backup is disabled")
                stopAutoBackup(context)
                return
            }
            
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED) // Need network for network path exports
                .setRequiresBatteryNotLow(true) // Don't run when battery is low
                .build()
            
            val backupWorkRequest = PeriodicWorkRequestBuilder<AutoBackupWorker>(
                config.backupIntervalHours.toLong(), TimeUnit.HOURS
            ).setConstraints(constraints)
             .build()
            
            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.REPLACE,
                backupWorkRequest
            )
            
            Log.d(TAG, "Auto backup scheduled every ${config.backupIntervalHours} hours")
        }
        
        /**
         * Stop automatic backup
         */
        fun stopAutoBackup(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME)
            Log.d(TAG, "Auto backup cancelled")
        }
        
        /**
         * Check if auto backup is currently scheduled
         */
        fun isAutoBackupEnabled(context: Context): Boolean {
            val workInfos = WorkManager.getInstance(context).getWorkInfosForUniqueWork(WORK_NAME)
            return try {
                val workInfo = workInfos.get().firstOrNull()
                workInfo != null && !workInfo.state.isFinished
            } catch (e: Exception) {
                Log.e(TAG, "Error checking work status", e)
                false
            }
        }
    }
    
    override fun onBind(intent: Intent?): IBinder? = null
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startAutoBackup(this)
        stopSelf() // Service is only used to start WorkManager
        return START_NOT_STICKY
    }
}

/**
 * WorkManager Worker for performing automatic backups
 */
class AutoBackupWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {
    
    companion object {
        private const val TAG = "AutoBackupWorker"
    }
    
    override fun doWork(): Result {
        return try {
            Log.d(TAG, "Starting automatic backup")
            
            val config = runBlocking {
                ExportUtils.getExportConfiguration(applicationContext)
            }
            
            if (!config.autoBackupEnabled) {
                Log.d(TAG, "Auto backup disabled, skipping")
                return Result.success()
            }
            
            // Check if it's time for backup
            val currentTime = System.currentTimeMillis()
            val timeSinceLastBackup = currentTime - config.lastBackupTime
            val intervalMillis = config.backupIntervalHours * 60 * 60 * 1000L
            
            if (timeSinceLastBackup < intervalMillis) {
                Log.d(TAG, "Not yet time for backup, skipping")
                return Result.success()
            }
            
            // Get database instance and repository
            val database = AppDatabase.getDatabase(applicationContext)
            val repository = AppRepository(
                database.memberDao(),
                database.beverageDao(),
                database.transactionDao(),
                database.archivedTransactionDao()
            )
            
            // Get recent transactions for backup
            val endDate = Date()
            val startDate = Date(endDate.time - (7 * 24 * 60 * 60 * 1000L)) // Last 7 days
            
            val transactions = runBlocking {
                repository.getTransactionsInDateRange(startDate.time, endDate.time)
            }
            
            if (transactions.isEmpty()) {
                Log.d(TAG, "No recent transactions to backup")
                return Result.success()
            }
            
            // Perform automatic backup
            val result = runBlocking {
                ExportUtils.performAutomaticBackup(applicationContext, transactions)
            }
            
            when (result) {
                is ExportUtils.ExportResult.Success -> {
                    Log.d(TAG, "Automatic backup successful: ${result.file.name}")
                    
                    // Update last backup time
                    val updatedConfig = config.copy(lastBackupTime = currentTime)
                    runBlocking {
                        ExportUtils.saveExportConfiguration(applicationContext, updatedConfig)
                    }
                    
                    Result.success()
                }
                is ExportUtils.ExportResult.Error -> {
                    Log.e(TAG, "Automatic backup failed: ${result.message}")
                    Result.retry()
                }
                null -> {
                    Log.d(TAG, "No backup performed (network path may be unavailable)")
                    Result.success()
                }
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "Error during automatic backup", e)
            Result.failure()
        }
    }
}