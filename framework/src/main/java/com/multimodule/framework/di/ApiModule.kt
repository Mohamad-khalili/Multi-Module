package com.multimodule.framework.di

import com.multimodule.framework.di.qualifires.Cdn
import com.multimodule.framework.di.qualifires.RenewToken
import com.multimodule.framework.network.apis.RefreshTokenApi
import com.multimodule.framework.network.apis.SplashApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideRefreshToken (@RenewToken retrofit: Retrofit): RefreshTokenApi = retrofit.create(RefreshTokenApi::class.java)

    @Provides
    @Singleton
    fun provideSplashApi(@Cdn retrofit: Retrofit) : SplashApi = retrofit.create(SplashApi::class.java)
}