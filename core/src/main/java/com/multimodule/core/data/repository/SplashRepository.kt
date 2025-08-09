package com.multimodule.core.data.repository

import com.multimodule.core.domain.common.network.Result
import com.multimodule.core.domain.common.network.ResultResponse
import com.multimodule.core.domain.model.response.AppSettingsResponse

interface SplashRepository {
    suspend fun getAppSettings() : Result<ResultResponse<AppSettingsResponse?>>

}