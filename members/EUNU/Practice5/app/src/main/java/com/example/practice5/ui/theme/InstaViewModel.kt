package com.example.practice5.ui.theme

import androidx.lifecycle.ViewModel
import com.example.practice5.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InstaViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        StoryUiState(
            profiles = listOf(
                Profile("_eunwooooo", R.drawable.profile),
                Profile("saida", R.drawable.saida),
                Profile("richard", R.drawable.richard),
                Profile("bboyami", R.drawable.bboyami),
                Profile("__dlrmxdn", R.drawable.profile),
                Profile("mati", R.drawable.mati),
                Profile("vanilla", R.drawable.vanilla)
            )
        ))
    val uiState: StateFlow<StoryUiState> = _uiState.asStateFlow()

    fun selectProfile(profile: Profile) {
        _uiState.update { currentState ->
            currentState.copy(selectedProfile = profile)
        }
    }

    fun storyViewed(profileId: String) {
        _uiState.update { currentState ->
            currentState.copy(
                profiles = currentState.profiles.map{
                    if(it.id == profileId) it.copy(readStory = true) else it
                },
                selectedProfile = null
            )
        }
    }
}