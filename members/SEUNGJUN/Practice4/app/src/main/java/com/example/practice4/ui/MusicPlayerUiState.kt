package com.example.practice4.ui

import androidx.annotation.DrawableRes
import com.example.practice4.R

// 재생 상태
enum class PlaybackState {
    Playing, Paused, Loading, Error, Ended
}

// 음악 정보
data class MediaInfo(
    val title: String,
    val artist: String,
    val duration: Long,
    @DrawableRes val albumArt: Int
)

data class MusicPlayerUiState(
    val playbackState: PlaybackState = PlaybackState.Paused,
    val currentTime:    Long          = 0L,
    val mediaInfo:      MediaInfo     = MediaInfo(
        "Happy",
        "Day6",
        180_000L,
        R.drawable.day6)
)