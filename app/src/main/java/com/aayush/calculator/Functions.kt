package com.aayush.calculator

import android.app.Activity
import android.content.ComponentName
import android.content.Intent



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


