package com.club.getraenkeapp.ui.beverage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.club.getraenkeapp.data.repository.AppRepository
import com.club.getraenkeapp.databinding.ItemMemberStatisticBinding
import java.text.NumberFormat
import java.util.*

class MemberStatisticsAdapter :
    ListAdapter<AppRepository.BeverageConsumption, MemberStatisticsAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMemberStatisticBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(private val binding: ItemMemberStatisticBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AppRepository.BeverageConsumption) {
            binding.beverageName.text = item.beverageName
            binding.beverageQuantity.text = item.quantity.toString()
            binding.beverageTotal.text = NumberFormat.getCurrencyInstance(Locale.GERMANY).format(item.totalPrice)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<AppRepository.BeverageConsumption>() {
        override fun areItemsTheSame(
            oldItem: AppRepository.BeverageConsumption,
            newItem: AppRepository.BeverageConsumption
        ): Boolean {
            return oldItem.beverageName == newItem.beverageName
        }

        override fun areContentsTheSame(
            oldItem: AppRepository.BeverageConsumption,
            newItem: AppRepository.BeverageConsumption
        ): Boolean {
            return oldItem == newItem
        }
    }
}
