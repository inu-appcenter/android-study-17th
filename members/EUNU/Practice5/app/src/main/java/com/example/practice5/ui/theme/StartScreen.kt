package com.example.practice5.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.practice5.R

@Composable
fun StartScreen(
    uiState: StoryUiState,
    onProfileClick: (Profile) -> Unit
){
    LazyRow {
        items(uiState.profiles) {profile ->
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable{onProfileClick(profile)}
            ){
                Box(
                    modifier = Modifier
                        .size(96.dp)
                        .clip(CircleShape)
                        .background(
                            if(profile.readStory) Color.Transparent
                            else Color.Magenta
                        )
                        .padding(3.dp)
                ){
                    Image(
                        painter = painterResource(id = profile.image),
                        contentDescription = profile.id,
                        modifier = Modifier
                            .size(96.dp)
                            .clip(CircleShape)
                    )
                }
                Text(
                    text = profile.id,
                    modifier = Modifier.align(androidx.compose.ui.Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Preview
@Composable
fun StartPreview() {
    Practice5Theme {
        StartScreen(
            uiState = StoryUiState(
                profiles = listOf(
                    Profile("_eunwooooo", R.drawable.profile),
                    Profile("saida", R.drawable.saida),
                    Profile("richard", R.drawable.richard),
                    Profile("bboyami", R.drawable.bboyami),
                    Profile("__dlrmxdn", R.drawable.profile),
                    Profile("mati", R.drawable.mati),
                    Profile("vanilla", R.drawable.vanilla)
                )
            ),
            onProfileClick = {}
        )
    }
}