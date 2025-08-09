package com.multimodule.core.data.datasource

interface AuthLocalDataSource {
    fun getAccessToken(): String?
    fun getRefreshToken(): String?

    fun getAccessExpireTime(): String?
    fun getRefreshExpireTime(): String?
    fun clearUserData()
}