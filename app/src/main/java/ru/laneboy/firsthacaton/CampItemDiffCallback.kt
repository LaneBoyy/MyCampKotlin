package ru.laneboy.firsthacaton

import androidx.recyclerview.widget.DiffUtil

class CampItemDiffCallback: DiffUtil.ItemCallback<CampItem>() {

    override fun areItemsTheSame(oldItem: CampItem, newItem: CampItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CampItem, newItem: CampItem): Boolean {
        return oldItem == newItem
    }
}