package com.example.practice1_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practice1_1.ui.theme.Practice11Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practice11Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GreetingText(
                        title = "Jetpack Compose Tutorial",
                        ui = "Jetpack Compose is a modern toolkit for building native Android UI.",
                        tutorial = "In this tutorial, you build a simple UI component with declarative functions.",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun GreetingText(title: String, ui: String, tutorial: String, modifier: Modifier = Modifier) {
    Column {
        Image(
            painter = painterResource(id = R.drawable.bg_compose_background),
            contentDescription = "뒷배경"
        )
        Text(
            text = title,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = ui,
            modifier = Modifier.padding(horizontal = 16.dp),
            textAlign = TextAlign.Start
        )
        Text(
            text = tutorial,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.End
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Practice11Theme {
        GreetingText("Jetpack Compose Tutorial", "Jetpack Compose is a modern toolkit for building native Android UI.", "In this tutorial, you build a simple UI component with declarative functions.")
    }
}