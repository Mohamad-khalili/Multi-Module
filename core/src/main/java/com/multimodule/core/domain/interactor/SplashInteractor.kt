package com.multimodule.core.domain.interactor

import com.multimodule.core.domain.common.network.Result
import com.multimodule.core.domain.common.network.ResultResponse
import com.multimodule.core.domain.model.response.AppSettingsResponse

interface SplashInteractor {
    suspend fun getAppSettings() : Result<ResultResponse<AppSettingsResponse?>>
}