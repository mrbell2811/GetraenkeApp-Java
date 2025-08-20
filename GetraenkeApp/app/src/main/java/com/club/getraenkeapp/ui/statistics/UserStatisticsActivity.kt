package com.club.getraenkeapp.ui.statistics

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.club.getraenkeapp.databinding.ActivityUserStatisticsBinding
import java.text.SimpleDateFormat
import java.util.*

/**
 * User Statistics Activity - Display consumption statistics since last monthly closing
 * 
 * Shows user consumption data in an attractive visual format including:
 * - Total consumption summary
 * - Beverage breakdown with quantities and costs
 * - Period information (since last monthly closing)
 * - Visual progress indicators
 */
class UserStatisticsActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityUserStatisticsBinding
    private val viewModel: UserStatisticsViewModel by viewModels()
    private lateinit var beverageStatisticsAdapter: BeverageStatisticsAdapter
    
    companion object {
        const val EXTRA_MEMBER_NAME = "member_name"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        android.util.Log.d("GetraenkeApp", "UserStatisticsActivity onCreate")
        
        binding = ActivityUserStatisticsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val memberName = intent.getStringExtra(EXTRA_MEMBER_NAME) ?: ""
        android.util.Log.d("GetraenkeApp", "Member name from intent: $memberName")
        
        setupUI(memberName)
        setupRecyclerView()
        setupObservers()
        setupClickListeners()
        
        // Load statistics for the member
        viewModel.loadStatistics(memberName)
    }
    
    private fun setupUI(memberName: String) {
        binding.textViewMemberName.text = memberName
        binding.textViewPeriodTitle.text = "Konsum seit letztem Abschluss"
    }
    
    private fun setupRecyclerView() {
        beverageStatisticsAdapter = BeverageStatisticsAdapter()
        binding.recyclerViewBeverages.apply {
            layoutManager = LinearLayoutManager(this@UserStatisticsActivity)
            adapter = beverageStatisticsAdapter
        }
    }
    
    private fun setupObservers() {
        viewModel.statistics.observe(this) { statistics ->
            statistics?.let {
                updateStatisticsDisplay(it)
            }
        }
        
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        
        viewModel.errorMessage.observe(this) { message ->
            // Handle error display if needed
            if (message.isNotEmpty()) {
                binding.textViewNoData.text = message
                binding.textViewNoData.visibility = View.VISIBLE
            }
        }
    }
    
    private fun setupClickListeners() {
        binding.buttonBack.setOnClickListener {
            finish()
        }
    }
    
    private fun updateStatisticsDisplay(statistics: UserStatisticsViewModel.UserStatisticsData) {
        // Update summary cards
        binding.textViewTotalQuantity.text = "${statistics.totalQuantity}"
        binding.textViewTotalSpent.text = String.format("%.2f €", statistics.totalSpent)
        binding.textViewTransactionCount.text = "${statistics.transactionCount}"
        
        // Update period information
        val periodStartDate = if (statistics.periodStart > 0) {
            SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN).format(Date(statistics.periodStart))
        } else {
            "Projektbeginn"
        }
        binding.textViewPeriodStart.text = "Seit: $periodStartDate"
        
        // Update beverage breakdown
        if (statistics.beverageBreakdown.isNotEmpty()) {
            binding.recyclerViewBeverages.visibility = View.VISIBLE
            binding.textViewNoData.visibility = View.GONE
            beverageStatisticsAdapter.updateData(
                statistics.beverageBreakdown.map { item ->
                    com.club.getraenkeapp.data.repository.AppRepository.BeverageConsumption(
                        beverageName = item.beverageName,
                        quantity = item.quantity,
                        totalPrice = item.totalPrice
                    )
                }
            )
        } else {
            binding.recyclerViewBeverages.visibility = View.GONE
            binding.textViewNoData.visibility = View.VISIBLE
            binding.textViewNoData.text = "Noch keine Getränke konsumiert"
        }
        
        // Calculate and display favorite beverage
        val favoriteBeverage = statistics.beverageBreakdown.maxByOrNull { it.quantity }
        if (favoriteBeverage != null) {
            binding.textViewFavoriteBeverage.text = favoriteBeverage.beverageName
            binding.textViewFavoriteBeverage.visibility = View.VISIBLE
            binding.textViewFavoriteLabel.visibility = View.VISIBLE
        } else {
            binding.textViewFavoriteBeverage.visibility = View.GONE
            binding.textViewFavoriteLabel.visibility = View.GONE
        }
    }
}