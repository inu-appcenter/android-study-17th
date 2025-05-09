package com.example.practice4.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practice4.R
import com.example.practice4.ui.theme.formatTime

@Composable
fun MusicPlayerScreen(
    viewModel: MusicPlayerViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = uiState.mediaInfo.albumArt),
            contentDescription = "앨범 사진",
            modifier = Modifier
                .width(390.dp)
                .height(420.dp)
                .align(Alignment.TopCenter)
                .padding(top = 30.dp)
                .clip(RoundedCornerShape(13.dp)),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(top = 200.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 미디어 정보
            Text(
                text = uiState.mediaInfo.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            )
            Text(
                text = uiState.mediaInfo.artist,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = .6f),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )
            Spacer(Modifier.height(16.dp))

            // 탐색 바
            val sliderPos = uiState.currentTime.toFloat() / uiState.mediaInfo.duration
            var tempPos by remember { mutableStateOf(sliderPos) }
            Slider(
                value                 = if (uiState.playbackState == PlaybackState.Playing || uiState.playbackState == PlaybackState.Paused)
                    sliderPos else tempPos,
                onValueChange         = { tempPos = it },
                onValueChangeFinished = { viewModel.seekTo(tempPos) },
                modifier              = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            // 시간 표시
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(formatTime(uiState.currentTime),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    formatTime(uiState.mediaInfo.duration),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(16.dp))

            // 플레이어 컨트롤 버튼
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment     = Alignment.CenterVertically
            ) {
                IconButton(onClick = { viewModel.playPreviousTrack() }) {
                    Icon(
                        painter = painterResource(R.drawable.back),
                        contentDescription = "이전 곡",
                        modifier = Modifier.size(35.dp)
                    )
                }
                when (uiState.playbackState) {
                    PlaybackState.Playing ->
                        IconButton(
                            onClick ={ viewModel.togglePlayPause() }
                        )   {
                            Icon(
                                painter = painterResource(R.drawable.pause),
                                contentDescription = "일시 정지",
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    PlaybackState.Paused, PlaybackState.Ended -> {
                        IconButton (
                            onClick = { viewModel.togglePlayPause() }
                        )   {
                            Icon(
                                painter = painterResource(R.drawable.play),
                                contentDescription = "재생",
                                modifier = Modifier.size(34.dp)
                            )
                        }
                    }
                    PlaybackState.Loading -> CircularProgressIndicator(
                        modifier = Modifier.size(48.dp))

                    PlaybackState.Error   -> Text(
                        "오류",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(35.dp))
                }
                IconButton(onClick = { viewModel.playNextTrack() }) {
                    Icon(
                        painter = painterResource(R.drawable.next),
                        contentDescription = "다음 곡",
                        modifier = Modifier.size(35.dp)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMediaPlayerScreen() {
    MusicPlayerScreen()
}