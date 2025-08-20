package com.club.getraenkeapp.ui.admin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.club.getraenkeapp.databinding.ActivityAdminBinding

/**
 * Admin Activity - Authentication and Management Panel
 * 
 * PATTERN: Password input with SharedPreferences storage, hashed password storage,
 * session management with timeout, navigation to admin panels after successful auth.
 * 
 * CRITICAL: Visual distinction between admin and user interfaces
 */
class AdminActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityAdminBinding
    private val viewModel: AdminViewModel by viewModels()
    private lateinit var adminOptionsAdapter: AdminOptionsAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupAdminOptionsRecyclerView()
        setupObservers()
        setupClickListeners()
    }
    
    /**
     * Setup RecyclerView for admin options
     */
    private fun setupAdminOptionsRecyclerView() {
        val spanCount = 2 // 2 columns for admin options
        val layoutManager = GridLayoutManager(this, spanCount)
        
        binding.recyclerViewAdminOptions.layoutManager = layoutManager
        
        adminOptionsAdapter = AdminOptionsAdapter { option ->
            viewModel.onAdminOptionSelected(option)
        }
        
        binding.recyclerViewAdminOptions.adapter = adminOptionsAdapter
        
        // Load admin options
        adminOptionsAdapter.submitList(viewModel.getAdminOptions())
    }
    
    /**
     * Setup observers for ViewModel LiveData
     */
    private fun setupObservers() {
        // Observe authentication state
        viewModel.isAuthenticated.observe(this) { isAuthenticated ->
            if (isAuthenticated) {
                showAdminPanel()
            } else {
                showLoginForm()
            }
        }
        
        // Observe navigation events
        viewModel.navigationEvent.observe(this) { event ->
            event?.let {
                handleNavigationEvent(it)
                viewModel.onNavigationEventHandled()
            }
        }
        
        // Observe loading state
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
            
            // Disable login button during loading
            binding.buttonLogin.isEnabled = !isLoading
        }
        
        // Observe error messages
        viewModel.errorMessage.observe(this) { message ->
            if (message != null) {
                binding.textViewLoginError.text = message
                binding.textViewLoginError.visibility = View.VISIBLE
                viewModel.onErrorMessageShown()
            } else {
                binding.textViewLoginError.visibility = View.GONE
            }
        }
    }
    
    /**
     * Setup click listeners for UI elements
     */
    private fun setupClickListeners() {
        // Login button
        binding.buttonLogin.setOnClickListener {
            val password = binding.editTextPassword.text.toString()
            viewModel.login(password)
        }
        
        // Logout button
        binding.buttonLogout.setOnClickListener {
            viewModel.logout()
        }
        
        // Enter key on password field
        binding.editTextPassword.setOnEditorActionListener { _, _, _ ->
            val password = binding.editTextPassword.text.toString()
            viewModel.login(password)
            true
        }
    }
    
    /**
     * Show login form and hide admin panel
     */
    private fun showLoginForm() {
        binding.layoutLogin.visibility = View.VISIBLE
        binding.scrollViewAdminPanel.visibility = View.GONE
        
        // Clear password field
        binding.editTextPassword.text?.clear()
        binding.textViewLoginError.visibility = View.GONE
        
        // Focus on password field
        binding.editTextPassword.requestFocus()
    }
    
    /**
     * Show admin panel and hide login form
     */
    private fun showAdminPanel() {
        binding.layoutLogin.visibility = View.GONE
        binding.scrollViewAdminPanel.visibility = View.VISIBLE
    }
    
    /**
     * Handle navigation events from ViewModel
     */
    private fun handleNavigationEvent(event: AdminViewModel.NavigationEvent) {
        when (event) {
            is AdminViewModel.NavigationEvent.MemberManagement -> {
                val intent = Intent(this, MemberManagementActivity::class.java)
                startActivity(intent)
            }
            
            is AdminViewModel.NavigationEvent.BeverageManagement -> {
                val intent = Intent(this, BeverageManagementActivity::class.java)
                startActivity(intent)
            }
            
            is AdminViewModel.NavigationEvent.LayoutConfiguration -> {
                Toast.makeText(this, "Layout Konfiguration - Coming soon", Toast.LENGTH_SHORT).show()
                // TODO: Start LayoutConfigurationActivity
            }
            
            is AdminViewModel.NavigationEvent.DataExport -> {
                val intent = Intent(this, ExportConfigurationActivity::class.java)
                startActivity(intent)
            }
            
            is AdminViewModel.NavigationEvent.BackupManagement -> {
                Toast.makeText(this, "Backup Verwaltung - Coming soon", Toast.LENGTH_SHORT).show()
                // TODO: Start BackupManagementActivity
            }
            
            is AdminViewModel.NavigationEvent.AppSettings -> {
                Toast.makeText(this, "App Einstellungen - Coming soon", Toast.LENGTH_SHORT).show()
                // TODO: Start AppSettingsActivity
            }
        }
    }
    
    override fun onBackPressed() {
        // If authenticated, logout first, then exit
        if (viewModel.isAuthenticated.value == true) {
            viewModel.logout()
        } else {
            super.onBackPressed()
        }
    }
}