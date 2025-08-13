package com.multimodule.framework.datasourceimpl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.multimodule.core.data.datasource.DataStoreDataSource
import javax.inject.Inject


class DataStoreDataSourceImpl @Inject constructor(val dataStore: DataStore<Preferences>) :
    DataStoreDataSource