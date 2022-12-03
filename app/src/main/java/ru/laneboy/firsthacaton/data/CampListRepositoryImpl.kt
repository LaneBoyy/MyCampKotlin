package ru.laneboy.firsthacaton.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.laneboy.firsthacaton.CampItem
import ru.laneboy.firsthacaton.CampListRepository
import ru.laneboy.firsthacaton.R

object CampListRepositoryImpl: CampListRepository {

    private val campListLD = MutableLiveData<List<CampItem>>()
    private val campList = sortedSetOf<CampItem>({ p0, p1 -> p0.id.compareTo(p1.id) })

    private var autoIncrementId = 0

    init {
        for (i in 0 until 3) {
            val item = CampItem("Хуй", "Хуевый", "Чернобыль", "Дохуя", R.drawable.sun_coutry)
            addCampItem(item)
        }
    }

    override fun addCampItem(campItem: CampItem) {
        if (campItem.id == CampItem.UNDEFINED_ID) {
            campItem.id = autoIncrementId++
        }
        campList.add(campItem)
        updateList()
    }

    override fun getCampItem(campItemId: Int): CampItem {
        return campList.find {
            it.id == campItemId
        } ?: throw  RuntimeException("Element with id $campItemId not found")
    }

    override fun getCampList(): LiveData<List<CampItem>> {
        return campListLD
    }

    private fun updateList() {
        campListLD.value = campList.toList()
    }
}