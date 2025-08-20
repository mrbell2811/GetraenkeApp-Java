package com.club.getraenkeapp.ui.admin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.club.getraenkeapp.utils.SharedPreferencesManager
import kotlinx.coroutines.launch

/**
 * ViewModel for Admin Activity
 * 
 * PATTERN: AndroidViewModel with SharedPreferencesManager integration for authentication,
 * session management, and admin panel access control.
 */
class AdminViewModel(application: Application) : AndroidViewModel(application) {
    
    private val prefsManager = SharedPreferencesManager.getInstance(application)
    
    // Authentication state
    private val _isAuthenticated = MutableLiveData<Boolean>()
    val isAuthenticated: LiveData<Boolean> = _isAuthenticated
    
    // Navigation events
    private val _navigationEvent = MutableLiveData<NavigationEvent>()
    val navigationEvent: LiveData<NavigationEvent> = _navigationEvent
    
    // Loading state
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    // Error messages
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage
    
    init {
        // Check if user is already authenticated
        checkAuthenticationStatus()
    }
    
    /**
     * Check current authentication status
     */
    private fun checkAuthenticationStatus() {
        _isAuthenticated.value = prefsManager.isValidAdminSession()
    }
    
    /**
     * Attempt admin login
     */
    fun login(password: String) {
        if (password.isBlank()) {
            _errorMessage.value = "Passwort erforderlich"
            return
        }
        
        viewModelScope.launch {
            try {
                _isLoading.value = true
                
                if (prefsManager.verifyAdminPassword(password)) {
                    // Create session and update authentication state
                    prefsManager.createAdminSession()
                    _isAuthenticated.value = true
                    _errorMessage.value = null
                } else {
                    _errorMessage.value = "Ungültiges Passwort"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Anmeldefehler: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Logout admin user
     */
    fun logout() {
        prefsManager.clearAdminSession()
        _isAuthenticated.value = false
    }
    
    /**
     * Handle admin option selection
     */
    fun onAdminOptionSelected(option: AdminOption) {
        // Update session timestamp to extend session
        prefsManager.updateSessionTimestamp()
        
        when (option) {
            AdminOption.MEMBERS -> _navigationEvent.value = NavigationEvent.MemberManagement
            AdminOption.BEVERAGES -> _navigationEvent.value = NavigationEvent.BeverageManagement
            AdminOption.LAYOUT -> _navigationEvent.value = NavigationEvent.LayoutConfiguration
            AdminOption.EXPORT -> _navigationEvent.value = NavigationEvent.DataExport
            AdminOption.BACKUP -> _navigationEvent.value = NavigationEvent.BackupManagement
            AdminOption.SETTINGS -> _navigationEvent.value = NavigationEvent.AppSettings
        }
    }
    
    /**
     * Get admin options for display
     */
    fun getAdminOptions(): List<AdminOptionItem> {
        return listOf(
            AdminOptionItem(
                option = AdminOption.MEMBERS,
                icon = "👥",
                title = "Mitglieder",
                description = "Hinzufügen, bearbeiten und löschen"
            ),
            AdminOptionItem(
                option = AdminOption.BEVERAGES,
                icon = "🥤",
                title = "Getränke",
                description = "Preise und Angebot verwalten"
            ),
            AdminOptionItem(
                option = AdminOption.LAYOUT,
                icon = "🔧",
                title = "Layout",
                description = "Button-Anordnung konfigurieren"
            ),
            AdminOptionItem(
                option = AdminOption.EXPORT,
                icon = "📊",
                title = "Export",
                description = "Daten exportieren"
            ),
            AdminOptionItem(
                option = AdminOption.BACKUP,
                icon = "💾",
                title = "Backup",
                description = "Datensicherung verwalten"
            ),
            AdminOptionItem(
                option = AdminOption.SETTINGS,
                icon = "⚙️",
                title = "Einstellungen",
                description = "App-Konfiguration"
            )
        )
    }
    
    /**
     * Clear navigation event after handling
     */
    fun onNavigationEventHandled() {
        _navigationEvent.value = null
    }
    
    /**
     * Clear error message after showing
     */
    fun onErrorMessageShown() {
        _errorMessage.value = null
    }
    
    /**
     * Admin options enum
     */
    enum class AdminOption {
        MEMBERS, BEVERAGES, LAYOUT, EXPORT, BACKUP, SETTINGS
    }
    
    /**
     * Admin option item for display
     */
    data class AdminOptionItem(
        val option: AdminOption,
        val icon: String,
        val title: String,
        val description: String
    )
    
    /**
     * Navigation events for UI to handle
     */
    sealed class NavigationEvent {
        object MemberManagement : NavigationEvent()
        object BeverageManagement : NavigationEvent()
        object LayoutConfiguration : NavigationEvent()
        object DataExport : NavigationEvent()
        object BackupManagement : NavigationEvent()
        object AppSettings : NavigationEvent()
    }
}