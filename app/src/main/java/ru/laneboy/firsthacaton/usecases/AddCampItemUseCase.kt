package ru.laneboy.firsthacaton.usecases
import ru.laneboy.firsthacaton.CampItem
import ru.laneboy.firsthacaton.CampListRepository

class AddCampItemUseCase(private val campListRepository: CampListRepository) {

    fun addCampItem(campItem: CampItem) {
        campListRepository.addCampItem(campItem)
    }
}