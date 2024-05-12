package com.aayush.calculator

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.aayush.calculator.saving.PrefManager
import com.aayush.calculator.saving.PrefName

var navBarHeight = 0
var statusBarHeight = 0
fun Activity.restartApp() {
    val mainIntent = Intent.makeRestartActivityTask(
        packageManager.getLaunchIntentForPackage(this.packageName)!!.component
    )
    val component =
        ComponentName(this@restartApp.packageName, this@restartApp::class.qualifiedName!!)
    try {
        startActivity(Intent().setComponent(component))
    } catch (e: Exception) {
        startActivity(mainIntent)
    }
    finishAndRemoveTask()
}
fun startMainActivity(activity: Activity, bundle: Bundle? = null) {
    activity.finishAffinity()
    activity.startActivity(
        Intent(
            activity,
            MainActivity::class.java
        ).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            if (bundle != null) putExtras(bundle)
        }
    )
}

