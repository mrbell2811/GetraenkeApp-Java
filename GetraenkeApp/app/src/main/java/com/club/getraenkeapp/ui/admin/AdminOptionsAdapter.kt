package com.club.getraenkeapp.ui.admin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.club.getraenkeapp.databinding.ItemAdminOptionBinding

/**
 * RecyclerView adapter for admin options
 * 
 * Displays admin management options in a grid layout with icons and descriptions.
 */
class AdminOptionsAdapter(
    private val onOptionClick: (AdminViewModel.AdminOption) -> Unit
) : ListAdapter<AdminViewModel.AdminOptionItem, AdminOptionsAdapter.AdminOptionViewHolder>(AdminOptionDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminOptionViewHolder {
        val binding = ItemAdminOptionBinding.inflate(
            LayoutInflater.from(parent.context), 
            parent, 
            false
        )
        return AdminOptionViewHolder(binding, onOptionClick)
    }
    
    override fun onBindViewHolder(holder: AdminOptionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    /**
     * ViewHolder for admin options
     */
    class AdminOptionViewHolder(
        private val binding: ItemAdminOptionBinding,
        private val onOptionClick: (AdminViewModel.AdminOption) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(item: AdminViewModel.AdminOptionItem) {
            binding.apply {
                textViewIcon.text = item.icon
                textViewTitle.text = item.title
                textViewDescription.text = item.description
                
                // Set click listener
                root.setOnClickListener { 
                    onOptionClick(item.option) 
                }
                
                // Accessibility
                root.contentDescription = "${item.title}: ${item.description}"
                root.isClickable = true
                root.isFocusable = true
            }
        }
    }
    
    /**
     * DiffUtil callback for efficient list updates
     */
    private class AdminOptionDiffCallback : DiffUtil.ItemCallback<AdminViewModel.AdminOptionItem>() {
        override fun areItemsTheSame(
            oldItem: AdminViewModel.AdminOptionItem, 
            newItem: AdminViewModel.AdminOptionItem
        ): Boolean {
            return oldItem.option == newItem.option
        }
        
        override fun areContentsTheSame(
            oldItem: AdminViewModel.AdminOptionItem, 
            newItem: AdminViewModel.AdminOptionItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}