package com.multimodule.framework.di

import com.multimodule.core.data.repository.SplashRepository
import com.multimodule.framework.network.apis.SplashApi
import com.multimodule.framework.repositoryimpl.SplashRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSplashRepo(api: SplashApi): SplashRepository = SplashRepositoryImpl(api)
}