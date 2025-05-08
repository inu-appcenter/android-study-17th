package com.example.practice4.ui.theme

import java.util.concurrent.TimeUnit

fun formatTime(milliseconds: Long): String {
    val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
    val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds) % 60
    return String.format("%02d:%02d", minutes, seconds)
}
