package com.example.practice5.ui.theme

import androidx.annotation.DrawableRes

data class StoryUiState (
    val profiles: List<Profile> = emptyList(),
    val selectedProfile: Profile? = null
)

data class Profile (
    val id: String,
    @DrawableRes val image: Int,
    val readStory: Boolean = false
)