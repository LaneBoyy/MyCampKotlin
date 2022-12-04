package ru.laneboy.firsthacaton.api

import retrofit2.http.Body
import retrofit2.http.POST
import ru.laneboy.firsthacaton.data.SignInDataResponse
import ru.laneboy.firsthacaton.data.SignUpDataResponse

interface ApiService {

    @POST("account/reg")
    suspend fun singUp(@Body singUpData: SignUpDataResponse)

    @POST("account/sign")
    suspend fun signIn(@Body signInData: SignInDataResponse)
}