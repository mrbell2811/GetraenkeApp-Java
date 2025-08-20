package com.club.getraenkeapp.ui.admin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.club.getraenkeapp.data.database.entities.Member
import com.club.getraenkeapp.databinding.ItemMemberManagementBinding
import java.text.SimpleDateFormat
import java.util.*

/**
 * RecyclerView Adapter for Member Management List
 * 
 * PATTERN: ListAdapter with DiffUtil for efficient updates,
 * item actions for edit/delete operations with callbacks
 */
class MemberManagementAdapter(
    private val onEditClick: (Member) -> Unit,
    private val onDeleteClick: (Member) -> Unit
) : ListAdapter<Member, MemberManagementAdapter.MemberViewHolder>(MemberDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val binding = ItemMemberManagementBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MemberViewHolder(binding, onEditClick, onDeleteClick)
    }
    
    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class MemberViewHolder(
        private val binding: ItemMemberManagementBinding,
        private val onEditClick: (Member) -> Unit,
        private val onDeleteClick: (Member) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        
        private val dateFormatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMAN)
        
        fun bind(member: Member) {
            binding.apply {
                textViewMemberName.text = member.name
                textViewMemberId.text = "ID: ${member.id}"
                
                // Format creation date
                val createdDate = Date(member.createdAt)
                textViewCreatedAt.text = "Erstellt: ${dateFormatter.format(createdDate)}"
                
                // Grid position info
                if (member.gridPosition != null) {
                    textViewGridPosition.text = "Position: ${member.gridPosition}"
                    textViewGridPosition.visibility = android.view.View.VISIBLE
                } else {
                    textViewGridPosition.visibility = android.view.View.GONE
                }
                
                // Edit button
                buttonEdit.setOnClickListener {
                    onEditClick(member)
                }
                
                // Delete button
                buttonDelete.setOnClickListener {
                    onDeleteClick(member)
                }
                
                // Card click for edit
                cardView.setOnClickListener {
                    onEditClick(member)
                }
            }
        }
    }
    
    private class MemberDiffCallback : DiffUtil.ItemCallback<Member>() {
        override fun areItemsTheSame(oldItem: Member, newItem: Member): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Member, newItem: Member): Boolean {
            return oldItem == newItem
        }
    }
}