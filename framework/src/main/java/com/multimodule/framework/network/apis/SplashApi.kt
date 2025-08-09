package com.multimodule.framework.network.apis

import com.multimodule.commons.constants.ApiConstants
import com.multimodule.core.domain.common.network.ResultResponse
import com.multimodule.core.domain.model.response.AppSettingsResponse
import retrofit2.Response
import retrofit2.http.GET

interface SplashApi {

    @GET(ApiConstants.SAMPLE)
    suspend fun getAppSettings() : Response<ResultResponse<AppSettingsResponse?>>
}