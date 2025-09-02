package com.multimodule.framework.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.multimodule.commons.constants.DataStoreConstants
import com.multimodule.core.data.datasource.AuthLocalDataSource
import com.multimodule.core.data.datasource.DataStoreDataSource
import com.multimodule.framework.datasourceimpl.AuthLocalDataSourceImpl
import com.multimodule.framework.datasourceimpl.DataStoreDataSourceImpl
import com.multimodule.framework.util.DataStoreUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStoreInitial(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> {
        return DataStoreUtil.initDataStore(context, DataStoreConstants.APP_PREFS)
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStoreDataSource {
        val dataStore = DataStoreUtil.initDataStore(context, DataStoreConstants.APP_PREFS)
        return DataStoreDataSourceImpl(dataStore)
    }

    @Provides
    @Singleton
    fun provideAuth(dataStore: DataStore<Preferences>): AuthLocalDataSource {
        return AuthLocalDataSourceImpl(dataStore)
    }
}