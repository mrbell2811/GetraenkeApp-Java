package com.club.getraenkeapp.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import java.security.MessageDigest
import java.nio.charset.StandardCharsets

/**
 * SharedPreferencesManager for secure app settings storage
 * 
 * PATTERN: Singleton for app settings storage, encrypted SharedPreferences for sensitive data,
 * admin password hash storage, session timeout configuration.
 * 
 * CRITICAL: Hashed password storage (never plain text), session management with timeout
 */
class SharedPreferencesManager private constructor(context: Context) {
    
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()
    
    // Encrypted preferences for sensitive data
    private val securePrefs: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        "admin_security_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
    
    // Regular preferences for non-sensitive settings
    private val regularPrefs: SharedPreferences = context.getSharedPreferences(
        "app_settings_prefs",
        Context.MODE_PRIVATE
    )
    
    companion object {
        @Volatile
        private var INSTANCE: SharedPreferencesManager? = null
        
        // Keys for secure preferences
        private const val KEY_ADMIN_PASSWORD_HASH = "admin_password_hash"
        private const val KEY_SESSION_TOKEN = "session_token"
        private const val KEY_SESSION_TIMESTAMP = "session_timestamp"
        
        // Keys for regular preferences
        private const val KEY_SESSION_TIMEOUT_MINUTES = "session_timeout_minutes"
        private const val KEY_OPERATING_HOURS_START = "operating_hours_start"
        private const val KEY_OPERATING_HOURS_END = "operating_hours_end"
        private const val KEY_BACKUP_ENABLED = "backup_enabled"
        private const val KEY_LAST_BACKUP_TIMESTAMP = "last_backup_timestamp"
        
        // Default values
        private const val DEFAULT_SESSION_TIMEOUT_MINUTES = 30
        private const val DEFAULT_OPERATING_HOURS_START = "08:00"
        private const val DEFAULT_OPERATING_HOURS_END = "22:00"
        private const val DEFAULT_ADMIN_PASSWORD = "admin123" // Default password for first setup
        
        fun getInstance(context: Context): SharedPreferencesManager {
            return INSTANCE ?: synchronized(this) {
                val instance = SharedPreferencesManager(context.applicationContext)
                INSTANCE = instance
                instance
            }
        }
    }
    
    init {
        // Initialize default admin password if not set
        if (!hasAdminPassword()) {
            setAdminPassword(DEFAULT_ADMIN_PASSWORD)
        }
    }
    
    // ========== Admin Authentication ==========
    
    /**
     * Set admin password (hashed)
     */
    fun setAdminPassword(password: String) {
        val hashedPassword = hashPassword(password)
        securePrefs.edit()
            .putString(KEY_ADMIN_PASSWORD_HASH, hashedPassword)
            .apply()
    }
    
    /**
     * Verify admin password
     */
    fun verifyAdminPassword(password: String): Boolean {
        val storedHash = securePrefs.getString(KEY_ADMIN_PASSWORD_HASH, null)
        val inputHash = hashPassword(password)
        return storedHash == inputHash
    }
    
    /**
     * Check if admin password is set
     */
    fun hasAdminPassword(): Boolean {
        return securePrefs.getString(KEY_ADMIN_PASSWORD_HASH, null) != null
    }
    
    /**
     * Hash password using SHA-256
     */
    private fun hashPassword(password: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(password.toByteArray(StandardCharsets.UTF_8))
        return hashBytes.joinToString("") { "%02x".format(it) }
    }
    
    // ========== Session Management ==========
    
    /**
     * Create admin session
     */
    fun createAdminSession(): String {
        val sessionToken = generateSessionToken()
        val currentTimestamp = System.currentTimeMillis()
        
        securePrefs.edit()
            .putString(KEY_SESSION_TOKEN, sessionToken)
            .putLong(KEY_SESSION_TIMESTAMP, currentTimestamp)
            .apply()
            
        return sessionToken
    }
    
