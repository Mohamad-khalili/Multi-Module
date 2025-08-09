package com.multimodule.framework.repositoryimpl

import com.multimodule.core.data.repository.SplashRepository
import com.multimodule.core.domain.common.network.Result
import com.multimodule.core.domain.common.network.ResultResponse
import com.multimodule.core.domain.model.response.AppSettingsResponse
import com.multimodule.framework.network.Api
import com.multimodule.framework.network.apis.SplashApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SplashRepositoryImpl @Inject constructor(private val api: SplashApi) : SplashRepository {
    override suspend fun getAppSettings(): Result<ResultResponse<AppSettingsResponse?>> {
       return withContext(Dispatchers.IO){
           return@withContext Api.result {
               api.getAppSettings()
           }
       }
    }
}