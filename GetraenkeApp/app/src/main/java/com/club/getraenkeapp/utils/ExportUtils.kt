package com.club.getraenkeapp.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.core.content.FileProvider
import androidx.documentfile.provider.DocumentFile
import com.club.getraenkeapp.data.database.entities.TransactionWithDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException
import java.net.URI
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.text.SimpleDateFormat
import java.util.*

/**
 * ExportUtils for CSV generation and data export functionality
 * 
 * PATTERN: CSV generation with proper escaping, date range selection for export periods,
 * email intent integration for sending exports, file system write for network path exports.
 * 
 * CRITICAL: CSV export with proper escaping pattern from PRP
 */
object ExportUtils {
    
    private const val TAG = "ExportUtils"
    private const val CSV_HEADER = "Date,Time,Member,Beverage,Quantity,Unit Price,Total Price"
    private const val DATE_FORMAT = "yyyy-MM-dd"
    private const val TIME_FORMAT = "HH:mm:ss"
    private const val BACKUP_FILENAME_FORMAT = "getraenke_backup_%s.csv"
    
    // Export configuration keys for SharedPreferences
    const val PREF_LOCAL_EXPORT_PATH = "local_export_path"
    const val PREF_NETWORK_EXPORT_PATH = "network_export_path" 
    const val PREF_AUTO_BACKUP_ENABLED = "auto_backup_enabled"
    const val PREF_BACKUP_INTERVAL_HOURS = "backup_interval_hours"
    const val PREF_LAST_BACKUP_TIME = "last_backup_time"
    
    /**
     * Export transactions to CSV file
     * PATTERN: CSV generation with proper escaping from PRP example
     */
    suspend fun exportTransactionsToCSV(
        context: Context,
        transactions: List<TransactionWithDetails>,
        startDate: Date,
        endDate: Date
    ): File {
        return withContext(Dispatchers.IO) {
            // PATTERN: Create timestamped filename
            val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
            val filename = "getraenke_export_${dateFormat.format(startDate)}_to_${dateFormat.format(endDate)}.csv"
            
            val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), filename)
            
            file.printWriter().use { writer ->
                // CRITICAL: CSV header matching Google Sheets format
                writer.println(CSV_HEADER)
                
                val dateFormatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
                val timeFormatter = SimpleDateFormat(TIME_FORMAT, Locale.getDefault())
                
                transactions.forEach { transaction ->
                    val date = dateFormatter.format(Date(transaction.timestamp))
                    val time = timeFormatter.format(Date(transaction.timestamp))
                    
                    // GOTCHA: Escape commas and quotes in CSV data
                    val memberName = escapeCsvField(transaction.memberName)
                    val beverageName = escapeCsvField(transaction.beverageName)
                    
                    writer.println("$date,$time,$memberName,$beverageName,${transaction.quantity},${transaction.unitPrice},${transaction.totalPrice}")
                }
            }
            
