package com.example.practice4.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice4.R
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class MusicPlayerViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MusicPlayerUiState())
    val uiState: StateFlow<MusicPlayerUiState> = _uiState.asStateFlow()

    private var playbackJob: Job? = null

    fun togglePlayPause() {
        val s = _uiState.value
        if (s.playbackState == PlaybackState.Playing) {
            stopSimulation()
            _uiState.update { it.copy(playbackState = PlaybackState.Paused) }
        } else {
            _uiState.update { it.copy(playbackState = PlaybackState.Loading) }
            viewModelScope.launch {
                delay(500)
                _uiState.update { it.copy(playbackState = PlaybackState.Playing) }
                startSimulation()
            }
        }
    }

    fun playNextTrack() = viewModelScope.launch {
        stopSimulation()
        _uiState.update { it.copy(playbackState = PlaybackState.Loading, currentTime = 0L) }
        delay(1000)
        _uiState.update {
            it.copy(
                playbackState = PlaybackState.Playing,
                mediaInfo     = MediaInfo("내 이름 맑음","QWER",240_000L,R.drawable.qwer)
            )
        }
        startSimulation()
    }

    fun playPreviousTrack() = viewModelScope.launch {
        stopSimulation()
        _uiState.update { it.copy(playbackState = PlaybackState.Loading, currentTime = 0L) }
        delay(1000)
        _uiState.update {
            it.copy(
                playbackState = PlaybackState.Playing,
                mediaInfo     = MediaInfo("Welcome to the Show","Day6",180_000L,R.drawable.day6)
            )
        }
        startSimulation()
    }

    fun seekTo(position: Float) {
        val duration = _uiState.value.mediaInfo.duration
        val newTime  = (position * duration).toLong()
        _uiState.update { it.copy(currentTime = newTime) }
        if (_uiState.value.playbackState == PlaybackState.Playing) {
            stopSimulation()
            startSimulation()
        }
    }

    private fun startSimulation() {
        playbackJob?.cancel()
        playbackJob = viewModelScope.launch {
            while (_uiState.value.currentTime < _uiState.value.mediaInfo.duration
                && _uiState.value.playbackState == PlaybackState.Playing) {
                delay(1000)
                _uiState.update { st -> st.copy(currentTime = st.currentTime + 1000) }
            }
            if (_uiState.value.currentTime >= _uiState.value.mediaInfo.duration) {
                _uiState.update {
                    it.copy(playbackState = PlaybackState.Ended,
                        currentTime   = it.mediaInfo.duration)
                }
            }
        }
    }

    private fun stopSimulation() {
        playbackJob?.cancel()
    }
}