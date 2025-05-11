package com.example.practice5.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practice5.InstaAppBar
import com.example.practice5.R

@Composable
fun StoryScreen(
    profile: Profile,
    onBack: () -> Unit
){
    Scaffold(
        topBar = {
            InstaAppBar(canNavigateBack = true, navigateUp = onBack, title = null)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).padding((16.dp))
        ){
            Text("@${profile.id}")
            Spacer(modifier = Modifier.height(16.dp))
            Text("${profile.id}의 스토리")
            Image(
                painter = painterResource(id = profile.image),
                contentDescription = profile.id,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview
@Composable
fun StoryPreview() {
    Practice5Theme {
        StoryScreen(
            profile = Profile("saida", R.drawable.saida),
            onBack = {}
        )
    }
}