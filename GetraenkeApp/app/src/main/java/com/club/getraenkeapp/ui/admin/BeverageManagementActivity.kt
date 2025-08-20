package com.club.getraenkeapp.ui.admin

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.club.getraenkeapp.R
import com.club.getraenkeapp.databinding.ActivityBeverageManagementBinding

/**
 * Beverage Management Activity for Admin Panel
 * 
 * PATTERN: Admin interface for beverage CRUD operations with price validation,
 * optional categorization, and automatic beverage selection screen updates.
 * 
 * CRITICAL: Soft deletion for beverages with transaction history
 */
class BeverageManagementActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityBeverageManagementBinding
    private val viewModel: BeverageManagementViewModel by viewModels()
    private lateinit var beverageAdapter: BeverageManagementAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityBeverageManagementBinding.inflate(layoutInflater)
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
            title = "Getränke Verwaltung"
        }
    }
    
    private fun setupRecyclerView() {
        beverageAdapter = BeverageManagementAdapter(
            onEditClick = { beverage ->
                showEditBeverageDialog(beverage)
            },
            onDeleteClick = { beverage ->
                showDeleteConfirmationDialog(beverage)
            },
            onToggleActiveClick = { beverage ->
                viewModel.toggleBeverageActive(beverage)
            }
        )
        
        binding.recyclerViewBeverages.apply {
            layoutManager = LinearLayoutManager(this@BeverageManagementActivity)
            adapter = beverageAdapter
            setHasFixedSize(true)
        }
    }
    
    private fun setupObservers() {
        viewModel.beverages.observe(this) { beverages ->
            beverageAdapter.submitList(beverages)
            
            // Show/hide empty state
            if (beverages.isEmpty()) {
                binding.textViewEmpty.visibility = android.view.View.VISIBLE
                binding.recyclerViewBeverages.visibility = android.view.View.GONE
            } else {
                binding.textViewEmpty.visibility = android.view.View.GONE
                binding.recyclerViewBeverages.visibility = android.view.View.VISIBLE
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
        binding.fabAddBeverage.setOnClickListener {
            showAddBeverageDialog()
        }
        
        binding.editTextSearch.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchBeverages(newText ?: "")
                return true
            }
        })
    }
    
    private fun showAddBeverageDialog() {
        val dialogView = createBeverageDialogView()
        val editTextName = dialogView.findViewById<EditText>(R.id.editTextBeverageName)
        val editTextPrice = dialogView.findViewById<EditText>(R.id.editTextBeveragePrice)
        val editTextCategory = dialogView.findViewById<EditText>(R.id.editTextBeverageCategory)
        
        AlertDialog.Builder(this)
            .setTitle("Getränk hinzufügen")
            .setView(dialogView)
            .setPositiveButton("Hinzufügen") { _, _ ->
                val name = editTextName.text.toString().trim()
                val priceText = editTextPrice.text.toString().trim()
                val category = editTextCategory.text.toString().trim()
                
                if (name.isEmpty()) {
                    Toast.makeText(this, "Name darf nicht leer sein", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                
                val price = priceText.toDoubleOrNull()
                if (price == null || price <= 0) {
                    Toast.makeText(this, "Preis muss eine positive Zahl sein", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                
                viewModel.addBeverage(name, price, category.ifEmpty { null })
            }
            .setNegativeButton("Abbrechen", null)
            .show()
            
        editTextName.requestFocus()
    }
    
    private fun showEditBeverageDialog(beverage: com.club.getraenkeapp.data.database.entities.Beverage) {
        val dialogView = createBeverageDialogView()
        val editTextName = dialogView.findViewById<EditText>(R.id.editTextBeverageName)
        val editTextPrice = dialogView.findViewById<EditText>(R.id.editTextBeveragePrice)
        val editTextCategory = dialogView.findViewById<EditText>(R.id.editTextBeverageCategory)
        
        editTextName.setText(beverage.name)
        editTextPrice.setText(String.format("%.2f", beverage.price))
        editTextCategory.setText(beverage.category ?: "")
        
        editTextName.selectAll()
        
        AlertDialog.Builder(this)
            .setTitle("Getränk bearbeiten")
            .setView(dialogView)
            .setPositiveButton("Speichern") { _, _ ->
                val name = editTextName.text.toString().trim()
                val priceText = editTextPrice.text.toString().trim()
                val category = editTextCategory.text.toString().trim()
                
                if (name.isEmpty()) {
                    Toast.makeText(this, "Name darf nicht leer sein", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                
                val price = priceText.toDoubleOrNull()
                if (price == null || price <= 0) {
                    Toast.makeText(this, "Preis muss eine positive Zahl sein", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                
                val updatedBeverage = beverage.copy(
                    name = name,
                    price = price,
                    category = category.ifEmpty { null }
                )
                viewModel.updateBeverage(updatedBeverage)
            }
            .setNegativeButton("Abbrechen", null)
            .show()
            
        editTextName.requestFocus()
    }
    
    private fun createBeverageDialogView(): LinearLayout {
        val dialogView = LinearLayout(this)
        dialogView.orientation = LinearLayout.VERTICAL
        dialogView.setPadding(60, 40, 60, 40)
        
        // Name input
        val nameLayout = com.google.android.material.textfield.TextInputLayout(this)
        nameLayout.hint = "Getränk Name"
        val editTextName = com.google.android.material.textfield.TextInputEditText(this)
        editTextName.id = R.id.editTextBeverageName
        editTextName.inputType = InputType.TYPE_TEXT_FLAG_CAP_WORDS
        nameLayout.addView(editTextName)
        
        // Price input
        val priceLayout = com.google.android.material.textfield.TextInputLayout(this)
        priceLayout.hint = "Preis (€)"
        val editTextPrice = com.google.android.material.textfield.TextInputEditText(this)
        editTextPrice.id = R.id.editTextBeveragePrice
        editTextPrice.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        priceLayout.addView(editTextPrice)
        
        // Category input
        val categoryLayout = com.google.android.material.textfield.TextInputLayout(this)
        categoryLayout.hint = "Kategorie (optional)"
        val editTextCategory = com.google.android.material.textfield.TextInputEditText(this)
        editTextCategory.id = R.id.editTextBeverageCategory
        editTextCategory.inputType = InputType.TYPE_TEXT_FLAG_CAP_WORDS
        categoryLayout.addView(editTextCategory)
        
        dialogView.addView(nameLayout)
        dialogView.addView(priceLayout)
        dialogView.addView(categoryLayout)
        
        return dialogView
    }
    
    private fun showDeleteConfirmationDialog(beverage: com.club.getraenkeapp.data.database.entities.Beverage) {
        AlertDialog.Builder(this)
            .setTitle("Getränk löschen")
            .setMessage("Möchten Sie '${beverage.name}' wirklich löschen?\n\nHinweis: Getränke mit Transaktionen werden nur deaktiviert, nicht gelöscht.")
            .setPositiveButton("Löschen") { _, _ ->
                viewModel.deleteBeverage(beverage)
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