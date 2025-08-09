package com.multimodule.framework.network

import android.os.Build
import com.multimodule.commons.utils.BuildValues

class UserAgent {
    private val os = "Android;"
    private val version = Build.VERSION.RELEASE + ";"
    private val brand = Build.BRAND + ";"
    private val model = Build.MODEL + ";"
    private val appVersion = BuildValues.versionName()

    fun userAgentDetails(): String {
        return os+version+brand+model+appVersion
    }
}