            file
        }
    }
    
    /**
     * Escape CSV field for proper formatting
     * GOTCHA: Proper CSV escaping to prevent data corruption
     */
    private fun escapeCsvField(field: String): String {
        return if (field.contains(",") || field.contains("\"") || field.contains("\n")) {
            "\"${field.replace("\"", "\"\"")}\""
        } else {
            field
        }
    }
    
    /**
     * Share CSV file via email intent
     */
    fun shareCSVFile(context: Context, file: File): Intent {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/csv"
            putExtra(Intent.EXTRA_STREAM, androidx.core.content.FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            ))
            putExtra(Intent.EXTRA_SUBJECT, "GeträkeApp Export - ${file.nameWithoutExtension}")
            putExtra(Intent.EXTRA_TEXT, "Export der Getränke-Transaktionen im Anhang.")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        
        return Intent.createChooser(intent, "Export teilen")
    }
    
    /**
     * Generate export summary
     */
    fun generateExportSummary(
        transactions: List<TransactionWithDetails>,
        startDate: Date,
        endDate: Date
    ): ExportSummary {
        val totalRevenue = transactions.sumOf { it.totalPrice }
        val totalTransactions = transactions.size
        val uniqueMembers = transactions.map { it.memberName }.distinct().size
        val uniqueBeverages = transactions.map { it.beverageName }.distinct().size
        
        return ExportSummary(
            startDate = startDate,
            endDate = endDate,
            totalTransactions = totalTransactions,
            totalRevenue = totalRevenue,
            uniqueMembers = uniqueMembers,
            uniqueBeverages = uniqueBeverages
        )
    }
    
    /**
     * Export to configured local directory using Storage Access Framework
     */
    suspend fun exportToLocalDirectory(
        context: Context,
        transactions: List<TransactionWithDetails>,
        startDate: Date,
        endDate: Date
    ): ExportResult {
        return withContext(Dispatchers.IO) {
            try {
                val config = getExportConfiguration(context)
                val localPathUri = config.localPath
                
                if (localPathUri.isNullOrBlank()) {
                    return@withContext ExportResult.Error("Lokaler Export-Pfad nicht konfiguriert")
                }
                
                // Generate CSV content
                val csvContent = generateCSVContent(transactions, startDate, endDate)
                val fileName = generateExportFileName(startDate, endDate)
                
                // Handle both URI (SAF) and file path formats
                val result = if (localPathUri.startsWith("content://")) {
                    // Use Storage Access Framework
                    exportToSAFDirectory(context, localPathUri, fileName, csvContent)
                } else {
                    // Fallback to app's external files directory for manual paths
                    exportToAppDirectory(context, fileName, csvContent)
                }
                
                Log.d(TAG, "Lokaler Export erfolgreich")
                result
                
            } catch (e: Exception) {
                Log.e(TAG, "Fehler beim lokalen Export", e)
                ExportResult.Error("Lokaler Export fehlgeschlagen: ${e.message}")
            }
        }
    }
    
    /**
     * Export using Storage Access Framework
     */
    private fun exportToSAFDirectory(
        context: Context,
        directoryUriString: String,
        fileName: String,
        csvContent: String
    ): ExportResult {
        return try {
            val directoryUri = Uri.parse(directoryUriString)
            val documentFile = DocumentFile.fromTreeUri(context, directoryUri)
            
            if (documentFile == null || !documentFile.canWrite()) {
                return ExportResult.Error("Kein Zugriff auf gewählten Ordner")
            }
            
            // Check if file already exists and delete it
            val existingFile = documentFile.findFile(fileName)
            existingFile?.delete()
            
            // Create new file
            val newFile = documentFile.createFile("text/csv", fileName)
            if (newFile == null) {
                return ExportResult.Error("Datei konnte nicht erstellt werden")
            }
            
            // Write CSV content
            context.contentResolver.openOutputStream(newFile.uri)?.use { outputStream ->
                outputStream.write(csvContent.toByteArray(Charsets.UTF_8))
            }
            
            ExportResult.Success(
                File(fileName), // Dummy file object for compatibility
                "Export erfolgreich: $fileName"
            )
            
        } catch (e: Exception) {
            Log.e(TAG, "SAF Export Fehler", e)
            ExportResult.Error("SAF Export fehlgeschlagen: ${e.message}")
        }
    }
    
    /**
     * Export to app's external files directory (fallback)
     */
    private fun exportToAppDirectory(
        context: Context,
        fileName: String,
        csvContent: String
    ): ExportResult {
        return try {
            val externalDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
                ?: return ExportResult.Error("Externer Speicher nicht verfügbar")
            
            val exportFile = File(externalDir, fileName)
            exportFile.writeText(csvContent, Charsets.UTF_8)
            
            ExportResult.Success(exportFile, "Export erfolgreich: ${exportFile.absolutePath}")
            
        } catch (e: Exception) {
            Log.e(TAG, "App Directory Export Fehler", e)
            ExportResult.Error("App Directory Export fehlgeschlagen: ${e.message}")
        }
    }
    
    /**
     * Export to configured network directory
     */
    suspend fun exportToNetworkDirectory(
        context: Context,
        transactions: List<TransactionWithDetails>,
        startDate: Date,
        endDate: Date
    ): ExportResult {
        return withContext(Dispatchers.IO) {
            try {
                val config = getExportConfiguration(context)
                val networkPath = config.networkPath
                
                if (networkPath.isNullOrBlank()) {
                    return@withContext ExportResult.Error("Netzwerk Export-Pfad nicht konfiguriert")
                }
                
                // Generate CSV file in temp location first
                val tempFile = exportTransactionsToCSV(context, transactions, startDate, endDate)
                
                // Try to copy to network location
                val networkFile = copyToNetworkPath(tempFile, networkPath)
                
                Log.d(TAG, "Netzwerk Export erfolgreich: $networkPath")
                ExportResult.Success(networkFile, "Export erfolgreich ins Netzwerk: ${networkFile.name}")
                
            } catch (e: Exception) {
                Log.e(TAG, "Fehler beim Netzwerk Export", e)
                ExportResult.Error("Netzwerk Export fehlgeschlagen: ${e.message}")
            }
        }
    }
    
    /**
     * Perform automatic backup if conditions are met
     */
    suspend fun performAutomaticBackup(
        context: Context,
        transactions: List<TransactionWithDetails>
    ): ExportResult? {
        return withContext(Dispatchers.IO) {
            try {
                val config = getExportConfiguration(context)
                
                if (!config.autoBackupEnabled) {
                    Log.d(TAG, "Automatisches Backup deaktiviert")
                    return@withContext null
                }
                
                val intervalHours = config.backupIntervalHours
                val lastBackupTime = config.lastBackupTime
                val currentTime = System.currentTimeMillis()
                val hoursSinceLastBackup = (currentTime - lastBackupTime) / (1000 * 60 * 60)
                
                if (hoursSinceLastBackup < intervalHours) {
                    Log.d(TAG, "Backup-Intervall noch nicht erreicht (${hoursSinceLastBackup}h von ${intervalHours}h)")
                    return@withContext null
                }
                
                // Perform backup to network location
                val endDate = Date()
                val startDate = Date(0L) // All transactions
                
                val result = exportToNetworkDirectory(context, transactions, startDate, endDate)
                
                // Update last backup time if successful
                if (result is ExportResult.Success) {
                    val updatedConfig = config.copy(lastBackupTime = currentTime)
                    saveExportConfiguration(context, updatedConfig)
                    Log.d(TAG, "Automatisches Backup erfolgreich durchgeführt")
                }
                
                result
                
            } catch (e: Exception) {
                Log.e(TAG, "Fehler beim automatischen Backup", e)
                ExportResult.Error("Automatisches Backup fehlgeschlagen: ${e.message}")
            }
        }
    }
    
    /**
     * Copy file to network path (handles both UNC paths and mapped drives)
     */
    private fun copyToNetworkPath(sourceFile: File, networkPath: String): File {
        return try {
            val targetPath = when {
                networkPath.startsWith("\\\\") -> {
                    // UNC path like \\server\share\folder
                    Paths.get(URI("file://$networkPath"))
                }
                networkPath.matches(Regex("[A-Z]:\\\\.*")) -> {
                    // Mapped drive like Z:\folder
                    Paths.get(networkPath)
                }
                else -> {
                    // Assume local path
                    Paths.get(networkPath)
                }
            }
            
            // Ensure target directory exists
            Files.createDirectories(targetPath)
            
            // Create target file path
            val targetFilePath = targetPath.resolve(sourceFile.name)
            
            // Copy file
            Files.copy(sourceFile.toPath(), targetFilePath, StandardCopyOption.REPLACE_EXISTING)
            
            targetFilePath.toFile()
            
        } catch (e: Exception) {
            throw IOException("Netzwerk-Kopie fehlgeschlagen: ${e.message}", e)
        }
    }
    
    /**
     * Validate network path accessibility
     */
    fun validateNetworkPath(networkPath: String): ValidationResult {
        return try {
            if (networkPath.isBlank()) {
                return ValidationResult.Error("Pfad darf nicht leer sein")
            }
            
            val path = when {
                networkPath.startsWith("\\\\") -> {
                    Paths.get(URI("file://$networkPath"))
                }
                networkPath.matches(Regex("[A-Z]:\\\\.*")) -> {
                    Paths.get(networkPath)
                }
                else -> {
                    Paths.get(networkPath)
                }
            }
            
            if (!Files.exists(path)) {
                // Try to create directory
                Files.createDirectories(path)
            }
            
            if (!Files.isWritable(path)) {
                return ValidationResult.Error("Pfad ist nicht beschreibbar")
            }
            
            ValidationResult.Success("Pfad ist gültig und beschreibbar")
            
        } catch (e: Exception) {
            ValidationResult.Error("Pfad-Validierung fehlgeschlagen: ${e.message}")
        }
    }
    
    /**
     * Get export configuration summary
     */
    fun getExportConfiguration(context: Context): ExportConfiguration {
        val prefs = context.getSharedPreferences("export_settings", Context.MODE_PRIVATE)
        
        return ExportConfiguration(
            localPath = prefs.getString(PREF_LOCAL_EXPORT_PATH, "") ?: "",
            networkPath = prefs.getString(PREF_NETWORK_EXPORT_PATH, "") ?: "",
            autoBackupEnabled = prefs.getBoolean(PREF_AUTO_BACKUP_ENABLED, false),
            backupIntervalHours = prefs.getInt(PREF_BACKUP_INTERVAL_HOURS, 24),
            lastBackupTime = prefs.getLong(PREF_LAST_BACKUP_TIME, 0L)
        )
    }
    
    /**
     * Save export configuration
     */
    fun saveExportConfiguration(context: Context, config: ExportConfiguration) {
        val prefs = context.getSharedPreferences("export_settings", Context.MODE_PRIVATE)
        
        prefs.edit().apply {
            putString(PREF_LOCAL_EXPORT_PATH, config.localPath)
            putString(PREF_NETWORK_EXPORT_PATH, config.networkPath)
            putBoolean(PREF_AUTO_BACKUP_ENABLED, config.autoBackupEnabled)
            putInt(PREF_BACKUP_INTERVAL_HOURS, config.backupIntervalHours)
            putLong(PREF_LAST_BACKUP_TIME, config.lastBackupTime)
        }.apply()
    }
    
    /**
     * Generate enhanced CSV content with transactions and consumption matrix
     */
    private fun generateCSVContent(
        transactions: List<TransactionWithDetails>,
        startDate: Date,
        endDate: Date
    ): String {
        val csv = StringBuilder()
        
        // Section 1: Transaction Log
        csv.appendLine("=== TRANSACTION LOG ===")
        csv.appendLine(CSV_HEADER)
        
        val dateFormatter = SimpleDateFormat(DATE_FORMAT, Locale.GERMAN)
        val timeFormatter = SimpleDateFormat(TIME_FORMAT, Locale.GERMAN)
        
        for (transactionDetail in transactions) {
            val transactionDate = Date(transactionDetail.timestamp)
            val dateStr = dateFormatter.format(transactionDate)
            val timeStr = timeFormatter.format(transactionDate)
            
            csv.appendLine(
                "${escapeCSV(dateStr)}," +
                "${escapeCSV(timeStr)}," +
                "${escapeCSV(transactionDetail.memberName)}," +
                "${escapeCSV(transactionDetail.beverageName)}," +
                "${transactionDetail.quantity}," +
                "${String.format("%.2f", transactionDetail.unitPrice)}," +
                "${String.format("%.2f", transactionDetail.totalPrice)}"
            )
        }
        
        // Section 2: Consumption Matrix
        csv.appendLine()
        csv.appendLine("=== CONSUMPTION MATRIX ===")
        
        // Generate consumption matrix
        val consumptionMatrix = generateConsumptionMatrix(transactions)
        csv.append(consumptionMatrix)
        
        return csv.toString()
    }
    
    /**
     * Generate consumption matrix (Drinks as rows, Members as columns)
     */
    private fun generateConsumptionMatrix(transactions: List<TransactionWithDetails>): String {
        val matrix = StringBuilder()
        
        // Get unique beverages and members
        val beverages = transactions.map { it.beverageName }.distinct().sorted()
        val members = transactions.map { it.memberName }.distinct().sorted()
        
        if (beverages.isEmpty() || members.isEmpty()) {
            return "No data available for consumption matrix\n"
        }
        
        // Create consumption map: beverage -> member -> total quantity
        val consumptionMap = mutableMapOf<String, MutableMap<String, Int>>()
        
        transactions.forEach { transaction ->
            val beverageMap = consumptionMap.getOrPut(transaction.beverageName) { mutableMapOf() }
            val currentQuantity = beverageMap.getOrDefault(transaction.memberName, 0)
            beverageMap[transaction.memberName] = currentQuantity + transaction.quantity
        }
        
        // Build header row (Beverage, Member1, Member2, ...)
        matrix.append("Beverage")
        members.forEach { member ->
            matrix.append(",${escapeCSV(member)}")
        }
        matrix.append(",Total\n")
        
        // Build data rows
        beverages.forEach { beverage ->
            matrix.append(escapeCSV(beverage))
            var beverageTotal = 0
            
            members.forEach { member ->
                val quantity = consumptionMap[beverage]?.get(member) ?: 0
                matrix.append(",$quantity")
                beverageTotal += quantity
            }
            
            matrix.append(",$beverageTotal\n")
        }
        
        // Build totals row
        matrix.append("TOTAL")
        members.forEach { member ->
            val memberTotal = beverages.sumOf { beverage ->
                consumptionMap[beverage]?.get(member) ?: 0
            }
            matrix.append(",$memberTotal")
        }
        val grandTotal = transactions.sumOf { it.quantity }
        matrix.append(",$grandTotal\n")
        
        return matrix.toString()
    }
    
    /**
     * Perform monthly closing with archival - Export data and archive to historical table
     */
    suspend fun performMonthlyClosingWithArchive(
        context: Context,
        repository: com.club.getraenkeapp.data.repository.AppRepository
    ): ExportResult {
        return withContext(Dispatchers.IO) {
            try {
                Log.d(TAG, "Starting monthly closing with archive process")
                
                // Perform archival first
                val archiveResult = repository.performMonthlyArchive()
                
                when (archiveResult) {
                    is com.club.getraenkeapp.data.repository.AppRepository.ArchiveResult.Error -> {
                        return@withContext ExportResult.Error("Archivierung fehlgeschlagen: ${archiveResult.message}")
                    }
                    is com.club.getraenkeapp.data.repository.AppRepository.ArchiveResult.Success -> {
                        Log.d(TAG, "Archive successful: ${archiveResult.message}")
                        
                        // Get archived transactions for export
                        val archivedTransactions = repository.getArchivedTransactionsByPeriod(archiveResult.archivePeriod)
                        
                        // Convert archived transactions to TransactionWithDetails format for export
                        val transactionDetails = archivedTransactions.map { archived ->
                            TransactionWithDetails(
                                id = archived.originalTransactionId,
                                memberName = archived.memberName,
                                beverageName = archived.beverageName,
                                quantity = archived.quantity,
                                unitPrice = archived.unitPrice,
                                totalPrice = archived.totalPrice,
                                timestamp = archived.transactionTimestamp
                            )
                        }
                        
                        // Generate closing export
                        val monthStart = Date() // Use current date for filename
                        val csvContent = generateCSVContent(transactionDetails, monthStart, monthStart)
                        val fileName = generateMonthlyClosingFileName(monthStart)
                        
                        // Try local export first
                        val config = getExportConfiguration(context)
                        val localResult = if (!config.localPath.isNullOrBlank()) {
                            if (config.localPath.startsWith("content://")) {
                                exportToSAFDirectory(context, config.localPath, fileName, csvContent)
                            } else {
                                exportToAppDirectory(context, fileName, csvContent)
                            }
                        } else null
                        
                        // Try network export
                        val networkResult = if (!config.networkPath.isNullOrBlank()) {
                            try {
                                val tempFile = File(context.cacheDir, fileName)
                                tempFile.writeText(csvContent, Charsets.UTF_8)
                                val networkFile = copyToNetworkPath(tempFile, config.networkPath)
                                ExportResult.Success(networkFile, "Monatlicher Abschluss ins Netzwerk exportiert")
                            } catch (e: Exception) {
                                Log.e(TAG, "Network export during monthly closing failed", e)
                                null
                            }
                        } else null
                        
                        // Return result based on what succeeded
                        when {
                            localResult is ExportResult.Success -> {
                                Log.d(TAG, "Monthly closing completed successfully")
                                ExportResult.Success(
                                    localResult.file, 
                                    "Monatlicher Abschluss erfolgreich: ${fileName} - ${archiveResult.message}"
                                )
                            }
                            networkResult is ExportResult.Success -> {
                                Log.d(TAG, "Monthly closing completed via network")
                                ExportResult.Success(
                                    networkResult.file,
                                    "Netzwerk Export erfolgreich - ${archiveResult.message}"
                                )
                            }
                            else -> {
                                ExportResult.Error("Export fehlgeschlagen, aber Archivierung erfolgreich: ${archiveResult.message}")
                            }
                        }
                    }
                }
                
            } catch (e: Exception) {
                Log.e(TAG, "Monthly closing failed", e)
                ExportResult.Error("Monatlicher Abschluss fehlgeschlagen: ${e.message}")
            }
        }
    }
    
    /**
     * Generate filename for monthly closing export
     */
    private fun generateMonthlyClosingFileName(monthStart: Date): String {
        val dateFormatter = SimpleDateFormat("yyyy-MM", Locale.GERMAN)
        val monthName = SimpleDateFormat("MMMM_yyyy", Locale.GERMAN).format(monthStart)
        return "${monthName}_Abschluss.csv"
    }
    
    /**
     * Generate filename for export
     */
    private fun generateExportFileName(startDate: Date, endDate: Date): String {
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.GERMAN)
        val startStr = dateFormatter.format(startDate)
        val endStr = dateFormatter.format(endDate)
        return "getraenke_export_${startStr}_to_${endStr}.csv"
    }
    
    /**
     * Escape CSV field according to RFC 4180
     */
    private fun escapeCSV(field: String): String {
        return if (field.contains(",") || field.contains("\"") || field.contains("\n")) {
            "\"${field.replace("\"", "\"\"")}\""
        } else {
            field
        }
    }
    
    // Data Classes
    
    /**
     * Export result sealed class
     */
    sealed class ExportResult {
        data class Success(val file: File, val message: String) : ExportResult()
        data class Error(val message: String) : ExportResult()
    }
    
    /**
     * Validation result sealed class
     */
    sealed class ValidationResult {
        data class Success(val message: String) : ValidationResult()
        data class Error(val message: String) : ValidationResult()
    }
    
    /**
     * Export configuration data class
     */
    data class ExportConfiguration(
        val localPath: String,
        val networkPath: String,
        val autoBackupEnabled: Boolean,
        val backupIntervalHours: Int,
        val lastBackupTime: Long
    )
    
    /**
     * Export summary data class
     */
    data class ExportSummary(
        val startDate: Date,
        val endDate: Date,
        val totalTransactions: Int,
        val totalRevenue: Double,
        val uniqueMembers: Int,
        val uniqueBeverages: Int
    )
}