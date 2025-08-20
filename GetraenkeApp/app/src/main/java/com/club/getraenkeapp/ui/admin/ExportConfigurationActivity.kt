package com.club.getraenkeapp.ui.admin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.club.getraenkeapp.databinding.ActivityExportConfigurationBinding
import com.club.getraenkeapp.service.AutoBackupService
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 * Activity for configuring export settings including local and network paths
 * and automatic backup intervals.
 */
class ExportConfigurationActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityExportConfigurationBinding
    private val viewModel: ExportConfigurationViewModel by viewModels()
    
    // Directory picker for local path using Storage Access Framework
    private val directoryPickerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                // Grant persistent permissions
                contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                )
                // Store the content URI (not the path)
                binding.editTextLocalPath.setText(uri.toString())
            }
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityExportConfigurationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupClickListeners()
        setupObservers()
        setupInputValidation()
        
        // Load current configuration
        viewModel.loadConfiguration()
    }
    
    private fun setupClickListeners() {
        binding.buttonBack.setOnClickListener {
            finish()
        }
        
        binding.buttonSave.setOnClickListener {
            saveConfiguration()
        }
        
        binding.buttonBrowseLocal.setOnClickListener {
            browseForLocalDirectory()
        }
        
        binding.buttonTestLocal.setOnClickListener {
            testLocalExport()
        }
        
        binding.buttonTestNetwork.setOnClickListener {
            testNetworkPath()
        }
        
        binding.buttonMonthlyClosing.setOnClickListener {
            performMonthlyClosing()
        }
        
        binding.checkBoxAutoBackup.setOnCheckedChangeListener { _, isChecked ->
            binding.layoutBackupInterval.visibility = if (isChecked) View.VISIBLE else View.GONE
            updateNextBackupDisplay()
        }
    }
    
    private fun setupObservers() {
        viewModel.configuration.observe(this) { config ->
            binding.editTextLocalPath.setText(config.localPath)
            binding.editTextNetworkPath.setText(config.networkPath)
            binding.checkBoxAutoBackup.isChecked = config.autoBackupEnabled
            binding.editTextBackupInterval.setText(config.backupIntervalHours.toString())
            
            updateBackupStatus(config)
        }
        
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        
        viewModel.message.observe(this) { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                viewModel.clearMessage()
            }
        }
        
        viewModel.validationResult.observe(this) { result ->
            result?.let {
                val message = when (it) {
                    is com.club.getraenkeapp.utils.ExportUtils.ValidationResult.Success -> 
                        "✓ ${it.message}"
                    is com.club.getraenkeapp.utils.ExportUtils.ValidationResult.Error -> 
                        "✗ ${it.message}"
                }
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                viewModel.clearValidationResult()
            }
        }
    }
    
    private fun setupInputValidation() {
        binding.editTextBackupInterval.addTextChangedListener {
            val interval = it.toString().toIntOrNull()
            if (interval != null && interval in 1..168) {
                updateNextBackupDisplay()
            }
        }
    }
    
    private fun saveConfiguration() {
        val localPath = binding.editTextLocalPath.text.toString().trim()
        val networkPath = binding.editTextNetworkPath.text.toString().trim()
        val autoBackupEnabled = binding.checkBoxAutoBackup.isChecked
        val backupInterval = binding.editTextBackupInterval.text.toString().toIntOrNull() ?: 24
        
        // Validate backup interval
        if (backupInterval < 1 || backupInterval > 168) {
            Toast.makeText(this, "Backup-Intervall muss zwischen 1 und 168 Stunden liegen", Toast.LENGTH_SHORT).show()
            return
        }
        
        viewModel.saveConfiguration(
            localPath = localPath,
            networkPath = networkPath,
            autoBackupEnabled = autoBackupEnabled,
            backupIntervalHours = backupInterval
        )
        
        // Start or stop automatic backup service based on configuration
        if (autoBackupEnabled) {
            AutoBackupService.startAutoBackup(this)
            Toast.makeText(this, "Konfiguration gespeichert - Automatisches Backup aktiviert", Toast.LENGTH_SHORT).show()
        } else {
            AutoBackupService.stopAutoBackup(this)
            Toast.makeText(this, "Konfiguration gespeichert - Automatisches Backup deaktiviert", Toast.LENGTH_SHORT).show()
        }
        
        finish()
    }
    
    private fun browseForLocalDirectory() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)
        directoryPickerLauncher.launch(intent)
    }
    
    private fun testLocalExport() {
        val localPath = binding.editTextLocalPath.text.toString().trim()
        if (localPath.isBlank()) {
            Toast.makeText(this, "Bitte lokalen Pfad eingeben", Toast.LENGTH_SHORT).show()
            return
        }
        
        lifecycleScope.launch {
            viewModel.testLocalExport(localPath)
        }
    }
    
    private fun testNetworkPath() {
        val networkPath = binding.editTextNetworkPath.text.toString().trim()
        if (networkPath.isBlank()) {
            Toast.makeText(this, "Bitte Netzwerkpfad eingeben", Toast.LENGTH_SHORT).show()
            return
        }
        
        viewModel.validateNetworkPath(networkPath)
    }
    
    private fun performMonthlyClosing() {
        lifecycleScope.launch {
            viewModel.performMonthlyClosing()
        }
    }
    
    private fun updateBackupStatus(config: com.club.getraenkeapp.utils.ExportUtils.ExportConfiguration) {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMAN)
        
        // Last backup
        val lastBackupText = if (config.lastBackupTime > 0) {
            "Letztes Backup: ${dateFormat.format(Date(config.lastBackupTime))}"
        } else {
            "Letztes Backup: Noch kein Backup"
        }
        binding.textViewLastBackup.text = lastBackupText
        
        updateNextBackupDisplay()
    }
    
    private fun updateNextBackupDisplay() {
        val config = viewModel.configuration.value ?: return
        
        val nextBackupText = if (binding.checkBoxAutoBackup.isChecked) {
            val intervalHours = binding.editTextBackupInterval.text.toString().toIntOrNull() ?: 24
            if (config.lastBackupTime > 0) {
                val nextBackupTime = config.lastBackupTime + (intervalHours * 60 * 60 * 1000)
                val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMAN)
                "Nächstes Backup: ${dateFormat.format(Date(nextBackupTime))}"
            } else {
                "Nächstes Backup: Bei der nächsten Transaktion"
            }
        } else {
            "Nächstes Backup: Automatische Backups deaktiviert"
        }
        
        binding.textViewNextBackup.text = nextBackupText
    }
}