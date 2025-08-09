package com.multimodule.core.domain.interactor

import com.multimodule.core.data.repository.SplashRepository
import com.multimodule.core.domain.common.network.Result
import com.multimodule.core.domain.common.network.ResultResponse
import com.multimodule.core.domain.model.response.AppSettingsResponse
import javax.inject.Inject

class SplashInteractorImpl @Inject constructor(
    private val splashRepository: SplashRepository
) : SplashInteractor {
    override suspend fun getAppSettings(): Result<ResultResponse<AppSettingsResponse?>> {
        return splashRepository.getAppSettings()
    }

}