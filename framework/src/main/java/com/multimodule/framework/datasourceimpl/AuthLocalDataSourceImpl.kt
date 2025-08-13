package com.multimodule.framework.datasourceimpl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.multimodule.core.data.datasource.AuthLocalDataSource
import com.multimodule.framework.util.DataStoreUtil.getValue
import com.multimodule.framework.util.DataStoreUtil.putValue
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    AuthLocalDataSource {
    override suspend fun getAccessToken(): String {
        return dataStore.data.first().getValue("access_token")
    }


    override fun getRefreshToken(): String? {

        TODO("Not yet implemented")
    }

    override suspend fun saveAccessToken(token: String) {
        dataStore.putValue("access_token", token)
    }

    override fun getAccessExpireTime(): String? {

        TODO("Not yet implemented")
    }

    override fun getRefreshExpireTime(): String? {
        TODO("Not yet implemented")
    }


    override fun clearUserData() {
        TODO("Not yet implemented")
    }
}