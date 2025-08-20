package com.club.getraenkeapp.ui.statistics

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.club.getraenkeapp.data.database.AppDatabase
import com.club.getraenkeapp.data.repository.AppRepository
import kotlinx.coroutines.launch

/**
 * ViewModel for User Statistics Activity
 * 
 * Manages loading and processing of user consumption statistics
 * since the last monthly closing.
 */
class UserStatisticsViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: AppRepository
    
    // Statistics data
    private val _statistics = MutableLiveData<UserStatisticsData?>()
    val statistics: LiveData<UserStatisticsData?> = _statistics
    
    // Loading state
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    // Error message
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
    }
    
    /**
     * Load statistics for a specific member
     */
    fun loadStatistics(memberName: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _errorMessage.value = ""
                
                // Get member statistics since last archive
                val memberStats = repository.getMemberStatisticsSinceLastArchive(memberName)
                
                // Convert to UI data model
                val uiData = UserStatisticsData(
                    memberName = memberStats.memberName,
                    totalQuantity = memberStats.totalQuantity,
                    totalSpent = memberStats.totalSpent,
                    transactionCount = memberStats.transactionCount,
                    beverageBreakdown = memberStats.beverageBreakdown.map { consumption ->
                        BeverageStatisticsItem(
                            beverageName = consumption.beverageName,
                            quantity = consumption.quantity,
                            totalPrice = consumption.totalPrice,
                            averagePrice = if (consumption.quantity > 0) consumption.totalPrice / consumption.quantity else 0.0
                        )
                    },
                    periodStart = memberStats.periodStart
                )
                
                _statistics.value = uiData
                
            } catch (e: Exception) {
                _errorMessage.value = "Fehler beim Laden der Statistiken: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Data model for user statistics display
     */
    data class UserStatisticsData(
        val memberName: String,
        val totalQuantity: Int,
        val totalSpent: Double,
        val transactionCount: Int,
        val beverageBreakdown: List<BeverageStatisticsItem>,
        val periodStart: Long
    )
    
    /**
     * Data model for individual beverage statistics
     */
    data class BeverageStatisticsItem(
        val beverageName: String,
        val quantity: Int,
        val totalPrice: Double,
        val averagePrice: Double
    )
}