package com.club.getraenkeapp.ui.admin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.club.getraenkeapp.data.database.entities.Beverage
import com.club.getraenkeapp.databinding.ItemBeverageManagementBinding
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * RecyclerView Adapter for Beverage Management List
 * 
 * PATTERN: ListAdapter with DiffUtil for efficient updates,
 * item actions for edit/delete/toggle operations with callbacks
 */
class BeverageManagementAdapter(
    private val onEditClick: (Beverage) -> Unit,
    private val onDeleteClick: (Beverage) -> Unit,
    private val onToggleActiveClick: (Beverage) -> Unit
) : ListAdapter<Beverage, BeverageManagementAdapter.BeverageViewHolder>(BeverageDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeverageViewHolder {
        val binding = ItemBeverageManagementBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BeverageViewHolder(binding, onEditClick, onDeleteClick, onToggleActiveClick)
    }
    
    override fun onBindViewHolder(holder: BeverageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class BeverageViewHolder(
        private val binding: ItemBeverageManagementBinding,
        private val onEditClick: (Beverage) -> Unit,
        private val onDeleteClick: (Beverage) -> Unit,
        private val onToggleActiveClick: (Beverage) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        
        private val dateFormatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMAN)
        private val priceFormatter = NumberFormat.getCurrencyInstance(Locale.GERMANY)
        
        fun bind(beverage: Beverage) {
            binding.apply {
                textViewBeverageName.text = beverage.name
                textViewBeverageId.text = "ID: ${beverage.id}"
                
                // Format price
                textViewBeveragePrice.text = priceFormatter.format(beverage.price)
                
                // Category (optional)
                if (beverage.category != null) {
                    textViewBeverageCategory.text = "Kategorie: ${beverage.category}"
                    textViewBeverageCategory.visibility = android.view.View.VISIBLE
                } else {
                    textViewBeverageCategory.visibility = android.view.View.GONE
                }
                
                // Hide creation date since not tracked in entity
                textViewCreatedAt.visibility = android.view.View.GONE
                
                // Active status
                textViewActiveStatus.text = if (beverage.active) "Aktiv" else "Inaktiv"
                textViewActiveStatus.setTextColor(
                    if (beverage.active) {
                        android.graphics.Color.GREEN
                    } else {
                        android.graphics.Color.RED
                    }
                )
                
                // Toggle active button
                buttonToggleActive.text = if (beverage.active) "Deaktivieren" else "Aktivieren"
                buttonToggleActive.setOnClickListener {
                    onToggleActiveClick(beverage)
                }
                
                // Edit button
                buttonEdit.setOnClickListener {
                    onEditClick(beverage)
                }
                
                // Delete button
                buttonDelete.setOnClickListener {
                    onDeleteClick(beverage)
                }
                
                // Card click for edit
                cardView.setOnClickListener {
                    onEditClick(beverage)
                }
                
                // Visual indication for inactive beverages
                cardView.alpha = if (beverage.active) 1.0f else 0.7f
            }
        }
    }
    
    private class BeverageDiffCallback : DiffUtil.ItemCallback<Beverage>() {
        override fun areItemsTheSame(oldItem: Beverage, newItem: Beverage): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Beverage, newItem: Beverage): Boolean {
            return oldItem == newItem
        }
    }
}