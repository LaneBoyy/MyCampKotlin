package ru.laneboy.firsthacaton

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CampItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val tvNameCamp = view.findViewById<TextView>(R.id.tvNameCamp)
    val tvTypeCamp = view.findViewById<TextView>(R.id.tvTypeCamp)
    val tvCoastOfCamp = view.findViewById<TextView>(R.id.tvCoastOfCamp)
    val tvCityForCamp = view.findViewById<TextView>(R.id.tvCityForCamp)
    val ivPictureCamp = view.findViewById<ImageView>(R.id.ivPictureCamp)
}