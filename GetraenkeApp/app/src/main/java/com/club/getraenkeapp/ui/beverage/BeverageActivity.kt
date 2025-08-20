package com.club.getraenkeapp.ui.beverage

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.club.getraenkeapp.databinding.ActivityBeverageBinding
import com.club.getraenkeapp.ui.quantity.QuantityActivity

/**
 * Beverage Selection Activity
 * 
 * PATTERN: Receive selected member via Intent extras, full-screen layout with back button,
 * RecyclerView grid for beverage options, display beverage name and price clearly.
 * 
 * CRITICAL: Full-screen approach eliminates distractions, large touch targets for tablet
 */
class BeverageActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityBeverageBinding
    private val viewModel: BeverageViewModel by viewModels()
    private lateinit var beverageAdapter: BeverageGridAdapter
    private lateinit var memberStatisticsAdapter: MemberStatisticsAdapter
    
    companion object {
        const val EXTRA_MEMBER_ID = "extra_member_id"
        const val EXTRA_MEMBER_NAME = "extra_member_name"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityBeverageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Get member information from intent
        val memberId = intent.getLongExtra(EXTRA_MEMBER_ID, -1L)
        val memberName = intent.getStringExtra(EXTRA_MEMBER_NAME)
        
        if (memberId == -1L || memberName == null) {
            Toast.makeText(this, "Fehler: Mitglied Information fehlt", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        
        // Initialize ViewModel with member info
        viewModel.setMemberInfo(memberId, memberName)
        
        setupRecyclerView()
        setupObservers()
        setupClickListeners()
        
        // Display member name
        binding.textViewMemberName.text = memberName
    }
    
    /**
     * Setup RecyclerView with GridLayoutManager and adapter
     */
    private fun setupRecyclerView() {
        // PATTERN: Grid layout for beverage options
        val spanCount = calculateOptimalSpanCount()
        val layoutManager = GridLayoutManager(this, spanCount)
        
        binding.recyclerViewBeverages.layoutManager = layoutManager
        
        // CRITICAL: Large touch targets optimized for tablet
        beverageAdapter = BeverageGridAdapter { beverage ->
            viewModel.onBeverageSelected(beverage)
        }
        
        binding.recyclerViewBeverages.adapter = beverageAdapter
        
        // Optimize performance for fixed size content
        binding.recyclerViewBeverages.setHasFixedSize(true)

        memberStatisticsAdapter = MemberStatisticsAdapter()
        binding.recyclerViewStatistics.apply {
            adapter = memberStatisticsAdapter
            this.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@BeverageActivity)
        }
    }
    
    /**
     * Setup observers for ViewModel LiveData
     */
    private fun setupObservers() {
        // PATTERN: Observe LiveData for reactive updates
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
        }
        
        // Observe error messages
        viewModel.errorMessage.observe(this) { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                viewModel.onErrorMessageShown()
            }
        }

        viewModel.memberStatistics.observe(this) { stats ->
            binding.totalConsumptionValue.text = stats.totalQuantity.toString()
            binding.totalSpentValue.text = java.text.NumberFormat.getCurrencyInstance(java.util.Locale.GERMANY).format(stats.totalSpent)
            memberStatisticsAdapter.submitList(stats.beverageBreakdown)
        }
    }
    
    /**
     * Setup click listeners for UI elements
     */
    private fun setupClickListeners() {
        binding.buttonBack.setOnClickListener {
            viewModel.onBackPressed()
        }
    }
    
    /**
     * Handle navigation events from ViewModel
     */
    private fun handleNavigationEvent(event: BeverageViewModel.NavigationEvent) {
        when (event) {
            is BeverageViewModel.NavigationEvent.QuantitySelection -> {
                // Start quantity confirmation with selected beverage
                val intent = Intent(this, QuantityActivity::class.java).apply {
                    putExtra(QuantityActivity.EXTRA_MEMBER_ID, event.memberId)
                    putExtra(QuantityActivity.EXTRA_MEMBER_NAME, event.memberName)
                    putExtra(QuantityActivity.EXTRA_BEVERAGE_ID, event.beverageId)
                    putExtra(QuantityActivity.EXTRA_BEVERAGE_NAME, event.beverageName)
                    putExtra(QuantityActivity.EXTRA_UNIT_PRICE, event.unitPrice)
                }
                startActivity(intent)
            }
            
            is BeverageViewModel.NavigationEvent.Back -> {
                finish()
            }
        }
    }
    
    /**
     * Calculate optimal span count for beverage grid - Orientation aware
     */
    private fun calculateOptimalSpanCount(): Int {
        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val buttonMinWidth = resources.getDimensionPixelSize(
            com.club.getraenkeapp.R.dimen.beverage_button_min_width
        )
        
        val spanCount = (screenWidth / buttonMinWidth).coerceAtLeast(1)
        
        // Different max columns based on screen aspect ratio
        return if (screenWidth < 800) { // Likely portrait or small screen
            spanCount.coerceAtMost(1) // Maximum 1 column for portrait readability with large buttons
        } else { // Likely landscape
            spanCount.coerceAtMost(3) // Maximum 3 columns for landscape mode
        }
    }
    
    override fun onBackPressed() {
        super.onBackPressed()
        viewModel.onBackPressed()
    }
}