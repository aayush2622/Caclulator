package com.aayush.calculator

import android.app.Activity
import android.os.Build
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aayush.calculator.saving.PrefManager
import com.aayush.calculator.saving.PrefName
import com.google.android.material.color.DynamicColors as MaterialUI
import com.google.android.material.color.DynamicColorsOptions as MaterialUIOptions

object ThemeManager {

    fun apply(context: Activity) {
        setWindowFlag(context.window)
        darkTheme()
        val useAmoled: Boolean = PrefManager.getVal(PrefName.Amoled)

        val theme = MaterialUIOptions.Builder().build()
        MaterialUI.applyToActivityIfAvailable(context, theme)

        if (useAmoled) {
            val themeAmoled = MaterialUIOptions.Builder() // apply on top of the current theme
                .setThemeOverlay(R.style.AppTheme_Amoled)
                .build()
            MaterialUI.applyToActivityIfAvailable(context, themeAmoled)
        }
    }

    private fun setWindowFlag(window: Window) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            @Suppress("DEPRECATION")
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = 0x00000000
        window.navigationBarColor = 0x00000000
        val windowInsets =
            ViewCompat.getRootWindowInsets(window.decorView.findViewById(android.R.id.content))
        if (windowInsets != null) {
            statusBarHeight = windowInsets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            navBarHeight =
                windowInsets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom
        }
    }
    private fun darkTheme(){
        val darkMode = PrefManager.getVal<Int>(PrefName.DarkMode)
        darkMode.apply {
            AppCompatDelegate.setDefaultNightMode(
                when (this) {
                    2 -> AppCompatDelegate.MODE_NIGHT_YES
                    1 -> AppCompatDelegate.MODE_NIGHT_NO
                    else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                }
            )
        }

    }

}