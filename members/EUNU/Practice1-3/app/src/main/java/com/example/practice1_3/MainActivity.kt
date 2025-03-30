package com.example.practice1_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practice1_3.ui.theme.Practice13Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practice13Theme {
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
fun Greeting(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()){
        Row(modifier = Modifier.weight(1f)){
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEADDFF))
                .padding(16.dp)
                .weight(1f),
                contentAlignment = Alignment.Center,
            ){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Text composable",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Text(
                        text = "Displays text and follows the recommended Material Design guidelines."
                    )
                }
            }
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFD0BCFF))
                .padding(16.dp)
                .weight(1f),
                contentAlignment = Alignment.Center
            ){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Image composable",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Text(
                        text = "Creates a composable that lays out and draws a given Painter class object."
                    )
                }
            }
        }
        Row(modifier = Modifier.weight(1f)){
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFB69DF8))
                .padding(16.dp)
                .weight(1f),
                contentAlignment = Alignment.Center
            ){
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Text(
                        text = "Row composable",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Text(
                        text = "A layout composable that places its children in a horizontal sequence."
                    )
                }
            }
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6EDFF))
                .padding(16.dp)
                .weight(1f),
                contentAlignment = Alignment.Center
            ){
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Text(
                        text = "Column composable",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Text(
                        text = "A layout composable that places its children in a vertical sequence."
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Practice13Theme {
        Greeting()
    }
}