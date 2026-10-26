
package com.example.slideshowproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.slideshowproject.ui.theme.SlideshowProjectTheme
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SlideshowProjectTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    AppLayout(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AppLayout(modifier: Modifier = Modifier) {
    var imageInput by remember { mutableStateOf("") }

    val images = listOf(
        R.drawable.img1,
        R.drawable.img2,
        R.drawable.img3,
        R.drawable.img4,
        R.drawable.img5,
        R.drawable.img6
    )

    var currentImage by remember {
        mutableIntStateOf(0)
    }

    val caption = when (currentImage) {
        0 -> "IMG1: New York City"
        1 -> "IMG2: Los Angeles"
        2 -> "IMG3: Las Vegas"
        3 -> "IMG4: Miami"
        4 -> "IMG5: Chicago"
        5 -> "IMG6: Atlantic City"
        else -> "Unknown Image"
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Slideshow Project",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )

        Image(
            painter = painterResource(images[currentImage]),
            contentDescription = "Slideshow image",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = caption,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )

        Button(
            onClick = {
                if (currentImage < 5) {
                    currentImage++
                } else {
                    currentImage = 0
                }
            }
        ) {
            Text("Next")
        }
        Button(
            onClick = {
                if (currentImage > 0) {
                    currentImage--
                } else {
                    currentImage=5
                }
            }
        ) {
            Text("Back")
        }
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        TextField(
            value = imageInput,
            onValueChange = { imageInput = it },
            label = { Text("Enter image number (1-6)") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        Button(
            onClick = {
                val number = imageInput.toIntOrNull()

                if (number != null && number in 1..6) {
                    currentImage = number - 1
                }
            }
        ) {
            Text("Go")
        }

    }
}
