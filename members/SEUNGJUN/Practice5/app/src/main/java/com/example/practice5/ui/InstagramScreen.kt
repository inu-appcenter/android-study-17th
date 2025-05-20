package com.example.practice5.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.practice5.R
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController


@Composable
fun InstagramAppbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(33.dp)
            .padding(horizontal = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Instagram",
            fontSize = 29.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = Color.Black
        )


        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            Image(
                painter = painterResource(id = R.drawable.heart),
                contentDescription = "하트",
                modifier = Modifier
                    .size(50.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.send),
                contentDescription = "전송",
                modifier = Modifier
                    .size(50.dp)
            )
        }
    }
}


@Composable
fun InstagramApp(
    viewModel: InstagramViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            InstagramAppbar()
        },
        bottomBar = {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.bar),
                    contentDescription = "하단 바",
                    modifier = Modifier
                        .width(1500.dp)

                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "main",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable("main") {
                InstagramScreen(viewModel = viewModel,
                    navController = navController,
                    modifier = Modifier
                        .padding(innerPadding)
                )
            }
            composable("story/{userId}") { backStackEntry ->
                val userId = backStackEntry.arguments?.getString("userId") ?: ""
                StoryScreen(viewModel = viewModel,
                    navController = navController,
                    userId = userId)
            }
        }
    }
}




@Composable
fun StoryItem(story: Story, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(90.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val ring =
            if (story.view_check) {
                Modifier.size(90.dp)
        } else {
            Modifier
                .size(90.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFFF5722),
                            Color(0xFFFF9800),
                            Color(0xFFE91E63),
                            Color(0xFF9C27B0)
                        )
                    ),
                    shape = CircleShape
                )
        }

        Box(
            modifier = ring,
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = story.profile),
                contentDescription = "${story.username}의 프로필",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = story.username,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun InstagramScreen(
    viewModel: InstagramViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(
            items = uiState.stories,
            key = { story -> story.userId }
        ) { story ->
            StoryItem(
                story = story,
                onClick = {
                    viewModel.markStoryAsViewed(story.userId)
                    navController.navigate("story/${story.userId}")
                }
            )
        }
    }
}

