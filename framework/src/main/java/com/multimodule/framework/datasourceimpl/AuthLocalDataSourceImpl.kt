package com.multimodule.framework.datasourceimpl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.multimodule.commons.constants.DataStoreConstants
import com.multimodule.commons.constants.Tags
import com.multimodule.core.data.datasource.AuthLocalDataSource
import com.multimodule.framework.util.DataStoreUtil.getValue
import com.multimodule.framework.util.DataStoreUtil.putValue
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    AuthLocalDataSource {
    override suspend fun getAccessToken(): String {
        return dataStore.data.first().getValue(DataStoreConstants.ACCESS_TOKEN)
    }


    override fun getRefreshToken(): String {
        return "refreshToken"
//        TODO("Not yet implemented")
    }

    override suspend fun saveAccessToken(token: String) {
        dataStore.putValue(DataStoreConstants.ACCESS_TOKEN, token)
    }

    override fun getAccessExpireTime(): String? {

        return "2026-01-01T13:00:00Z"

//        TODO("Not yet implemented")
    }

    override fun getRefreshExpireTime(): String? {
        return "refreshExpireTime"
//        TODO("Not yet implemented")
    }


    override fun clearUserData() {
//        TODO("Not yet implemented")
    }
}