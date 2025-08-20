package com.club.getraenkeapp.utils;

/**
 * ExportUtils for CSV generation and data export functionality
 *
 * PATTERN: CSV generation with proper escaping, date range selection for export periods,
 * email intent integration for sending exports, file system write for network path exports.
 *
 * CRITICAL: CSV export with proper escaping pattern from PRP
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0004>?@AB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0004H\u0002J\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0004H\u0002J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0004H\u0002J \u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0002J4\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 H\u0086@\u00a2\u0006\u0002\u0010\"J4\u0010#\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 H\u0086@\u00a2\u0006\u0002\u0010\"J(\u0010$\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010%\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0002J4\u0010&\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 H\u0086@\u00a2\u0006\u0002\u0010\"J&\u0010\'\u001a\u00020\u00042\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 H\u0002J\u0016\u0010(\u001a\u00020\u00042\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0002J\u0018\u0010)\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 H\u0002J$\u0010*\u001a\u00020+2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 J\u0010\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020 H\u0002J\u000e\u0010.\u001a\u00020/2\u0006\u0010\u0017\u001a\u00020\u0018J&\u00100\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0086@\u00a2\u0006\u0002\u00101J\u001e\u00102\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u00103\u001a\u000204H\u0086@\u00a2\u0006\u0002\u00105J\u0016\u00106\u001a\u0002072\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u00108\u001a\u00020/J\u0016\u00109\u001a\u00020:2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010;\u001a\u00020\u000fJ\u000e\u0010<\u001a\u00020=2\u0006\u0010\u0011\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006B"}, d2 = {"Lcom/club/getraenkeapp/utils/ExportUtils;", "", "()V", "BACKUP_FILENAME_FORMAT", "", "CSV_HEADER", "DATE_FORMAT", "PREF_AUTO_BACKUP_ENABLED", "PREF_BACKUP_INTERVAL_HOURS", "PREF_LAST_BACKUP_TIME", "PREF_LOCAL_EXPORT_PATH", "PREF_NETWORK_EXPORT_PATH", "TAG", "TIME_FORMAT", "copyToNetworkPath", "Ljava/io/File;", "sourceFile", "networkPath", "escapeCSV", "field", "escapeCsvField", "exportToAppDirectory", "Lcom/club/getraenkeapp/utils/ExportUtils$ExportResult;", "context", "Landroid/content/Context;", "fileName", "csvContent", "exportToLocalDirectory", "transactions", "", "Lcom/club/getraenkeapp/data/database/entities/TransactionWithDetails;", "startDate", "Ljava/util/Date;", "endDate", "(Landroid/content/Context;Ljava/util/List;Ljava/util/Date;Ljava/util/Date;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "exportToNetworkDirectory", "exportToSAFDirectory", "directoryUriString", "exportTransactionsToCSV", "generateCSVContent", "generateConsumptionMatrix", "generateExportFileName", "generateExportSummary", "Lcom/club/getraenkeapp/utils/ExportUtils$ExportSummary;", "generateMonthlyClosingFileName", "monthStart", "getExportConfiguration", "Lcom/club/getraenkeapp/utils/ExportUtils$ExportConfiguration;", "performAutomaticBackup", "(Landroid/content/Context;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "performMonthlyClosingWithArchive", "repository", "Lcom/club/getraenkeapp/data/repository/AppRepository;", "(Landroid/content/Context;Lcom/club/getraenkeapp/data/repository/AppRepository;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveExportConfiguration", "", "config", "shareCSVFile", "Landroid/content/Intent;", "file", "validateNetworkPath", "Lcom/club/getraenkeapp/utils/ExportUtils$ValidationResult;", "ExportConfiguration", "ExportResult", "ExportSummary", "ValidationResult", "app_debug"})
public final class ExportUtils {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "ExportUtils";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CSV_HEADER = "Date,Time,Member,Beverage,Quantity,Unit Price,Total Price";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String DATE_FORMAT = "yyyy-MM-dd";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TIME_FORMAT = "HH:mm:ss";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String BACKUP_FILENAME_FORMAT = "getraenke_backup_%s.csv";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PREF_LOCAL_EXPORT_PATH = "local_export_path";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PREF_NETWORK_EXPORT_PATH = "network_export_path";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PREF_AUTO_BACKUP_ENABLED = "auto_backup_enabled";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PREF_BACKUP_INTERVAL_HOURS = "backup_interval_hours";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PREF_LAST_BACKUP_TIME = "last_backup_time";
    @org.jetbrains.annotations.NotNull()
    public static final com.club.getraenkeapp.utils.ExportUtils INSTANCE = null;
    
    private ExportUtils() {
        super();
    }
    
    /**
     * Export transactions to CSV file
     * PATTERN: CSV generation with proper escaping from PRP example
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object exportTransactionsToCSV(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<com.club.getraenkeapp.data.database.entities.TransactionWithDetails> transactions, @org.jetbrains.annotations.NotNull()
    java.util.Date startDate, @org.jetbrains.annotations.NotNull()
    java.util.Date endDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.io.File> $completion) {
        return null;
    }
    
    /**
     * Escape CSV field for proper formatting
     * GOTCHA: Proper CSV escaping to prevent data corruption
     */
    private final java.lang.String escapeCsvField(java.lang.String field) {
        return null;
    }
    
    /**
     * Share CSV file via email intent
     */
    @org.jetbrains.annotations.NotNull()
    public final android.content.Intent shareCSVFile(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.io.File file) {
        return null;
    }
    
    /**
     * Generate export summary
     */
    @org.jetbrains.annotations.NotNull()
    public final com.club.getraenkeapp.utils.ExportUtils.ExportSummary generateExportSummary(@org.jetbrains.annotations.NotNull()
    java.util.List<com.club.getraenkeapp.data.database.entities.TransactionWithDetails> transactions, @org.jetbrains.annotations.NotNull()
    java.util.Date startDate, @org.jetbrains.annotations.NotNull()
    java.util.Date endDate) {
        return null;
    }
    
    /**
     * Export to configured local directory using Storage Access Framework
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object exportToLocalDirectory(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<com.club.getraenkeapp.data.database.entities.TransactionWithDetails> transactions, @org.jetbrains.annotations.NotNull()
    java.util.Date startDate, @org.jetbrains.annotations.NotNull()
    java.util.Date endDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.club.getraenkeapp.utils.ExportUtils.ExportResult> $completion) {
        return null;
    }
    
    /**
     * Export using Storage Access Framework
     */
    private final com.club.getraenkeapp.utils.ExportUtils.ExportResult exportToSAFDirectory(android.content.Context context, java.lang.String directoryUriString, java.lang.String fileName, java.lang.String csvContent) {
        return null;
    }
    
    /**
     * Export to app's external files directory (fallback)
     */
    private final com.club.getraenkeapp.utils.ExportUtils.ExportResult exportToAppDirectory(android.content.Context context, java.lang.String fileName, java.lang.String csvContent) {
        return null;
    }
    
    /**
     * Export to configured network directory
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object exportToNetworkDirectory(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<com.club.getraenkeapp.data.database.entities.TransactionWithDetails> transactions, @org.jetbrains.annotations.NotNull()
    java.util.Date startDate, @org.jetbrains.annotations.NotNull()
    java.util.Date endDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.club.getraenkeapp.utils.ExportUtils.ExportResult> $completion) {
        return null;
    }
    
    /**
     * Perform automatic backup if conditions are met
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object performAutomaticBackup(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<com.club.getraenkeapp.data.database.entities.TransactionWithDetails> transactions, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.club.getraenkeapp.utils.ExportUtils.ExportResult> $completion) {
        return null;
    }
    
    /**
     * Copy file to network path (handles both UNC paths and mapped drives)
     */
    private final java.io.File copyToNetworkPath(java.io.File sourceFile, java.lang.String networkPath) {
        return null;
    }
    
    /**
     * Validate network path accessibility
     */
    @org.jetbrains.annotations.NotNull()
    public final com.club.getraenkeapp.utils.ExportUtils.ValidationResult validateNetworkPath(@org.jetbrains.annotations.NotNull()
    java.lang.String networkPath) {
        return null;
    }
    
    /**
     * Get export configuration summary
     */
    @org.jetbrains.annotations.NotNull()
    public final com.club.getraenkeapp.utils.ExportUtils.ExportConfiguration getExportConfiguration(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    /**
     * Save export configuration
     */
    public final void saveExportConfiguration(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.utils.ExportUtils.ExportConfiguration config) {
    }
    
    /**
     * Generate enhanced CSV content with transactions and consumption matrix
     */
    private final java.lang.String generateCSVContent(java.util.List<com.club.getraenkeapp.data.database.entities.TransactionWithDetails> transactions, java.util.Date startDate, java.util.Date endDate) {
        return null;
    }
    
    /**
     * Generate consumption matrix (Drinks as rows, Members as columns)
     */
    private final java.lang.String generateConsumptionMatrix(java.util.List<com.club.getraenkeapp.data.database.entities.TransactionWithDetails> transactions) {
        return null;
    }
    
    /**
     * Perform monthly closing with archival - Export data and archive to historical table
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object performMonthlyClosingWithArchive(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.repository.AppRepository repository, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.club.getraenkeapp.utils.ExportUtils.ExportResult> $completion) {
        return null;
    }
    
    /**
     * Generate filename for monthly closing export
     */
    private final java.lang.String generateMonthlyClosingFileName(java.util.Date monthStart) {
        return null;
    }
    
    /**
     * Generate filename for export
     */
    private final java.lang.String generateExportFileName(java.util.Date startDate, java.util.Date endDate) {
        return null;
    }
    
    /**
     * Escape CSV field according to RFC 4180
     */
    private final java.lang.String escapeCSV(java.lang.String field) {
        return null;
    }
    
    /**
     * Export configuration data class
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0015\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\bH\u00c6\u0003J\t\u0010\u0019\u001a\u00020\nH\u00c6\u0003J;\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001J\u0013\u0010\u001b\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001d\u001a\u00020\bH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013\u00a8\u0006\u001f"}, d2 = {"Lcom/club/getraenkeapp/utils/ExportUtils$ExportConfiguration;", "", "localPath", "", "networkPath", "autoBackupEnabled", "", "backupIntervalHours", "", "lastBackupTime", "", "(Ljava/lang/String;Ljava/lang/String;ZIJ)V", "getAutoBackupEnabled", "()Z", "getBackupIntervalHours", "()I", "getLastBackupTime", "()J", "getLocalPath", "()Ljava/lang/String;", "getNetworkPath", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
    public static final class ExportConfiguration {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String localPath = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String networkPath = null;
        private final boolean autoBackupEnabled = false;
        private final int backupIntervalHours = 0;
        private final long lastBackupTime = 0L;
        
        public ExportConfiguration(@org.jetbrains.annotations.NotNull()
        java.lang.String localPath, @org.jetbrains.annotations.NotNull()
        java.lang.String networkPath, boolean autoBackupEnabled, int backupIntervalHours, long lastBackupTime) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getLocalPath() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getNetworkPath() {
            return null;
        }
        
        public final boolean getAutoBackupEnabled() {
            return false;
        }
        
        public final int getBackupIntervalHours() {
            return 0;
        }
        
        public final long getLastBackupTime() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final int component4() {
            return 0;
        }
        
        public final long component5() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.club.getraenkeapp.utils.ExportUtils.ExportConfiguration copy(@org.jetbrains.annotations.NotNull()
        java.lang.String localPath, @org.jetbrains.annotations.NotNull()
        java.lang.String networkPath, boolean autoBackupEnabled, int backupIntervalHours, long lastBackupTime) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    /**
     * Export result sealed class
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/club/getraenkeapp/utils/ExportUtils$ExportResult;", "", "()V", "Error", "Success", "Lcom/club/getraenkeapp/utils/ExportUtils$ExportResult$Error;", "Lcom/club/getraenkeapp/utils/ExportUtils$ExportResult$Success;", "app_debug"})
    public static abstract class ExportResult {
        
        private ExportResult() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/club/getraenkeapp/utils/ExportUtils$ExportResult$Error;", "Lcom/club/getraenkeapp/utils/ExportUtils$ExportResult;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
        public static final class Error extends com.club.getraenkeapp.utils.ExportUtils.ExportResult {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String message = null;
            
            public Error(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMessage() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.club.getraenkeapp.utils.ExportUtils.ExportResult.Error copy(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lcom/club/getraenkeapp/utils/ExportUtils$ExportResult$Success;", "Lcom/club/getraenkeapp/utils/ExportUtils$ExportResult;", "file", "Ljava/io/File;", "message", "", "(Ljava/io/File;Ljava/lang/String;)V", "getFile", "()Ljava/io/File;", "getMessage", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
        public static final class Success extends com.club.getraenkeapp.utils.ExportUtils.ExportResult {
            @org.jetbrains.annotations.NotNull()
            private final java.io.File file = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String message = null;
            
            public Success(@org.jetbrains.annotations.NotNull()
            java.io.File file, @org.jetbrains.annotations.NotNull()
            java.lang.String message) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.io.File getFile() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMessage() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.io.File component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.club.getraenkeapp.utils.ExportUtils.ExportResult.Success copy(@org.jetbrains.annotations.NotNull()
            java.io.File file, @org.jetbrains.annotations.NotNull()
            java.lang.String message) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
    }
    
    /**
     * Export summary data class
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\bH\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0006H\u00c6\u0003JE\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020\u0006H\u00d6\u0001J\t\u0010 \u001a\u00020!H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\n\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012\u00a8\u0006\""}, d2 = {"Lcom/club/getraenkeapp/utils/ExportUtils$ExportSummary;", "", "startDate", "Ljava/util/Date;", "endDate", "totalTransactions", "", "totalRevenue", "", "uniqueMembers", "uniqueBeverages", "(Ljava/util/Date;Ljava/util/Date;IDII)V", "getEndDate", "()Ljava/util/Date;", "getStartDate", "getTotalRevenue", "()D", "getTotalTransactions", "()I", "getUniqueBeverages", "getUniqueMembers", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "", "app_debug"})
    public static final class ExportSummary {
        @org.jetbrains.annotations.NotNull()
        private final java.util.Date startDate = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.Date endDate = null;
        private final int totalTransactions = 0;
        private final double totalRevenue = 0.0;
        private final int uniqueMembers = 0;
        private final int uniqueBeverages = 0;
        
        public ExportSummary(@org.jetbrains.annotations.NotNull()
        java.util.Date startDate, @org.jetbrains.annotations.NotNull()
        java.util.Date endDate, int totalTransactions, double totalRevenue, int uniqueMembers, int uniqueBeverages) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Date getStartDate() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Date getEndDate() {
            return null;
        }
        
        public final int getTotalTransactions() {
            return 0;
        }
        
        public final double getTotalRevenue() {
            return 0.0;
        }
        
        public final int getUniqueMembers() {
            return 0;
        }
        
        public final int getUniqueBeverages() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Date component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Date component2() {
            return null;
        }
        
        public final int component3() {
            return 0;
        }
        
        public final double component4() {
            return 0.0;
        }
        
        public final int component5() {
            return 0;
        }
        
        public final int component6() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.club.getraenkeapp.utils.ExportUtils.ExportSummary copy(@org.jetbrains.annotations.NotNull()
        java.util.Date startDate, @org.jetbrains.annotations.NotNull()
        java.util.Date endDate, int totalTransactions, double totalRevenue, int uniqueMembers, int uniqueBeverages) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    /**
     * Validation result sealed class
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/club/getraenkeapp/utils/ExportUtils$ValidationResult;", "", "()V", "Error", "Success", "Lcom/club/getraenkeapp/utils/ExportUtils$ValidationResult$Error;", "Lcom/club/getraenkeapp/utils/ExportUtils$ValidationResult$Success;", "app_debug"})
    public static abstract class ValidationResult {
        
        private ValidationResult() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/club/getraenkeapp/utils/ExportUtils$ValidationResult$Error;", "Lcom/club/getraenkeapp/utils/ExportUtils$ValidationResult;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
        public static final class Error extends com.club.getraenkeapp.utils.ExportUtils.ValidationResult {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String message = null;
            
            public Error(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMessage() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.club.getraenkeapp.utils.ExportUtils.ValidationResult.Error copy(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/club/getraenkeapp/utils/ExportUtils$ValidationResult$Success;", "Lcom/club/getraenkeapp/utils/ExportUtils$ValidationResult;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
        public static final class Success extends com.club.getraenkeapp.utils.ExportUtils.ValidationResult {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String message = null;
            
            public Success(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMessage() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.club.getraenkeapp.utils.ExportUtils.ValidationResult.Success copy(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
    }
}