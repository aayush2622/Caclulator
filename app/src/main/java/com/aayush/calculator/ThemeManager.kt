package com.aayush.calculator

import android.app.Activity
import android.os.Build
import android.view.WindowManager
import com.aayush.calculator.saving.PrefManager
import com.aayush.calculator.saving.PrefName
import com.google.android.material.color.DynamicColors as MaterialUI
import com.google.android.material.color.DynamicColorsOptions as MaterialUIOptions

object ThemeManager {

    fun apply(context: Activity) {
        val window = context.window
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            @Suppress("DEPRECATION")
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = 0x00000000

        val useAmoled: Boolean = true

        val theme = MaterialUIOptions.Builder()
            .setThemeOverlay(if (useAmoled) R.style.AppTheme_Amoled else 0)
            .build()

        MaterialUI.applyToActivityIfAvailable(context, theme)
    }
}