package com.multimodule.core.domain.model.request

data class RefreshTokenRequest(
    val accessToken: String?,
    val refreshToken: String?,
)
