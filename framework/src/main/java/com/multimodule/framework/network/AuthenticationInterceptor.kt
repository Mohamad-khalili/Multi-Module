package com.multimodule.framework.network

import com.multimodule.commons.utils.DateTimeHelper
import com.multimodule.core.data.datasource.AuthLocalDataSource
import com.multimodule.core.domain.common.network.Result
import com.multimodule.core.domain.common.network.ResultResponse
import com.multimodule.core.domain.model.request.RefreshTokenRequest
import com.multimodule.core.domain.model.response.LoginResponse
import com.multimodule.framework.network.apis.RefreshTokenApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor(
    private val auth: AuthLocalDataSource,
    private val api: RefreshTokenApi,
    private val userAgent: UserAgent
) : Interceptor, Authenticator {

    companion object {
        const val AUTH = "Authorization"
        const val BEAR = "Bearer"
        const val USER_AGENT = "User-Agent"
        const val DEVICE_ID = "Device-Id"
        const val LANGUAGE = "Accept-Language"
    }


    override fun intercept(chain: Interceptor.Chain): Response {

        runBlocking { saveToken() }

        val accessToken = runBlocking { auth.getAccessToken() }
        val originalRequest = chain.request()
        val authenticationRequest = originalRequest.newBuilder()
            .addHeader(AUTH, "$BEAR $accessToken")
            .addHeader(USER_AGENT, userAgent.userAgentDetails())
//            .addHeader(DEVICE_ID, auth.getAndroidId())
            .build()

        if (accessTokenIsValid() || auth.getRefreshToken().isNullOrEmpty()) {
            return chain.proceed(authenticationRequest)
        } else {
            return runBlocking {
                when (val tokenResponse = getUpdatedToken()) {
                    is Result.Success -> {
                        val it = tokenResponse.data.result
                        //saveToken(it)
                        val req = originalRequest.newBuilder()
                            .header(AUTH, BEAR + " ${it?.accessToken}")
                            .addHeader(USER_AGENT, userAgent.userAgentDetails())
                            .build()
                        chain.proceed(req)
                    }

                    else -> {
                        chain.proceed(authenticationRequest)
                    }
                }
            }
        }

    }

    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            when (val tokenResponse = getUpdatedToken()) {
                is Result.Success -> {
                    val it = tokenResponse.data.result
                    //saveToken(it)
                    response.request.newBuilder()
                        .header(AUTH, BEAR + " ${it?.accessToken}")
                        .build()
                }

                else -> {
                    auth.clearUserData()
                    //Launch login screen or sharedViewModel.restartApp(true) or ...
                    null
                }
            }
        }
    }


    //call renewToken and get new accessToken
    private suspend fun getUpdatedToken(): Result<ResultResponse<LoginResponse>> {
        val tokenInfo = RefreshTokenRequest(
            accessToken = auth.getAccessToken(),
            refreshToken = auth.getRefreshToken()
        )
        return withContext(Dispatchers.IO) {
            return@withContext Api.result {
                api.renewToken(
                    req = tokenInfo,
                    userAgent = userAgent.userAgentDetails(),
                    ""
                )
            }
        }
    }


    //check accessToken expire time
    private fun accessTokenIsValid(): Boolean {
        return if (auth.getAccessExpireTime() != null) {
            val tokenExpireTime = auth.getAccessExpireTime()
            if (tokenExpireTime.isNullOrEmpty())
                false
            else
                DateTimeHelper.compareUtcTimesInMills(tokenExpireTime)

        } else
            false
    }

    /**Test Data Store**/
    private suspend fun saveToken() {
        withContext(Dispatchers.IO) {
            return@withContext auth.saveAccessToken("sdifjmnodjrfnbgodrjngboedrngoerg")
        }
    }

}