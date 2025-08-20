package com.club.getraenkeapp.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.club.getraenkeapp.data.Consumption
import com.club.getraenkeapp.databinding.ItemConsumptionOverviewBinding

class ConsumptionOverviewAdapter :
    ListAdapter<Consumption, ConsumptionOverviewAdapter.ConsumptionViewHolder>(ConsumptionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsumptionViewHolder {
        val binding =
            ItemConsumptionOverviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConsumptionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ConsumptionViewHolder, position: Int) {
        val consumption = getItem(position)
        holder.bind(consumption)
    }

    class ConsumptionViewHolder(private val binding: ItemConsumptionOverviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(consumption: Consumption) {
            binding.beverageName.text = consumption.beverageName
            binding.beverageCount.text = consumption.count.toString()
        }
    }

    private class ConsumptionDiffCallback : DiffUtil.ItemCallback<Consumption>() {
        override fun areItemsTheSame(oldItem: Consumption, newItem: Consumption): Boolean {
            return oldItem.beverageName == newItem.beverageName
        }

        override fun areContentsTheSame(oldItem: Consumption, newItem: Consumption): Boolean {
            return oldItem == newItem
        }
    }
}
