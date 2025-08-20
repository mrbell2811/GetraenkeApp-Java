package com.club.getraenkeapp.ui.quantity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.club.getraenkeapp.databinding.ActivityQuantityBinding
import com.club.getraenkeapp.ui.confirmation.ConfirmationDialog
import com.club.getraenkeapp.ui.main.MainActivity
import java.text.NumberFormat
import java.util.*

/**
 * Quantity Confirmation Activity
 * 
 * PATTERN: Receive member and beverage data via Intent, default quantity=1 with +/- controls,
 * real-time cost calculation display, cancel/confirm buttons with quantity validation.
 * 
 * CRITICAL: Center-aligned quantity controls, large +/- buttons, cost summary display
 */
class QuantityActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityQuantityBinding
    private val viewModel: QuantityViewModel by viewModels()
    private val priceFormatter = NumberFormat.getCurrencyInstance(Locale.GERMANY)
    
    companion object {
        const val EXTRA_MEMBER_ID = "extra_member_id"
        const val EXTRA_MEMBER_NAME = "extra_member_name"
        const val EXTRA_BEVERAGE_ID = "extra_beverage_id"
        const val EXTRA_BEVERAGE_NAME = "extra_beverage_name"
        const val EXTRA_UNIT_PRICE = "extra_unit_price"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityQuantityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Get transaction data from intent
        val memberId = intent.getLongExtra(EXTRA_MEMBER_ID, -1L)
        val memberName = intent.getStringExtra(EXTRA_MEMBER_NAME)
        val beverageId = intent.getLongExtra(EXTRA_BEVERAGE_ID, -1L)
        val beverageName = intent.getStringExtra(EXTRA_BEVERAGE_NAME)
        val unitPrice = intent.getDoubleExtra(EXTRA_UNIT_PRICE, 0.0)
        
        if (memberId == -1L || memberName == null || beverageId == -1L || 
            beverageName == null || unitPrice <= 0) {
            Toast.makeText(this, "Fehler: Transaktionsdaten unvollständig", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        
        // Initialize ViewModel with transaction data
        viewModel.setTransactionData(memberId, memberName, beverageId, beverageName, unitPrice)
        
        setupUI(memberName, beverageName, unitPrice)
        setupObservers()
        setupClickListeners()
    }
    
    /**
     * Setup UI with transaction information
     */
    private fun setupUI(memberName: String, beverageName: String, unitPrice: Double) {
        binding.apply {
            textViewMemberName.text = memberName
            textViewBeverageName.text = beverageName
            textViewUnitPrice.text = "${priceFormatter.format(unitPrice)} / Stück"
        }
    }
    
    /**
     * Setup observers for ViewModel LiveData
     */
    private fun setupObservers() {
        // PATTERN: Handle quantity state and real-time cost calculation
        viewModel.quantity.observe(this) { quantity ->
            binding.textViewQuantity.text = quantity.toString()
            
            // Update button states based on quantity limits
            binding.buttonMinus.isEnabled = quantity > 1
            binding.buttonPlus.isEnabled = quantity < 10
        }
        
        // Observe total price changes
        viewModel.totalPrice.observe(this) { totalPrice ->
            binding.textViewTotalCost.text = getString(
                com.club.getraenkeapp.R.string.total_cost,
                totalPrice
            )
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
                android.view.View.VISIBLE
            } else {
                android.view.View.GONE
            }
            
            // Disable buttons during loading
            binding.buttonConfirm.isEnabled = !isLoading
            binding.buttonCancel.isEnabled = !isLoading
            binding.buttonPlus.isEnabled = !isLoading && (viewModel.quantity.value ?: 1) < 10
            binding.buttonMinus.isEnabled = !isLoading && (viewModel.quantity.value ?: 1) > 1
        }
        
        // Observe error messages
        viewModel.errorMessage.observe(this) { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                viewModel.onErrorMessageShown()
            }
        }
    }
    
    /**
     * Setup click listeners for UI elements
     */
    private fun setupClickListeners() {
        // PATTERN: Plus and minus buttons for quantity adjustment
        binding.buttonPlus.setOnClickListener {
            viewModel.increaseQuantity()
        }
        
        binding.buttonMinus.setOnClickListener {
            viewModel.decreaseQuantity()
        }
        
        // Action buttons
        binding.buttonConfirm.setOnClickListener {
            viewModel.confirmTransaction()
        }
        
        binding.buttonCancel.setOnClickListener {
            viewModel.cancelTransaction()
        }
    }
    
    /**
     * Handle navigation events from ViewModel
     */
    private fun handleNavigationEvent(event: QuantityViewModel.NavigationEvent) {
        when (event) {
            is QuantityViewModel.NavigationEvent.TransactionSuccess -> {
                // PATTERN: Show confirmation dialog on successful storage, auto-return to main screen
                showConfirmationDialog(
                    memberName = event.memberName,
                    beverageName = event.beverageName,
                    quantity = event.quantity,
                    totalPrice = event.totalPrice
                )
            }
            
            is QuantityViewModel.NavigationEvent.Back -> {
                finish()
            }
        }
    }
    
    /**
     * Show confirmation dialog and return to main screen
     */
    private fun showConfirmationDialog(
        memberName: String, 
        beverageName: String, 
        quantity: Int, 
        totalPrice: Double
    ) {
        val dialog = ConfirmationDialog.newInstance(
            memberName = memberName,
            beverageName = beverageName,
            quantity = quantity,
            totalPrice = totalPrice
        )
        
        // Set callback for when dialog is dismissed
        dialog.setOnDismissCallback {
            // CRITICAL: Automatic return to main screen after confirmation
            returnToMainScreen()
        }
        
        dialog.show(supportFragmentManager, "ConfirmationDialog")
    }
    
    /**
     * Return to main screen clearing the back stack
     */
    private fun returnToMainScreen() {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        startActivity(intent)
        finish()
    }
    
    override fun onBackPressed() {
        super.onBackPressed()
        viewModel.cancelTransaction()
    }
}