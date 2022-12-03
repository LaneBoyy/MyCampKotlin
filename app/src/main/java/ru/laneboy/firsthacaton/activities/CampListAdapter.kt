package ru.laneboy.firsthacaton.activities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.laneboy.firsthacaton.CampItem
import ru.laneboy.firsthacaton.CampItemDiffCallback
import ru.laneboy.firsthacaton.CampItemViewHolder
import ru.laneboy.firsthacaton.R

class CampListAdapter: ListAdapter<CampItem, CampItemViewHolder>(CampItemDiffCallback()) {

    var onCampItemClickListener: ((CampItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampItemViewHolder {
        val layout = R.layout.camp_item
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return CampItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: CampItemViewHolder, position: Int) {
        val campItem = getItem(position)
        with(viewHolder) {
            ivPictureCamp.setImageResource(campItem.pictureCamp)
            tvNameCamp.text = campItem.nameCamp
            tvCityForCamp.text = campItem.cityForCamp
            tvTypeCamp.text = campItem.typeCamp
            tvCoastOfCamp.text = campItem.coastOfCamp
            view.setOnClickListener {
                onCampItemClickListener?.invoke(campItem)
            }
        }
    }
}