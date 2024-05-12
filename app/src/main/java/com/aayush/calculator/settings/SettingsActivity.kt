package com.aayush.calculator.settings

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import com.aayush.calculator.R
import com.aayush.calculator.ThemeManager
import com.aayush.calculator.databinding.ActivitySettingsThemeBinding
import com.aayush.calculator.navBarHeight
import com.aayush.calculator.restartApp
import com.aayush.calculator.saving.PrefManager
import com.aayush.calculator.saving.PrefName
import com.aayush.calculator.startMainActivity
import com.aayush.calculator.statusBarHeight

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsThemeBinding
    private var reload = PrefManager.getCustomVal("reload", true)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.apply(this)
        val context = this
        binding = ActivitySettingsThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {

            root.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = statusBarHeight
                bottomMargin = navBarHeight
            }

            onBackPressedDispatcher.addCallback(context) {
                if (reload) {
                    PrefManager.setCustomVal("reload", false)
                    startMainActivity(context)
                } else {
                    finish()
                }
            }
            themeSettingsBack.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            var previous: View = when (PrefManager.getVal<Int>(PrefName.DarkMode)) {
                0 -> settingsUiAuto
                1 -> settingsUiLight
                2 -> settingsUiDark
                else -> settingsUiAuto
            }
            previous.alpha = 1f
            fun uiTheme(mode: Int, current: View) {
                previous.alpha = 0.33f
                previous = current
                current.alpha = 1f
                PrefManager.setVal(PrefName.DarkMode, mode)
                restartApp()
            }

            settingsUiAuto.setOnClickListener {
                uiTheme(0, it)
            }

            settingsUiLight.setOnClickListener {
                PrefManager.setVal(PrefName.Amoled, false)
                uiTheme(1, it)
            }

            settingsUiDark.setOnClickListener {
                uiTheme(2, it)
            }
            settingsRecyclerView.adapter = SettingsAdapter(
                arrayListOf(
                    Settings(
                        type = 2,
                        name = getString(R.string.oled_theme_variant),
                        desc = getString(R.string.oled_theme_variant_desc),
                        icon = R.drawable.ic_round_brightness_4_24,
                        isChecked = PrefManager.getVal(PrefName.Amoled),
                        switch = { isChecked, _ ->
                            PrefManager.setVal(PrefName.Amoled, isChecked)
                            reload()
                        }
                    ),
                )
            )
            settingsRecyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
            }
        }
    }

    private fun reload() {
        PrefManager.setCustomVal("reload", true)
        restartApp()
    }
}