    /**
     * Validate admin session
     */
    fun isValidAdminSession(): Boolean {
        val sessionToken = securePrefs.getString(KEY_SESSION_TOKEN, null)
        val sessionTimestamp = securePrefs.getLong(KEY_SESSION_TIMESTAMP, 0)
        
        if (sessionToken.isNullOrEmpty() || sessionTimestamp == 0L) {
            return false
        }
        
        val currentTime = System.currentTimeMillis()
        val timeoutMillis = getSessionTimeoutMinutes() * 60 * 1000L
        
        return (currentTime - sessionTimestamp) < timeoutMillis
    }
    
    /**
     * Update session timestamp to extend session
     */
    fun updateSessionTimestamp() {
        if (securePrefs.getString(KEY_SESSION_TOKEN, null) != null) {
            securePrefs.edit()
                .putLong(KEY_SESSION_TIMESTAMP, System.currentTimeMillis())
                .apply()
        }
    }
    
    /**
     * Clear admin session (logout)
     */
    fun clearAdminSession() {
        securePrefs.edit()
            .remove(KEY_SESSION_TOKEN)
            .remove(KEY_SESSION_TIMESTAMP)
            .apply()
    }
    
    /**
     * Generate random session token
     */
    private fun generateSessionToken(): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (1..32)
            .map { chars.random() }
            .joinToString("")
    }
    
    // ========== App Settings ==========
    
    /**
     * Get session timeout in minutes
     */
    fun getSessionTimeoutMinutes(): Int {
        return regularPrefs.getInt(KEY_SESSION_TIMEOUT_MINUTES, DEFAULT_SESSION_TIMEOUT_MINUTES)
    }
    
    /**
     * Set session timeout in minutes
     */
    fun setSessionTimeoutMinutes(minutes: Int) {
        regularPrefs.edit()
            .putInt(KEY_SESSION_TIMEOUT_MINUTES, minutes)
            .apply()
    }
    
    /**
     * Get operating hours start time
     */
    fun getOperatingHoursStart(): String {
        return regularPrefs.getString(KEY_OPERATING_HOURS_START, DEFAULT_OPERATING_HOURS_START) ?: DEFAULT_OPERATING_HOURS_START
    }
    
    /**
     * Set operating hours start time
     */
    fun setOperatingHoursStart(time: String) {
        regularPrefs.edit()
            .putString(KEY_OPERATING_HOURS_START, time)
            .apply()
    }
    
    /**
     * Get operating hours end time
     */
    fun getOperatingHoursEnd(): String {
        return regularPrefs.getString(KEY_OPERATING_HOURS_END, DEFAULT_OPERATING_HOURS_END) ?: DEFAULT_OPERATING_HOURS_END
    }
    
    /**
     * Set operating hours end time
     */
    fun setOperatingHoursEnd(time: String) {
        regularPrefs.edit()
            .putString(KEY_OPERATING_HOURS_END, time)
            .apply()
    }
    
    /**
     * Check if backup is enabled
     */
    fun isBackupEnabled(): Boolean {
        return regularPrefs.getBoolean(KEY_BACKUP_ENABLED, true)
    }
    
    /**
     * Set backup enabled state
     */
    fun setBackupEnabled(enabled: Boolean) {
        regularPrefs.edit()
            .putBoolean(KEY_BACKUP_ENABLED, enabled)
            .apply()
    }
    
    /**
     * Get last backup timestamp
     */
    fun getLastBackupTimestamp(): Long {
        return regularPrefs.getLong(KEY_LAST_BACKUP_TIMESTAMP, 0)
    }
    
    /**
     * Set last backup timestamp
     */
    fun setLastBackupTimestamp(timestamp: Long) {
        regularPrefs.edit()
            .putLong(KEY_LAST_BACKUP_TIMESTAMP, timestamp)
            .apply()
    }
}