package com.club.getraenkeapp.ui.beverage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.club.getraenkeapp.R
import com.club.getraenkeapp.data.database.entities.Beverage
import com.club.getraenkeapp.databinding.ItemBeverageButtonBinding
import java.text.NumberFormat
import java.util.*

/**
 * RecyclerView adapter for beverage selection buttons
 * 
 * Uses ListAdapter with DiffUtil for efficient updates and ViewBinding
 * for type-safe view access. Displays beverage name and price clearly.
 */
class BeverageGridAdapter(
    private val onBeverageClick: (Beverage) -> Unit
) : ListAdapter<Beverage, BeverageGridAdapter.BeverageViewHolder>(BeverageDiffCallback()) {
    
    private val priceFormatter = NumberFormat.getCurrencyInstance(Locale.GERMANY)
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeverageViewHolder {
        val binding = ItemBeverageButtonBinding.inflate(
            LayoutInflater.from(parent.context), 
            parent, 
            false
        )
        return BeverageViewHolder(binding, onBeverageClick, priceFormatter)
    }
    
    override fun onBindViewHolder(holder: BeverageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    /**
     * ViewHolder for beverage buttons
     * 
     * CRITICAL: Touch targets must be 48dp minimum for accessibility
     * PATTERN: Material Design card views for beverages with name and price
     */
    class BeverageViewHolder(
        private val binding: ItemBeverageButtonBinding,
        private val onBeverageClick: (Beverage) -> Unit,
        private val priceFormatter: NumberFormat
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(beverage: Beverage) {
            binding.apply {
                textViewBeverageName.text = beverage.name
                textViewBeveragePrice.text = priceFormatter.format(beverage.price)
                
                // Set category icon based on beverage category
                val iconRes = getCategoryIcon(beverage.category)
                imageViewCategoryIcon.setImageResource(iconRes)
                
                // Set click listener with beverage data
                root.setOnClickListener { 
                    onBeverageClick(beverage) 
                }
                
                // Ensure accessibility
                val categoryDesc = getCategoryDescription(beverage.category)
                root.contentDescription = "$categoryDesc ${beverage.name}, ${priceFormatter.format(beverage.price)}"
                
                // Visual feedback for touch
                root.isClickable = true
                root.isFocusable = true
            }
        }
        
        /**
         * Get category icon based on beverage category (matches getraenke-joy logic)
         */
        private fun getCategoryIcon(category: String?): Int {
            return when (category?.lowercase()) {
                "warm", "heiss", "hot" -> R.drawable.ic_hot_drink
                "alkohol", "alkoholisch", "alcohol", "alcoholic" -> R.drawable.ic_alcoholic_drink
                "kalt", "cold" -> R.drawable.ic_cold_drink
                else -> R.drawable.ic_cold_drink // Default to cold drink
            }
        }
        
        /**
         * Get category description for accessibility
         */
        private fun getCategoryDescription(category: String?): String {
            return when (category?.lowercase()) {
                "warm", "heiss", "hot" -> "Warmes Getränk"
                "alkohol", "alkoholisch", "alcohol", "alcoholic" -> "Alkoholisches Getränk"
                "kalt", "cold" -> "Kaltes Getränk"
                else -> "Getränk"
            }
        }
    }
    
    /**
     * DiffUtil callback for efficient list updates
     */
    private class BeverageDiffCallback : DiffUtil.ItemCallback<Beverage>() {
        override fun areItemsTheSame(oldItem: Beverage, newItem: Beverage): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Beverage, newItem: Beverage): Boolean {
            return oldItem == newItem
        }
    }
}