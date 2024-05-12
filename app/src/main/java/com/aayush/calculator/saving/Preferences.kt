package com.aayush.calculator.saving

import com.aayush.calculator.History
import com.aayush.calculator.saving.internal.Location
import com.aayush.calculator.saving.internal.Pref

enum class PrefName(val data: Pref) {
    //General
    Amoled(Pref(Location.General, Boolean::class, false)),
    HistoryList(Pref(Location.General, List::class, listOf<History>(History("1+1", "2")))),
}