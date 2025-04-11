package com.example.practice2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practice2.ui.theme.Practice2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practice2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun InfoBox(title: String, description: String, backgroundColor: Color){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(backgroundColor)
        .padding(16.dp),
        contentAlignment = Alignment.Center,
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = description
            )
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {

    val colorList = listOf(
        Color(0xFFEADDFF),
        Color(0xFFD0BCFF),
        Color(0xFFB69DF8),
        Color(0xFFF6EDFF)
    )

    val boxColors = remember{
        mutableStateListOf(
            colorList[0],
            colorList[1],
            colorList[2],
            colorList[3]
        )
    }

    Column(modifier = modifier.fillMaxSize()){

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ){
            Button(onClick = {
                for (i in 0..3){
                    boxColors[i]= colorList.random()
                }
            }){
                Text("배경색 변경")
            }
        }

        Row(modifier = Modifier.weight(1f)){
            Box(modifier = Modifier.weight(1f)) {
                InfoBox(
                    title = "Text composable",
                    description = "Displays text and follows Material Design.",
                    backgroundColor = boxColors[0]
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                InfoBox(
                    title = "Image composable",
                    description = "Draws a Painter class object.",
                    backgroundColor = boxColors[1]
                )
            }
        }

        Row(modifier = Modifier.weight(1f)){
            Box(modifier = Modifier.weight(1f)) {
                InfoBox(
                    title = "Row composable",
                    description = "Places children horizontally.",
                    backgroundColor = boxColors[2]
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                InfoBox(
                    title = "Column composable",
                    description = "Places children vertically.",
                    backgroundColor = boxColors[3]
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Practice2Theme {
        Greeting()
    }
}