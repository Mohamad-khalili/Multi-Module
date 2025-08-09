package com.multimodule.commons.utils

import com.multimodule.commons.BuildConfig

object BuildValues {
    fun cdnHostAddress() : String = BuildConfig.CDN_HOST
    fun versionName(): String = BuildConfig.VERSION_NAME
    fun apiHostAddress(): String = BuildConfig.CDN_HOST
}