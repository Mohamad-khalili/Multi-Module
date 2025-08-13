package com.multimodule.commons.utils

import com.multimodule.commons.BuildConfig

object BuildValues {
    fun versionName(): String = BuildConfig.VERSION_NAME
    fun cdnHostAddress() : String = BuildConfig.CDN_HOST
    fun baseHostAddress(): String = BuildConfig.BASE_HOST
    fun identityHostAddress(): String = BuildConfig.IDENTITY_HOST
}