package com.example.practice5.ui

data class InstagramUiState(
    val stories: List<Story> = emptyList()
)

data class Story(
    val userId: String,
    val username: String,
    val profile: Int,
    val newStory: Boolean,
    val view_check: Boolean = false
)