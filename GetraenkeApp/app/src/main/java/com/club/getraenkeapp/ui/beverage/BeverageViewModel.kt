package com.club.getraenkeapp.ui.beverage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.club.getraenkeapp.data.database.AppDatabase
import com.club.getraenkeapp.data.database.entities.Beverage
import com.club.getraenkeapp.data.database.entities.Member
import com.club.getraenkeapp.data.repository.AppRepository
import kotlinx.coroutines.launch

/**
 * ViewModel for Beverage Selection Activity
 * 
 * PATTERN: Load active beverages from repository, handle beverage selection state,
 * calculate pricing preview, prepare data for quantity screen transition.
 */
class BeverageViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: AppRepository
    
    // LiveData for beverage list (only active beverages)
    val beverages: LiveData<List<Beverage>>
    
    // Navigation state
    private val _navigationEvent = MutableLiveData<NavigationEvent>()
    val navigationEvent: LiveData<NavigationEvent> = _navigationEvent
    
    // Loading state
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    // Error state
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage
    
    // Selected member info
    private val _memberInfo = MutableLiveData<Member>()
    val memberInfo: LiveData<Member> = _memberInfo

    // LiveData for member statistics
    private val _memberStatistics = MutableLiveData<AppRepository.MemberStatistics>()
    val memberStatistics: LiveData<AppRepository.MemberStatistics> = _memberStatistics
    
    init {
        val database = AppDatabase.getDatabase(application)
        repository = AppRepository(
            database.memberDao(),
            database.beverageDao(),
            database.transactionDao(),
            database.archivedTransactionDao()
        )
        
        // Initialize beverages LiveData (only active beverages)
        beverages = repository.getActiveBeverages()
    }
    
    /**
     * Initialize with member information
     */
    fun setMemberInfo(memberId: Long, memberName: String) {
        viewModelScope.launch {
            try {
                val member = repository.getMemberById(memberId)
                if (member != null) {
                    _memberInfo.value = member
                    loadMemberStatistics(member.name)
                } else {
                    _errorMessage.value = "Mitglied nicht gefunden"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Fehler beim Laden des Mitglieds: ${e.message}"
            }
        }
    }

    private fun loadMemberStatistics(memberName: String) {
        viewModelScope.launch {
            try {
                val stats = repository.getMemberStatisticsSinceLastArchive(memberName)
                _memberStatistics.postValue(stats)
            } catch (e: Exception) {
                _errorMessage.postValue("Fehler beim Laden der Statistik: ${e.message}")
            }
        }
    }
    
    /**
     * Handle beverage selection
     */
    fun onBeverageSelected(beverage: Beverage) {
        val member = _memberInfo.value
        if (member != null) {
            viewModelScope.launch {
                try {
                    _isLoading.value = true
                    
                    // Validate beverage is still active and available
                    val currentBeverage = repository.getBeverageById(beverage.id)
                    if (currentBeverage != null && currentBeverage.active) {
                        _navigationEvent.value = NavigationEvent.QuantitySelection(
                            memberId = member.id,
                            memberName = member.name,
                            beverageId = beverage.id,
                            beverageName = beverage.name,
                            unitPrice = beverage.price
                        )
                    } else {
                        _errorMessage.value = "Getränk nicht mehr verfügbar"
                    }
                } catch (e: Exception) {
                    _errorMessage.value = "Fehler beim Auswählen: ${e.message}"
                } finally {
                    _isLoading.value = false
                }
            }
        } else {
            _errorMessage.value = "Mitglied Information fehlt"
        }
    }
    
    /**
     * Handle back navigation
     */
    fun onBackPressed() {
        _navigationEvent.value = NavigationEvent.Back
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
     * Navigation events for the UI to handle
     */
    sealed class NavigationEvent {
        data class QuantitySelection(
            val memberId: Long,
            val memberName: String,
            val beverageId: Long,
            val beverageName: String,
            val unitPrice: Double
        ) : NavigationEvent()
        
        object Back : NavigationEvent()
    }
}