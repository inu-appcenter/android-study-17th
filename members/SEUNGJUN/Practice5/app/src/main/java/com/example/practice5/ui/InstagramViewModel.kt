package com.example.practice5.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.practice5.R

class InstagramViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(InstagramUiState())
    val uiState: StateFlow<InstagramUiState> = _uiState.asStateFlow()

    init {
        loadStories()
    }

    private fun loadStories(){
        val storylist = listOf(
            Story("Zzangu", "Zzangu", R.drawable.zzangu, false),
            Story("Zzanga", "Zzanga", R.drawable.zzanga, true),
            Story("Chulsu", "Chulsu", R.drawable.chulsu, true),
            Story("Yuri", "Yuri", R.drawable.yuri, true),
            Story("Hooni", "Hooni", R.drawable.hooni, true),
            Story("Maenggu", "Maenggu", R.drawable.maenggu, true),


        )
        _uiState.value = _uiState.value.copy(stories = storylist)
    }

    fun markStoryAsViewed(userId: String) {
        val updatedStories = _uiState.value.stories.map { story ->
            if (story.userId == userId) story.copy(view_check = true)
            else story
        }
        _uiState.value = _uiState.value.copy(stories = updatedStories)
    }
}


