package com.multimodule.framework.di

import com.multimodule.core.data.repository.SplashRepository
import com.multimodule.core.domain.interactor.SplashInteractor
import com.multimodule.core.domain.interactor.SplashInteractorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideSplashIntractor(splashRepository: SplashRepository): SplashInteractor =
        SplashInteractorImpl(splashRepository)
}