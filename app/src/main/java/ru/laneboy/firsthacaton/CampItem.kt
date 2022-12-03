package ru.laneboy.firsthacaton
import androidx.annotation.DrawableRes

data class CampItem(
    val nameCamp: String,
    val typeCamp: String,
    val coastOfCamp: String,

    @DrawableRes
    val pictureCamp: Int,

    var id: Int = UNDEFINED_ID
) {
    companion object{
        const val UNDEFINED_ID = -1
    }
}
