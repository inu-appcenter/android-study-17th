package com.example.practice2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practice2.ui.theme.Practice2Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practice2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Check(
                        modifier = Modifier.padding(innerPadding)
                    )
                    ScaffoldTest()
                }
            }
        }
    }
}





@Composable
fun Check(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf( 1) }
    val imageResource = when(result) {
        1 -> R.drawable.green
        2 -> R.drawable.red
        3 -> R.drawable.orange
        4 -> R.drawable.blue
        else -> R.drawable.purple
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = "배경"
        )

        Button(
            onClick = { result = (1..5).random() }
        ) { Text("Change Button") }

        Text(
            text = "All tasks completed",
            fontSize = 24.sp,
            fontWeight = FontWeight(700),
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
        )
        Text(
            text = "Nice work!",
            fontSize = 16.sp
        )

       }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldTest() {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "Change Color", fontWeight = FontWeight.Bold, fontSize = 30.sp)
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF87CEEB)
            )
        )
      }
    )  { innerPadding ->
        Check(Modifier.padding(innerPadding))
    }
}


@Preview(showBackground = true)
@Composable
fun CheckPreview() {
    Practice2Theme {
        Check(modifier = Modifier)
    }
}