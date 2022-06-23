package com.example.alurachallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alurachallenge.ui.theme.AluraChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluraChallengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Avatar()
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Carlos Henrique Matos Borges",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "CarlosHenr1que")
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Mobile Software Developer")
                        Text(text = "at Compass")
                    }
                }
            }
        }
    }
}

@Composable
fun Avatar() {
    Image(
        painter = painterResource(id = R.drawable.profile),
        contentDescription = "Avatar",
        modifier = Modifier.clip(RoundedCornerShape(10.dp))
    )
}

