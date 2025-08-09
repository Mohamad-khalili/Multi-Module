package com.multimodule.framework.network.apis

import com.multimodule.commons.constants.ApiConstants
import com.multimodule.core.domain.common.network.ResultResponse
import com.multimodule.core.domain.model.request.RefreshTokenRequest
import com.multimodule.core.domain.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface RefreshTokenApi {
    @Headers("Content-Type: application/json")
    @POST(ApiConstants.RENEW_TOKEN)
    suspend fun renewToken(
        @Body req: RefreshTokenRequest,
        @Header("User-Agent") userAgent: String,
        @Header("Device-Id") deviceId: String?,
    ): Response<ResultResponse<LoginResponse>>
}