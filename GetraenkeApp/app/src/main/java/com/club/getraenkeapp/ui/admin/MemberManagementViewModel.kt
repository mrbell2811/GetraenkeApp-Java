package com.club.getraenkeapp.ui.admin

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
 * ViewModel for Member Management
 * 
 * PATTERN: MVVM with Repository pattern, business logic validation,
 * search/filter functionality, and error handling
 * 
 * CRITICAL: Prevent deletion of members with transaction history
 */
class MemberManagementViewModel(application: Application) : AndroidViewModel(application) {
    
    private val database = AppDatabase.getDatabase(application)
    private val repository = AppRepository(
        memberDao = database.memberDao(),
        beverageDao = database.beverageDao(), 
        transactionDao = database.transactionDao(),
            database.archivedTransactionDao()
    )
    
    private val allMembers = repository.getAllMembers()
    private val _members = MutableLiveData<List<Member>>()
    val members: LiveData<List<Member>> = _members
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage
    
    private val _successMessage = MutableLiveData<String?>()
    val successMessage: LiveData<String?> = _successMessage
    
    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String> = _searchQuery
    
    init {
        // Observe all members and apply current search filter
        allMembers.observeForever { membersList ->
            filterMembers(membersList, _searchQuery.value ?: "")
        }
        
        _searchQuery.value = ""
    }
    
    /**
     * Search members by name
     */
    fun searchMembers(query: String) {
        _searchQuery.value = query
        val currentMembers = allMembers.value ?: emptyList()
        filterMembers(currentMembers, query)
    }
    
    /**
     * Filter members based on search query
     */
    private fun filterMembers(membersList: List<Member>, query: String) {
        val filteredList = if (query.isEmpty()) {
            membersList
        } else {
            membersList.filter { member ->
                member.name.contains(query, ignoreCase = true)
            }
        }
        _members.value = filteredList
    }
    
    /**
     * Add new member with validation
     */
    fun addMember(name: String) {
        if (name.isBlank()) {
            _errorMessage.value = "Name darf nicht leer sein"
            return
        }
        
        if (name.length > 50) {
            _errorMessage.value = "Name zu lang (maximal 50 Zeichen)"
            return
        }
        
        viewModelScope.launch {
            try {
                _isLoading.value = true
                
                // Check if member with same name already exists
                val existingMember = repository.getMemberByName(name)
                if (existingMember != null) {
                    _errorMessage.value = "Mitglied '$name' existiert bereits"
                    return@launch
                }
                
                // Create new member
                val newMember = Member(
                    name = name.trim(),
                    gridPosition = null // Will be assigned automatically
                )
                
                repository.insertMember(newMember)
                _successMessage.value = "Mitglied '$name' wurde hinzugefügt"
                
            } catch (e: Exception) {
                _errorMessage.value = "Fehler beim Hinzufügen: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Update existing member with validation
     */
    fun updateMember(member: Member) {
        if (member.name.isBlank()) {
            _errorMessage.value = "Name darf nicht leer sein"
            return
        }
        
        if (member.name.length > 50) {
            _errorMessage.value = "Name zu lang (maximal 50 Zeichen)"
            return
        }
        
        viewModelScope.launch {
            try {
                _isLoading.value = true
                
                // Check if another member with same name already exists
                val existingMember = repository.getMemberByName(member.name)
                if (existingMember != null && existingMember.id != member.id) {
                    _errorMessage.value = "Ein anderes Mitglied mit dem Namen '${member.name}' existiert bereits"
                    return@launch
                }
                
                repository.updateMember(member.copy(name = member.name.trim()))
                _successMessage.value = "Mitglied '${member.name}' wurde aktualisiert"
                
            } catch (e: Exception) {
                _errorMessage.value = "Fehler beim Aktualisieren: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Delete member with transaction history validation
     */
    fun deleteMember(member: Member) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                
                // Check if member has any transactions
                val transactionCount = repository.getTransactionCountForMember(member.id)
                if (transactionCount > 0) {
                    _errorMessage.value = "Mitglied '${member.name}' kann nicht gelöscht werden, da Transaktionen vorhanden sind ($transactionCount Buchungen)"
                    return@launch
                }
                
                repository.deleteMember(member)
                _successMessage.value = "Mitglied '${member.name}' wurde gelöscht"
                
            } catch (e: Exception) {
                _errorMessage.value = "Fehler beim Löschen: ${e.message}"
            } finally {
                _isLoading.value = false
            }
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
        allMembers.removeObserver { }
    }
}