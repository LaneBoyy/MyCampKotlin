package ru.laneboy.firsthacaton.api

import retrofit2.http.Body
import retrofit2.http.POST
import ru.laneboy.firsthacaton.data.SignUpDataResponse

interface ApiService {

    @POST("account/reg")
    suspend fun singUp(@Body singUpData: SignUpDataResponse)
}