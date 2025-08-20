package com.club.getraenkeapp.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.club.getraenkeapp.data.database.AppDatabase
import com.club.getraenkeapp.data.database.entities.Member
import com.club.getraenkeapp.data.repository.AppRepository
import kotlinx.coroutines.launch

/**
 * ViewModel for Main Activity
 * 
 * PATTERN: AndroidViewModel with repository injection, LiveData for member list
 * and grid configuration, methods for member selection and grid layout updates,
 * handling configuration changes gracefully.
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: AppRepository
    
    // LiveData for member list
    val members: LiveData<List<Member>>
    
    // Navigation state
    private val _navigationEvent = MutableLiveData<NavigationEvent>()
    val navigationEvent: LiveData<NavigationEvent> = _navigationEvent
    
    // Loading state
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    // Error state
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage
    
    init {
        val database = AppDatabase.getDatabase(application)
        repository = AppRepository(
            database.memberDao(),
            database.beverageDao(),
            database.transactionDao(),
            database.archivedTransactionDao()
        )
        
        // Initialize members LiveData
        members = repository.getAllMembers()
    }
    
    /**
     * Handle member button click
     */
    fun onMemberSelected(member: Member) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                
                // Validate member still exists and is active
                val currentMember = repository.getMemberById(member.id)
                if (currentMember != null) {
                    _navigationEvent.value = NavigationEvent.BeverageSelection(member.id, member.name)
                } else {
                    _errorMessage.value = "Mitglied nicht gefunden"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Fehler beim Laden: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Handle admin button click
     */
    fun onAdminAccess() {
        _navigationEvent.value = NavigationEvent.AdminAccess
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
     * Calculate optimal grid span count based on screen width and orientation
     * GOTCHA: Portrait vs Landscape optimization - different max columns for each
     */
    fun calculateOptimalSpanCount(screenWidthPx: Int, buttonMinWidthPx: Int): Int {
        val spanCount = (screenWidthPx / buttonMinWidthPx).coerceAtLeast(2)
        
        // Different max columns based on screen aspect ratio
        return if (screenWidthPx < 800) { // Likely portrait or small screen
            spanCount.coerceAtMost(2) // Maximum 2 columns for portrait readability
        } else { // Likely landscape
            spanCount.coerceAtMost(4) // Maximum 4 columns for landscape mode
        }
    }
    
    /**
     * Navigation events for the UI to handle
     */
    sealed class NavigationEvent {
        data class BeverageSelection(val memberId: Long, val memberName: String) : NavigationEvent()
        object AdminAccess : NavigationEvent()
    }
}