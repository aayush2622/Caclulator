package com.aayush.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aayush.calculator.databinding.ActivityMainBinding
import com.aayush.calculator.saving.PrefManager

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PrefManager.init(this)
        ThemeManager.apply(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

