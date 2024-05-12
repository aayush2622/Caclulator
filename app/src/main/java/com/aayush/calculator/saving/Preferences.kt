package com.aayush.calculator.saving

import com.aayush.calculator.History
import com.aayush.calculator.saving.internal.Location
import com.aayush.calculator.saving.internal.Pref

enum class PrefName(val data: Pref) {
    //General
    Amoled(Pref(Location.General, Boolean::class, false)),
    DarkMode(Pref(Location.General, Int::class, 0)),
}