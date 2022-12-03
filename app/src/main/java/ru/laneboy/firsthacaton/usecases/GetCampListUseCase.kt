package ru.laneboy.firsthacaton.usecases

import androidx.lifecycle.LiveData
import ru.laneboy.firsthacaton.CampItem
import ru.laneboy.firsthacaton.CampListRepository

class GetCampListUseCase(private val campListRepository: CampListRepository) {

    fun getCampList(): LiveData<List<CampItem>> {
        return campListRepository.getCampList()
    }
}