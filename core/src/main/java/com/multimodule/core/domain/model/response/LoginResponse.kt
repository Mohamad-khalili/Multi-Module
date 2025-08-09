package com.multimodule.core.domain.model.response

data class LoginResponse(
    val accessToken: String,
    val accessTokenExpireInHour: Int,
    val accessTokenExpireTime: String,
    val refreshToken: String,
    val refreshTokenExpireInMonth: Int,
)
