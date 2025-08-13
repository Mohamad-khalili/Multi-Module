package com.multimodule.framework.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile


/**
 *This class is a wrapper for connecting with Data Store.
 **/
object DataStoreUtil {


    fun initDataStore(context: Context, name: String): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile(name)
        }
    }

    /**
     * puts a key value in data store if doesn't exists, otherwise updates value on given [key]
     */
    suspend fun <T : Any> DataStore<Preferences>.putValue(key: String, value: T) {
        edit { prefs ->
            when (value) {
                is String? -> prefs[stringPreferencesKey(key)] = value
                is Int? -> prefs[intPreferencesKey(key)] = value
                is Boolean? -> prefs[booleanPreferencesKey(key)] = value
                is Float? -> prefs[floatPreferencesKey(key)] = value
                is Long? -> prefs[longPreferencesKey(key)] = value
                else -> throw UnsupportedOperationException("Not yet implemented")
            }
        }
    }

    /**
     * finds value on given key.
     * [T] is the type of value
     * @param defaultValue optional default value - will take null for strings, false for bool and -1 for numeric values if [defaultValue] is not specified
     */
    inline fun <reified T : Any> Preferences.getValue(
        key: String,
        defaultValue: T? = null
    ): T {
        val preferencesKey = when (T::class) {
            String::class -> stringPreferencesKey(key)
            Boolean::class -> booleanPreferencesKey(key)
            Int::class -> intPreferencesKey(key)
            Long::class -> longPreferencesKey(key)
            Float::class -> floatPreferencesKey(key)
            else -> throw IllegalArgumentException("unsupported type: ${T::class}")
        } as Preferences.Key<*>

        return this[preferencesKey] as T? ?: defaultValue as T
    }

    /**
     * remove all User data
     * **/
    suspend fun DataStore<Preferences>.clearAll() {
        edit { prefs -> prefs.clear() }
    }
}

