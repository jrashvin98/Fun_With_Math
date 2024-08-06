package com.example.funwithmath

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = lightColorScheme()
            ) {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    // Limit the number of apples to a maximum of 10
    val maxApples = 10
    var number1 by remember { mutableStateOf(Random.nextInt(1, maxApples + 1)) }
    var number2 by remember { mutableStateOf(Random.nextInt(1, maxApples + 1)) }
    var feedback by remember { mutableStateOf("") }

    // Define a lambda to update feedback
    val updateFeedback: (Boolean) -> Unit = { isCorrect ->
        feedback = if (isCorrect) "Correct Answer!" else "Try Again!"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)  // Set background color to white
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "How many apples in total?", style = MaterialTheme.typography.headlineMedium, color = Color.Black)  // Set text color to black
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(number1) {
                Image(
                    painter = painterResource(id = R.drawable.ic_apple),
                    contentDescription = "Apple",
                    modifier = Modifier.size(40.dp)
                )
            }
            Spacer(modifier = Modifier.width(24.dp))
            repeat(number2) {
                Image(
                    painter = painterResource(id = R.drawable.ic_apple),
                    contentDescription = "Apple",
                    modifier = Modifier.size(40.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { updateFeedback(true) }) {
                Text(text = "${number1 + number2}")
            }
            Button(onClick = { updateFeedback(false) }) {
                Text(text = "${number1 + number2 + Random.nextInt(1, 3)}")
            }
            Button(onClick = { updateFeedback(false) }) {
                Text(text = "${number1 + number2 - Random.nextInt(1, 3)}")
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = feedback, style = MaterialTheme.typography.headlineMedium, color = Color.Black)  // Set text color to black
    }
}
