package com.multimodule.framework.repositoryimpl

import com.multimodule.core.data.repository.SplashRepository
import com.multimodule.framework.network.apis.SplashApi
import javax.inject.Inject

class SplashRepositoryImpl @Inject constructor(private val api: SplashApi) : SplashRepository {
    override suspend fun getAppSettings(): String {
       return ""
    }
}