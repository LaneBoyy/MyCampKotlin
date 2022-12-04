package ru.laneboy.firsthacaton.data

data class SignInDataResponse(
    val email: String,
    val password: String,
    val role: String
)
