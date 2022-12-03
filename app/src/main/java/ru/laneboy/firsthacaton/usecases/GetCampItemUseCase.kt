package ru.laneboy.firsthacaton.usecases
import ru.laneboy.firsthacaton.CampItem
import ru.laneboy.firsthacaton.CampListRepository

class GetCampItemUseCase(private val campListRepository: CampListRepository) {

    fun getCampItem(campItemId: Int): CampItem {
        return campListRepository.getCampItem(campItemId)
    }
}