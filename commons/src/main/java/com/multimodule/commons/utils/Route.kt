package com.multimodule.commons.utils

import android.app.Activity
import android.content.Intent
import com.multimodule.commons.constants.Tags

object Route {
    const val AUTH_ACTIVITY = "com.multimodule.authentication.AuthActivity"


    fun launch(activity: Activity, className: String) {
        val intent = Intent()
        intent.setClassName(activity, className)
        activity.startActivity(intent)
    }

    fun launchWithFinish(activity: Activity, className: String) {
        val intent = Intent()
        intent.setClassName(activity, className)
        activity.startActivity(intent)
        activity.finish()
    }


    fun setupIntent(activity: Activity, className: String, isReturn: Boolean): Intent {
        val intent = Intent()
        intent.setClassName(activity, className)
        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
        intent.putExtra(Tags.IS_RETURN, isReturn)
        return intent
    }
}