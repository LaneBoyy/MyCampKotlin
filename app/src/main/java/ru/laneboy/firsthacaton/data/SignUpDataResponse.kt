package ru.laneboy.firsthacaton.data

data class SignUpDataResponse(
    val email: String,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val password: String,
    val passwordConfirm: String,
    val role: String
)