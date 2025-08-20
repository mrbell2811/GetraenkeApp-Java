package com.club.getraenkeapp.ui.admin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.club.getraenkeapp.data.database.AppDatabase
import com.club.getraenkeapp.data.database.entities.Beverage
import com.club.getraenkeapp.data.repository.AppRepository
import kotlinx.coroutines.launch

/**
 * ViewModel for Beverage Management
 * 
 * PATTERN: MVVM with Repository pattern, price validation,
 * search/filter functionality, and soft deletion handling
 * 
 * CRITICAL: Soft deletion for beverages with transaction history
 */
class BeverageManagementViewModel(application: Application) : AndroidViewModel(application) {
    
    private val database = AppDatabase.getDatabase(application)
    private val repository = AppRepository(
        memberDao = database.memberDao(),
        beverageDao = database.beverageDao(), 
        transactionDao = database.transactionDao(),
            database.archivedTransactionDao()
    )
    
    private val allBeverages = repository.getAllBeverages()
    private val _beverages = MutableLiveData<List<Beverage>>()
    val beverages: LiveData<List<Beverage>> = _beverages
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage
    
    private val _successMessage = MutableLiveData<String?>()
    val successMessage: LiveData<String?> = _successMessage
    
    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String> = _searchQuery
    
    init {
        // Observe all beverages and apply current search filter
        allBeverages.observeForever { beveragesList ->
            filterBeverages(beveragesList, _searchQuery.value ?: "")
        }
        
        _searchQuery.value = ""
    }
    
    /**
     * Search beverages by name or category
     */
    fun searchBeverages(query: String) {
        _searchQuery.value = query
        val currentBeverages = allBeverages.value ?: emptyList()
        filterBeverages(currentBeverages, query)
    }
    
    /**
     * Filter beverages based on search query
     */
    private fun filterBeverages(beveragesList: List<Beverage>, query: String) {
        val filteredList = if (query.isEmpty()) {
            beveragesList
        } else {
            beveragesList.filter { beverage ->
                beverage.name.contains(query, ignoreCase = true) ||
                beverage.category?.contains(query, ignoreCase = true) == true
            }
        }
        _beverages.value = filteredList
    }
    
    /**
     * Add new beverage with validation
     */
    fun addBeverage(name: String, price: Double, category: String?) {
        if (name.isBlank()) {
            _errorMessage.value = "Name darf nicht leer sein"
            return
        }
        
        if (name.length > 100) {
            _errorMessage.value = "Name zu lang (maximal 100 Zeichen)"
            return
        }
        
        if (price <= 0) {
            _errorMessage.value = "Preis muss größer als 0 sein"
            return
        }
        
        if (price > 999.99) {
            _errorMessage.value = "Preis zu hoch (maximal 999.99 €)"
            return
        }
        
        viewModelScope.launch {
            try {
                _isLoading.value = true
                
                // Check if beverage with same name already exists
                val existingBeverage = repository.getBeverageByName(name)
                if (existingBeverage != null) {
                    _errorMessage.value = "Getränk '$name' existiert bereits"
                    return@launch
                }
                
                // Create new beverage
                val newBeverage = Beverage(
                    name = name.trim(),
                    price = price,
                    category = category?.trim()?.takeIf { it.isNotEmpty() },
                    active = true
                )
                
                repository.insertBeverage(newBeverage)
                _successMessage.value = "Getränk '$name' wurde hinzugefügt"
                
            } catch (e: Exception) {
                _errorMessage.value = "Fehler beim Hinzufügen: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Update existing beverage with validation
     */
    fun updateBeverage(beverage: Beverage) {
        if (beverage.name.isBlank()) {
            _errorMessage.value = "Name darf nicht leer sein"
            return
        }
        
        if (beverage.name.length > 100) {
            _errorMessage.value = "Name zu lang (maximal 100 Zeichen)"
            return
        }
        
        if (beverage.price <= 0) {
            _errorMessage.value = "Preis muss größer als 0 sein"
            return
        }
        
        if (beverage.price > 999.99) {
            _errorMessage.value = "Preis zu hoch (maximal 999.99 €)"
            return
        }
        
        viewModelScope.launch {
            try {
                _isLoading.value = true
                
                // Check if another beverage with same name already exists
                val existingBeverage = repository.getBeverageByName(beverage.name)
                if (existingBeverage != null && existingBeverage.id != beverage.id) {
                    _errorMessage.value = "Ein anderes Getränk mit dem Namen '${beverage.name}' existiert bereits"
                    return@launch
                }
                
                val updatedBeverage = beverage.copy(
                    name = beverage.name.trim(),
                    category = beverage.category?.trim()?.takeIf { it.isNotEmpty() }
                )
                
                repository.updateBeverage(updatedBeverage)
                _successMessage.value = "Getränk '${beverage.name}' wurde aktualisiert"
                
            } catch (e: Exception) {
                _errorMessage.value = "Fehler beim Aktualisieren: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Delete beverage with soft deletion for transaction history
     */
    fun deleteBeverage(beverage: Beverage) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                
                val deleted = repository.deleteBeverage(beverage)
                if (deleted) {
                    // Check if it was actually deleted or just deactivated
                    val transactionCount = repository.getTransactionCountForBeverage(beverage.id)
                    if (transactionCount > 0) {
                        _successMessage.value = "Getränk '${beverage.name}' wurde deaktiviert (hatte $transactionCount Transaktionen)"
                    } else {
                        _successMessage.value = "Getränk '${beverage.name}' wurde gelöscht"
                    }
                } else {
                    _errorMessage.value = "Fehler beim Löschen von '${beverage.name}'"
                }
                
            } catch (e: Exception) {
                _errorMessage.value = "Fehler beim Löschen: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Toggle beverage active status
     */
    fun toggleBeverageActive(beverage: Beverage) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                
                if (beverage.active) {
                    repository.deactivateBeverage(beverage.id)
                    _successMessage.value = "Getränk '${beverage.name}' wurde deaktiviert"
                } else {
                    repository.activateBeverage(beverage.id)
                    _successMessage.value = "Getränk '${beverage.name}' wurde aktiviert"
                }
                
            } catch (e: Exception) {
                _errorMessage.value = "Fehler beim Ändern des Status: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Get transaction count for beverage (for deletion decisions)
     */
    suspend fun getTransactionCountForBeverage(beverageId: Long): Int {
        return try {
            repository.getTransactionCountForBeverage(beverageId)
        } catch (e: Exception) {
            0
        }
    }
    
    /**
     * Clear error message after showing to user
     */
    fun onErrorMessageShown() {
        _errorMessage.value = null
    }
    
    /**
     * Clear success message after showing to user
     */
    fun onSuccessMessageShown() {
        _successMessage.value = null
    }
    
    override fun onCleared() {
        super.onCleared()
        allBeverages.removeObserver { }
    }
}