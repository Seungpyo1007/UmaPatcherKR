package com.leadrdrk.umapatcherkr.unity.common

@Suppress("unused")
data class BuildType(
    val value: String
) {
    fun isAlpha() = value == "a"
    fun isPatch() = value == "p"
}