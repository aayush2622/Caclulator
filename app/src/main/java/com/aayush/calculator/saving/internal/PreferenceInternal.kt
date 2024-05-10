package com.aayush.calculator.saving.internal

import kotlin.reflect.KClass


data class Pref(
    val prefLocation: Location,
    val type: KClass<*>,
    val default: Any
)

enum class Location(val location: String, val exportable: Boolean) {
    General("com.aayush262.calculator.general", true),
    Protected("com.aayush262.calculator.protected", true),
    Irrelevant("com.aayush262.calculator.irrelevant", false)
}
