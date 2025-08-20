package com.club.getraenkeapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.club.getraenkeapp.data.database.entities.Member
import com.club.getraenkeapp.databinding.ItemMemberButtonBinding

/**
 * RecyclerView adapter for member name buttons
 * 
 * Uses ListAdapter with DiffUtil for efficient updates and ViewBinding
 * for type-safe view access. Implements Material Design touch feedback.
 */
class MemberGridAdapter(
    private val onMemberClick: (Member) -> Unit,
    private val onMemberLongClick: (Member) -> Unit = {}
) : ListAdapter<Member, MemberGridAdapter.MemberViewHolder>(MemberDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val binding = ItemMemberButtonBinding.inflate(
            LayoutInflater.from(parent.context), 
            parent, 
            false
        )
        return MemberViewHolder(binding, onMemberClick, onMemberLongClick)
    }
    
    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    /**
     * ViewHolder for member cards with avatar and name
     * 
     * CRITICAL: Touch targets must be 48dp minimum for accessibility
     */
    class MemberViewHolder(
        private val binding: ItemMemberButtonBinding,
        private val onMemberClick: (Member) -> Unit,
        private val onMemberLongClick: (Member) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(member: Member) {
            // Set member name
            binding.textViewMemberName.text = member.name
            
            // Generate and set initials for avatar
            val initials = generateInitials(member.name)
            binding.textViewMemberInitials.text = initials
            
            // Set click listener on the card
            binding.root.setOnClickListener { 
                onMemberClick(member) 
            }
            
            // Set long click listener for statistics
            binding.root.setOnLongClickListener { 
                android.util.Log.d("GetraenkeApp", "Long press detected for member: ${member.name}")
                onMemberLongClick(member)
                true
            }
            
            // Ensure accessibility
            binding.root.contentDescription = "Mitglied ${member.name} auswählen. Lange drücken für Statistiken"
        }
        
        /**
         * Generate initials from full name (matches getraenke-joy logic)
         */
        private fun generateInitials(fullName: String): String {
            return fullName
                .split(" ")
                .mapNotNull { it.firstOrNull()?.toString() }
                .take(2)
                .joinToString("")
                .uppercase()
        }
    }
    
    /**
     * DiffUtil callback for efficient list updates
     */
    private class MemberDiffCallback : DiffUtil.ItemCallback<Member>() {
        override fun areItemsTheSame(oldItem: Member, newItem: Member): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Member, newItem: Member): Boolean {
            return oldItem == newItem
        }
    }
}