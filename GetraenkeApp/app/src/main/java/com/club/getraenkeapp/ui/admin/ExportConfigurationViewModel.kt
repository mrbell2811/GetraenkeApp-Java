package com.club.getraenkeapp.ui.admin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.club.getraenkeapp.data.database.AppDatabase
import com.club.getraenkeapp.data.repository.AppRepository
import com.club.getraenkeapp.utils.ExportUtils
import kotlinx.coroutines.launch
import java.io.File
import java.util.*

/**
 * ViewModel for Export Configuration Activity
 * 
 * Handles loading and saving export configuration, testing export paths,
 * and validating network connectivity.
 */
class ExportConfigurationViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: AppRepository
    
    // LiveData for configuration
    private val _configuration = MutableLiveData<ExportUtils.ExportConfiguration>()
    val configuration: LiveData<ExportUtils.ExportConfiguration> = _configuration
    
    // Loading state
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    // Message for user feedback
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message
    
    // Validation result
    private val _validationResult = MutableLiveData<ExportUtils.ValidationResult>()
    val validationResult: LiveData<ExportUtils.ValidationResult> = _validationResult
    
    init {
        val database = AppDatabase.getDatabase(application)
        repository = AppRepository(
            database.memberDao(),
            database.beverageDao(),
            database.transactionDao(),
            database.archivedTransactionDao()
        )
    }
    
    /**
     * Load current export configuration
     */
    fun loadConfiguration() {
        viewModelScope.launch {
            try {
                val config = ExportUtils.getExportConfiguration(getApplication())
                _configuration.value = config
            } catch (e: Exception) {
                _message.value = "Fehler beim Laden der Konfiguration: ${e.message}"
            }
        }
    }
    
    /**
     * Save export configuration
     */
    fun saveConfiguration(
        localPath: String,
        networkPath: String,
        autoBackupEnabled: Boolean,
        backupIntervalHours: Int
    ) {
        viewModelScope.launch {
            try {
                val config = ExportUtils.ExportConfiguration(
                    localPath = localPath,
                    networkPath = networkPath,
                    autoBackupEnabled = autoBackupEnabled,
                    backupIntervalHours = backupIntervalHours,
                    lastBackupTime = _configuration.value?.lastBackupTime ?: 0L
                )
                
                ExportUtils.saveExportConfiguration(getApplication(), config)
                _configuration.value = config
                _message.value = "Konfiguration erfolgreich gespeichert"
                
            } catch (e: Exception) {
                _message.value = "Fehler beim Speichern: ${e.message}"
            }
        }
    }
    
    /**
     * Test local export functionality
     */
    suspend fun testLocalExport(localPath: String) {
        _isLoading.value = true
        
        try {
            // Test with dummy data
            val testTransactions = repository.getTransactionsInDateRange(
                System.currentTimeMillis() - 86400000, // Last 24 hours
                System.currentTimeMillis()
            )
            
            if (testTransactions.isEmpty()) {
                _message.value = "Keine Transaktionen für Test verfügbar. Pfad wird auf Schreibbarkeit geprüft..."
                
                // Test directory creation and write access
                val testDir = File(localPath)
                if (!testDir.exists() && !testDir.mkdirs()) {
                    _message.value = "✗ Lokaler Ordner konnte nicht erstellt werden"
                    return
                }
                
                if (!testDir.canWrite()) {
                    _message.value = "✗ Lokaler Ordner ist nicht beschreibbar"
                    return
                }
                
                _message.value = "✓ Lokaler Pfad ist gültig und beschreibbar"
                return
            }
            
            // Temporarily save local path and perform export
            val originalConfig = ExportUtils.getExportConfiguration(getApplication())
            val testConfig = originalConfig.copy(localPath = localPath)
            ExportUtils.saveExportConfiguration(getApplication(), testConfig)
            
            val result = ExportUtils.exportToLocalDirectory(
                getApplication(),
                testTransactions,
                Date(System.currentTimeMillis() - 86400000),
                Date()
            )
            
            // Restore original config
            ExportUtils.saveExportConfiguration(getApplication(), originalConfig)
            
            when (result) {
                is ExportUtils.ExportResult.Success -> {
                    _message.value = "✓ Test Export erfolgreich: ${result.file.name}"
                }
                is ExportUtils.ExportResult.Error -> {
                    _message.value = "✗ Test Export fehlgeschlagen: ${result.message}"
                }
            }
            
        } catch (e: Exception) {
            _message.value = "✗ Test Export Fehler: ${e.message}"
        } finally {
            _isLoading.value = false
        }
    }
    
    /**
     * Validate network path accessibility
     */
    fun validateNetworkPath(networkPath: String) {
        viewModelScope.launch {
            _isLoading.value = true
            
            try {
                val result = ExportUtils.validateNetworkPath(networkPath)
                _validationResult.value = result
                
            } catch (e: Exception) {
                _validationResult.value = ExportUtils.ValidationResult.Error(
                    "Netzwerk-Validierung fehlgeschlagen: ${e.message}"
                )
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Clear message after showing
     */
    fun clearMessage() {
        _message.value = null
    }
    
    /**
     * Clear validation result after showing
     */
    fun clearValidationResult() {
        _validationResult.value = null
    }
    
    /**
     * Perform monthly closing - Export current month and prepare for new month
     */
    fun performMonthlyClosing() {
        viewModelScope.launch {
            _isLoading.value = true
            
            try {
                // Perform export with archival
                val result = ExportUtils.performMonthlyClosingWithArchive(
                    getApplication(),
                    repository
                )
                
                when (result) {
                    is ExportUtils.ExportResult.Success -> {
                        _message.value = "✓ ${result.message}"
                        
                        // Note: In a real implementation, you might want to:
                        // 1. Archive current month data to a separate table
                        // 2. Clear current statistics (if applicable)
                        // 3. Reset counters or monthly aggregations
                        // For now, we just export the data
                        
                    }
                    is ExportUtils.ExportResult.Error -> {
                        _message.value = "✗ Monatlicher Abschluss fehlgeschlagen: ${result.message}"
                    }
                }
                
            } catch (e: Exception) {
                _message.value = "✗ Monatlicher Abschluss Fehler: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}