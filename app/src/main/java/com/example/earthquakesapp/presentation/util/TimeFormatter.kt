package com.example.earthquakesapp.presentation.util

fun formatRelativeTime(epochMillis: Long): String {
    val now = System.currentTimeMillis()
    val diff = now - epochMillis

    val minutes = diff / 60_000
    val hours = diff / 3_600_000
    val days = diff / 86_400_000

    return when {
        minutes < 1 -> "hace un momento"
        minutes < 60 -> "hace ${minutes} min"
        hours < 24 -> "hace ${hours} h"
        days < 30 -> "hace ${days} d"
        else -> "hace más de un mes"
    }
}