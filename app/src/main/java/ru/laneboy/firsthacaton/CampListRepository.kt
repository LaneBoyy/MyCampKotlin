package ru.laneboy.firsthacaton

import androidx.lifecycle.LiveData

interface CampListRepository {

    fun addCampItem(campItem: CampItem)

    fun getCampItem(campItemId: Int): CampItem

    fun getCampList(): LiveData<List<CampItem>>
}