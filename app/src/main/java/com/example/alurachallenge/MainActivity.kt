package com.example.alurachallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
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
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val boxHeight = remember {
                            150.dp
                        }

                        val imageHeight = remember {
                            boxHeight
                        }

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .background(
                                    Color.Cyan, shape = RoundedCornerShape(
                                        bottomStart = 16.dp,
                                        bottomEnd = 16.dp
                                    )
                                ).height(boxHeight)
                        ) {
                            Avatar(imageHeight)

                        }
                        Spacer(modifier = Modifier.height(imageHeight / 2))
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
fun Avatar(height: Dp) {
    Image(
        painter = painterResource(id = R.drawable.profile),
        contentDescription = "Avatar",
        modifier = Modifier
            .offset(y = height / 2)
            .size(height)
            .clip(CircleShape),
    )
}

