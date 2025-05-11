package com.example.practice5

import android.R.attr.navigationIcon
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsProperties.ContentDescription
import com.example.practice5.ui.theme.InstaViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.practice5.ui.theme.StartScreen
import com.example.practice5.ui.theme.StoryScreen
import androidx.compose.runtime.getValue

enum class InstaScreen(){
    Start,
    Story
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstaAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    title: String? = null,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = { title?.let { Text(it) } },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}

@Composable
fun InstaApp(
    viewModel: InstaViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    Scaffold(
        topBar = {
            InstaAppBar(
                canNavigateBack = false,
                navigateUp = {},
                title = "Instagram"
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = InstaScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = InstaScreen.Start.name){
                StartScreen(
                    uiState = uiState,
                    onProfileClick = {
                        viewModel.selectProfile(it)
                        navController.navigate(InstaScreen.Story.name)
                    }
                )
            }
            composable(route = InstaScreen.Story.name){
                uiState.selectedProfile?.let { profile ->
                    StoryScreen(
                        profile = profile,
                        onBack = {
                            viewModel.storyViewed(profile.id)
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}

