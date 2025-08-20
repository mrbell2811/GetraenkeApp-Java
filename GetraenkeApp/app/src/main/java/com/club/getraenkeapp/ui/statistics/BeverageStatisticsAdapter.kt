package com.club.getraenkeapp.ui.statistics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.club.getraenkeapp.databinding.ItemBeverageStatisticBinding
import com.club.getraenkeapp.data.repository.AppRepository

/**
 * RecyclerView Adapter for displaying beverage consumption statistics
 * 
 * Shows individual beverage consumption with visual indicators
 * including quantity, total cost, and relative consumption bars.
 */
class BeverageStatisticsAdapter : RecyclerView.Adapter<BeverageStatisticsAdapter.BeverageStatisticsViewHolder>() {
    
    private var beverageStats = listOf<AppRepository.BeverageConsumption>()
    private var maxQuantity = 1 // For scaling progress bars
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeverageStatisticsViewHolder {
        val binding = ItemBeverageStatisticBinding.inflate(
            LayoutInflater.from(parent.context), 
            parent, 
            false
        )
        return BeverageStatisticsViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: BeverageStatisticsViewHolder, position: Int) {
        holder.bind(beverageStats[position])
    }
    
    override fun getItemCount(): Int = beverageStats.size
    
    fun updateData(newBeverageStats: List<AppRepository.BeverageConsumption>) {
        val diffCallback = BeverageStatisticsDiffCallback(beverageStats, newBeverageStats)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        
        beverageStats = newBeverageStats
        maxQuantity = beverageStats.maxOfOrNull { it.quantity } ?: 1
        
        diffResult.dispatchUpdatesTo(this)
    }
    
    inner class BeverageStatisticsViewHolder(
        private val binding: ItemBeverageStatisticBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(beverageConsumption: AppRepository.BeverageConsumption) {
            binding.textViewBeverageName.text = beverageConsumption.beverageName
            binding.textViewQuantity.text = "${beverageConsumption.quantity}x"
            binding.textViewTotalPrice.text = String.format("%.2f €", beverageConsumption.totalPrice)
            
            // Calculate average price
            val averagePrice = if (beverageConsumption.quantity > 0) {
                beverageConsumption.totalPrice / beverageConsumption.quantity
            } else {
                0.0
            }
            binding.textViewAveragePrice.text = String.format("Ø %.2f €", averagePrice)
            
            // Set progress bar (relative to maximum consumption)
            val progress = if (maxQuantity > 0) {
                ((beverageConsumption.quantity.toFloat() / maxQuantity.toFloat()) * 100).toInt()
            } else {
                0
            }
            binding.progressBarConsumption.progress = progress
        }
    }
    
    private class BeverageStatisticsDiffCallback(
        private val oldList: List<AppRepository.BeverageConsumption>,
        private val newList: List<AppRepository.BeverageConsumption>
    ) : DiffUtil.Callback() {
        
        override fun getOldListSize(): Int = oldList.size
        
        override fun getNewListSize(): Int = newList.size
        
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].beverageName == newList[newItemPosition].beverageName
        }
        
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}