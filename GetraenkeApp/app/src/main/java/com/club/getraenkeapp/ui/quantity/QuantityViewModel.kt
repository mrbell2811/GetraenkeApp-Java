package com.club.getraenkeapp.ui.quantity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.club.getraenkeapp.data.database.AppDatabase
import com.club.getraenkeapp.data.repository.AppRepository
import kotlinx.coroutines.launch

/**
 * ViewModel for Quantity Confirmation Activity
 * 
 * PATTERN: Handle quantity state and calculations, validate quantity limits (min 1, max 10),
 * calculate total price, prepare transaction data for storage.
 */
class QuantityViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: AppRepository
    
    // Transaction data
    private var memberId: Long = -1
    private var memberName: String = ""
    private var beverageId: Long = -1
    private var beverageName: String = ""
    private var unitPrice: Double = 0.0
    
    // Quantity state
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity
    
    // Total price calculation
    private val _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<Double> = _totalPrice
    
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
        
        // Initialize with default quantity of 1
        _quantity.value = 1
    }
    
    /**
     * Initialize with transaction data
     */
    fun setTransactionData(
        memberId: Long,
        memberName: String,
        beverageId: Long,
        beverageName: String,
        unitPrice: Double
    ) {
        this.memberId = memberId
        this.memberName = memberName
        this.beverageId = beverageId
        this.beverageName = beverageName
        this.unitPrice = unitPrice
        
        // Calculate initial total price
        calculateTotalPrice()
    }
    
    /**
     * Increase quantity by 1 (max 10)
     */
    fun increaseQuantity() {
        val current = _quantity.value ?: 1
        if (current < 10) { // PATTERN: Maximum quantity limit to prevent accidental high quantities
            _quantity.value = current + 1
            calculateTotalPrice()
        }
    }
    
    /**
     * Decrease quantity by 1 (min 1)
     */
    fun decreaseQuantity() {
        val current = _quantity.value ?: 1
        if (current > 1) { // PATTERN: Quantity cannot be reduced below 1
            _quantity.value = current - 1
            calculateTotalPrice()
        }
    }
    
    /**
     * Calculate total price based on current quantity
     */
    private fun calculateTotalPrice() {
        val currentQuantity = _quantity.value ?: 1
        val total = currentQuantity * unitPrice
        _totalPrice.value = total
    }
    
    /**
     * Confirm transaction and store in database
     */
    fun confirmTransaction() {
        val currentQuantity = _quantity.value ?: 1
        
        if (memberId == -1L || beverageId == -1L) {
            _errorMessage.value = "Ungültige Transaktionsdaten"
            return
        }
        
        viewModelScope.launch {
            try {
                _isLoading.value = true
                
                // PATTERN: Repository handles business logic like transaction calculations
                val transactionId = repository.createTransaction(
                    memberId = memberId,
                    beverageId = beverageId,
                    quantity = currentQuantity
                )
                
                // CRITICAL: Handle database operations asynchronously
                if (transactionId > 0) {
                    _navigationEvent.value = NavigationEvent.TransactionSuccess(
                        memberName = memberName,
                        beverageName = beverageName,
                        quantity = currentQuantity,
                        totalPrice = _totalPrice.value ?: 0.0
                    )
                } else {
                    _errorMessage.value = "Transaktion konnte nicht gespeichert werden"
                }
                
            } catch (e: Exception) {
                // CRITICAL: Handle errors gracefully
                _errorMessage.value = "Fehler beim Speichern: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Cancel transaction and go back
     */
    fun cancelTransaction() {
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
     * Get transaction display info
     */
    fun getTransactionInfo(): TransactionInfo {
        return TransactionInfo(
            memberName = memberName,
            beverageName = beverageName,
            unitPrice = unitPrice,
            quantity = _quantity.value ?: 1,
            totalPrice = _totalPrice.value ?: 0.0
        )
    }
    
    /**
     * Navigation events for the UI to handle
     */
    sealed class NavigationEvent {
        data class TransactionSuccess(
            val memberName: String,
            val beverageName: String,
            val quantity: Int,
            val totalPrice: Double
        ) : NavigationEvent()
        
        object Back : NavigationEvent()
    }
    
    /**
     * Transaction information data class
     */
    data class TransactionInfo(
        val memberName: String,
        val beverageName: String,
        val unitPrice: Double,
        val quantity: Int,
        val totalPrice: Double
    )
}