package ru.laneboy.firsthacaton

import androidx.lifecycle.ViewModel
import ru.laneboy.firsthacaton.data.CampListRepositoryImpl
import ru.laneboy.firsthacaton.usecases.GetCampListUseCase

class MainViewModel: ViewModel() {

    private val repository = CampListRepositoryImpl
    private val getShopListUseCase = GetCampListUseCase(repository)

    val campList = getShopListUseCase.getCampList()
}