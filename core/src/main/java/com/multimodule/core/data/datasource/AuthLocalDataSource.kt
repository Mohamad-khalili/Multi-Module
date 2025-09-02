package com.multimodule.core.data.datasource

interface AuthLocalDataSource {
    suspend fun getAccessToken(): String?
    fun getRefreshToken(): String?

    suspend fun saveAccessToken(token: String)

    fun getAccessExpireTime(): String?
    fun getRefreshExpireTime(): String?
    fun clearUserData()
}