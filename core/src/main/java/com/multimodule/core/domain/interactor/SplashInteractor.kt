package com.multimodule.core.domain.interactor

interface SplashInteractor {
    suspend fun getAppSettings() : String
}