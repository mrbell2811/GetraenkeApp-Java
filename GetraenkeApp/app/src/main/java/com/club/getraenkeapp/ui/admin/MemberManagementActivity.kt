package com.club.getraenkeapp.ui.admin

import android.app.AlertDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.club.getraenkeapp.R
import com.club.getraenkeapp.databinding.ActivityMemberManagementBinding

/**
 * Member Management Activity for Admin Panel
 * 
 * PATTERN: Admin interface for member CRUD operations with validation,
 * search/filter capabilities, confirmation dialogs, and automatic updates.
 * 
 * CRITICAL: Prevent deletion of members with transaction history
 */
class MemberManagementActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMemberManagementBinding
    private val viewModel: MemberManagementViewModel by viewModels()
    private lateinit var memberAdapter: MemberManagementAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMemberManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        setupRecyclerView()
        setupObservers()
        setupClickListeners()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Mitglieder Verwaltung"
        }
    }
    
    private fun setupRecyclerView() {
        memberAdapter = MemberManagementAdapter(
            onEditClick = { member ->
                showEditMemberDialog(member)
            },
            onDeleteClick = { member ->
                showDeleteConfirmationDialog(member)
            }
        )
        
        binding.recyclerViewMembers.apply {
            layoutManager = LinearLayoutManager(this@MemberManagementActivity)
            adapter = memberAdapter
            setHasFixedSize(true)
        }
    }
    
    private fun setupObservers() {
        viewModel.members.observe(this) { members ->
            memberAdapter.submitList(members)
            
            // Show/hide empty state
            if (members.isEmpty()) {
                binding.textViewEmpty.visibility = android.view.View.VISIBLE
                binding.recyclerViewMembers.visibility = android.view.View.GONE
            } else {
                binding.textViewEmpty.visibility = android.view.View.GONE
                binding.recyclerViewMembers.visibility = android.view.View.VISIBLE
            }
        }
        
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) {
                android.view.View.VISIBLE
            } else {
                android.view.View.GONE
            }
        }
        
        viewModel.errorMessage.observe(this) { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                viewModel.onErrorMessageShown()
            }
        }
        
        viewModel.successMessage.observe(this) { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                viewModel.onSuccessMessageShown()
            }
        }
        
        viewModel.searchQuery.observe(this) { query ->
            binding.editTextSearch.setQuery(query, false)
        }
    }
    
    private fun setupClickListeners() {
        binding.fabAddMember.setOnClickListener {
            showAddMemberDialog()
        }
        
        binding.editTextSearch.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchMembers(newText ?: "")
                return true
            }
        })
    }
    
    private fun showAddMemberDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_member, null)
        val editTextName = dialogView.findViewById<EditText>(R.id.editTextMemberName)
        
        AlertDialog.Builder(this)
            .setTitle("Mitglied hinzufügen")
            .setView(dialogView)
            .setPositiveButton("Hinzufügen") { _, _ ->
                val name = editTextName.text.toString().trim()
                if (name.isNotEmpty()) {
                    viewModel.addMember(name)
                } else {
                    Toast.makeText(this, "Name darf nicht leer sein", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Abbrechen", null)
            .show()
            
        // Focus on input field
        editTextName.requestFocus()
    }
    
    private fun showEditMemberDialog(member: com.club.getraenkeapp.data.database.entities.Member) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_member, null)
        val editTextName = dialogView.findViewById<EditText>(R.id.editTextMemberName)
        
        editTextName.setText(member.name)
        editTextName.selectAll()
        
        AlertDialog.Builder(this)
            .setTitle("Mitglied bearbeiten")
            .setView(dialogView)
            .setPositiveButton("Speichern") { _, _ ->
                val newName = editTextName.text.toString().trim()
                if (newName.isNotEmpty()) {
                    viewModel.updateMember(member.copy(name = newName))
                } else {
                    Toast.makeText(this, "Name darf nicht leer sein", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Abbrechen", null)
            .show()
            
        editTextName.requestFocus()
    }
    
    private fun showDeleteConfirmationDialog(member: com.club.getraenkeapp.data.database.entities.Member) {
        AlertDialog.Builder(this)
            .setTitle("Mitglied löschen")
            .setMessage("Möchten Sie '${member.name}' wirklich löschen?\n\nHinweis: Mitglieder mit Transaktionen können nicht gelöscht werden.")
            .setPositiveButton("Löschen") { _, _ ->
                viewModel.deleteMember(member)
            }
            .setNegativeButton("Abbrechen", null)
            .setIcon(R.drawable.ic_warning)
            .show()
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}