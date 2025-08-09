package com.multimodule.framework.di

import com.multimodule.core.data.datasource.AuthLocalDataSource
import com.multimodule.framework.network.AuthenticationInterceptor
import com.multimodule.framework.network.UserAgent
import com.multimodule.framework.network.apis.RefreshTokenApi
import dagger.Provides
import javax.inject.Singleton

object AuthInterceptorModule {

    @Provides
    @Singleton
    fun provideAuthInterceptor(
        authLocalDataSource: AuthLocalDataSource,
        refreshTokenApi: RefreshTokenApi,
        userAgent: UserAgent
    ): AuthenticationInterceptor{
        return AuthenticationInterceptor(authLocalDataSource,refreshTokenApi,userAgent)
    }

    @Provides
    @Singleton
    fun provideUserAgent(): UserAgent{
        return UserAgent()
    }
}