package com.club.getraenkeapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.club.getraenkeapp.databinding.ActivityMainBinding
import com.club.getraenkeapp.ui.admin.AdminActivity
import com.club.getraenkeapp.ui.beverage.BeverageActivity
import com.club.getraenkeapp.ui.statistics.UserStatisticsActivity
import java.text.SimpleDateFormat
import java.util.*

/**
 * Main Activity - Member Name Grid Screen
 * 
 * PATTERN: AppCompatActivity with viewBinding, RecyclerView with GridLayoutManager 
 * for member buttons, observe LiveData from ViewModel, handle button clicks with 
 * member selection, implement always-on screen with WAKE_LOCK.
 * 
 * CRITICAL: Enable always-on display and implement tablet-optimized grid layout
 */
class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var memberAdapter: MemberGridAdapter
    
    // Date/Time formatters
    private val dateFormat = SimpleDateFormat("EEEE, dd. MMMM yyyy", Locale.GERMAN)
    private val timeFormat = SimpleDateFormat("HH:mm", Locale.GERMAN)
    
    // Handler for updating time every minute
    private val timeUpdateHandler = Handler(Looper.getMainLooper())
    private val timeUpdateRunnable = object : Runnable {
        override fun run() {
            updateDateTime()
            timeUpdateHandler.postDelayed(this, 60000) // Update every minute
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // PATTERN: Always initialize binding first
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // CRITICAL: Enable always-on display
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        
        setupRecyclerView()
        setupObservers()
        setupClickListeners()
        
        // Initialize and start date/time updates
        updateDateTime()
        startTimeUpdates()
    }
    
    /**
     * Setup RecyclerView with GridLayoutManager and adapter
     */
    private fun setupRecyclerView() {
        // PATTERN: Grid layout calculation based on member count and screen size
        val spanCount = calculateOptimalSpanCount()
        val layoutManager = GridLayoutManager(this, spanCount)
        
        binding.recyclerViewMembers.layoutManager = layoutManager
        
        // CRITICAL: Touch targets must be 48dp minimum, adapter handles member selection
        memberAdapter = MemberGridAdapter(
            onMemberClick = { member ->
                viewModel.onMemberSelected(member)
            },
            onMemberLongClick = { member ->
                viewModel.onMemberSelected(member)
            }
        )
        
        binding.recyclerViewMembers.adapter = memberAdapter
        
        // Optimize performance for fixed size content
        binding.recyclerViewMembers.setHasFixedSize(true)
    }
    
    /**
     * Setup observers for ViewModel LiveData
     */
    private fun setupObservers() {
        // PATTERN: Observe LiveData for reactive updates
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
    }
    
    /**
     * Setup click listeners for UI elements
     */
    private fun setupClickListeners() {
        binding.buttonAdmin.setOnClickListener {
            viewModel.onAdminAccess()
        }

        binding.buttonOverview.setOnClickListener {
            val intent = Intent(this, com.club.getraenkeapp.ui.overview.ConsumptionOverviewActivity::class.java)
            startActivity(intent)
        }
    }
    
    /**
     * Handle navigation events from ViewModel
     */
    private fun handleNavigationEvent(event: MainViewModel.NavigationEvent) {
        when (event) {
            is MainViewModel.NavigationEvent.BeverageSelection -> {
                // Start beverage selection with selected member
                val intent = Intent(this, BeverageActivity::class.java).apply {
                    putExtra(BeverageActivity.EXTRA_MEMBER_ID, event.memberId)
                    putExtra(BeverageActivity.EXTRA_MEMBER_NAME, event.memberName)
                }
                startActivity(intent)
            }
            
            is MainViewModel.NavigationEvent.AdminAccess -> {
                // Start admin panel
                val intent = Intent(this, AdminActivity::class.java)
                startActivity(intent)
            }
        }
    }
    
    /**
     * Calculate optimal span count for grid layout
     * GOTCHA: Tablet optimization - calculate based on screen width
     */
    private fun calculateOptimalSpanCount(): Int {
        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val buttonMinWidth = resources.getDimensionPixelSize(
            com.club.getraenkeapp.R.dimen.member_button_min_width
        )
        
        return viewModel.calculateOptimalSpanCount(screenWidth, buttonMinWidth)
    }
    
    override fun onResume() {
        super.onResume()
        // PATTERN: Return to main screen - ensure always-on flag is set
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        
        // Restart time updates when returning to the screen
        updateDateTime()
        startTimeUpdates()
    }
    
    override fun onPause() {
        super.onPause()
        // Stop time updates when app is paused to save resources
        stopTimeUpdates()
        // Note: Keep screen on even when paused for club environment
        // The flag will be managed by system power settings if needed
    }
    
    override fun onDestroy() {
        super.onDestroy()
        // Clean up time updates
        stopTimeUpdates()
    }
    
    /**
     * Update the current date and time display
     */
    private fun updateDateTime() {
        val currentTime = Date()
        binding.textViewDate.text = dateFormat.format(currentTime)
        binding.textViewTime.text = timeFormat.format(currentTime)
    }
    
    /**
     * Start automatic time updates every minute
     */
    private fun startTimeUpdates() {
        stopTimeUpdates() // Ensure no duplicate handlers
        timeUpdateHandler.postDelayed(timeUpdateRunnable, 60000) // Start in 1 minute
    }
    
    /**
     * Stop automatic time updates
     */
    private fun stopTimeUpdates() {
        timeUpdateHandler.removeCallbacks(timeUpdateRunnable)
    }
    
    
}