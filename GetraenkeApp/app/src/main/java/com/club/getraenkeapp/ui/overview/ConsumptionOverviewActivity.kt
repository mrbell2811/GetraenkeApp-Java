package com.club.getraenkeapp.ui.overview

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.club.getraenkeapp.databinding.ActivityConsumptionOverviewBinding

class ConsumptionOverviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConsumptionOverviewBinding
    private val viewModel: ConsumptionOverviewViewModel by viewModels()
    private lateinit var consumptionAdapter: ConsumptionOverviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsumptionOverviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        consumptionAdapter = ConsumptionOverviewAdapter()
        binding.recyclerViewConsumption.apply {
            adapter = consumptionAdapter
            layoutManager = LinearLayoutManager(this@ConsumptionOverviewActivity)
        }
    }

    private fun setupObservers() {
        viewModel.consumptionData.observe(this) {
            consumptionAdapter.submitList(it)
        }
    }
}
