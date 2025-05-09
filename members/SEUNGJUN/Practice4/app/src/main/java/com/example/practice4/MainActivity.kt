package com.example.practice4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.practice4.ui.MusicPlayerScreen
import com.example.practice4.ui.theme.Practice4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practice4Theme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                    ) {
                    MusicPlayerScreen()
                }
            }
        }
    }
}
