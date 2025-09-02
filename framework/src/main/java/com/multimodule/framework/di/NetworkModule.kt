package com.multimodule.framework.di

import com.google.gson.GsonBuilder
import com.google.gson.Strictness
import com.multimodule.commons.utils.BuildValues
import com.multimodule.framework.di.qualifires.Base
import com.multimodule.framework.di.qualifires.Cdn
import com.multimodule.framework.di.qualifires.Identity
import com.multimodule.framework.di.qualifires.RenewToken
import com.multimodule.framework.network.AuthenticationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter.Factory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @Base
    fun provideBaseUrl(): String = BuildValues.baseHostAddress()

    @Provides
    @Singleton
    @Cdn
    fun provideCdnUrl(): String = BuildValues.cdnHostAddress()

    @Provides
    @Singleton
    @Identity
    fun provideIdentity(): String = BuildValues.identityHostAddress()


    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder()
            .setStrictness(Strictness.LENIENT)
            .create()
        return GsonConverterFactory.create(gson)
    }


    @Provides
    @Singleton
    fun provideConverterFactory(): Factory =
        GsonConverterFactory.create(GsonBuilder().create())


    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: AuthenticationInterceptor,
        networkInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val timeout: Long = 30
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1
        return OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .addNetworkInterceptor(networkInterceptor)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .dispatcher(dispatcher = dispatcher).build()
    }


    @Provides
    @Singleton
    @RenewToken
    fun provideRetrofitWithoutAuthentication(
        converterFactory: Factory,
        @Identity url: String,
        networkInterceptor: HttpLoggingInterceptor
    ): Retrofit = Retrofit.Builder()
        .client(
            OkHttpClient.Builder()
                .addNetworkInterceptor(networkInterceptor)
                .build()
        )

        .baseUrl(url)
        .addConverterFactory(converterFactory)
        .build()


    @Provides
    @Singleton
    @Base
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Factory,
        @Base url: String
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(converterFactory)
        .baseUrl(url)
        .build()

    @Provides
    @Singleton
    @Base
    fun provideBaseRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Factory,
        @Base url: String
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(url)
        .addConverterFactory(converterFactory)
        .build()


    @Provides
    @Singleton
    @Cdn
    fun provideCdnRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Factory,
        @Cdn url: String
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(url)
        .addConverterFactory(converterFactory)
        .build()


}