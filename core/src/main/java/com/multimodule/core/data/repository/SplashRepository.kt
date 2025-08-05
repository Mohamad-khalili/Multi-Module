package com.multimodule.core.data.repository

interface SplashRepository {
    suspend fun getAppSettings() : String

}