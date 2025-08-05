package com.multimodule.core.domain.interactor

import com.multimodule.core.data.repository.SplashRepository
import javax.inject.Inject

class SplashInteractorImpl @Inject constructor(
    private val splashRepository: SplashRepository
) : SplashInteractor {
    override suspend fun getAppSettings(): String {
        return splashRepository.getAppSettings()
    }

}