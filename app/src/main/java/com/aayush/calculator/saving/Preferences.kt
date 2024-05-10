package com.aayush.calculator.saving

import com.aayush.calculator.History
import com.aayush.calculator.saving.internal.Location
import com.aayush.calculator.saving.internal.Pref

enum class PrefName(val data: Pref) {
    //General
    OLED(Pref(Location.General, Boolean::class, false)),
    History(Pref(Location.General, Set::class, setOf<com.aayush.calculator.History>())),
}