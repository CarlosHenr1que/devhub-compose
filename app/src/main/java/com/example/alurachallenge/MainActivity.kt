package com.example.alurachallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.alurachallenge.retrofit.webclient.GitHubWebClient
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
                    ProfileScreen("CarlosHenr1que")
                }
            }

        }
    }

    @Composable
    private fun ProfileScreen(username: String, webClient: GitHubWebClient = GitHubWebClient()) {
        val foundUser by webClient.findProfileByUsername(username)
            .collectAsState(initial = null)
        foundUser?.let { userProfile ->
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
                        )
                        .height(boxHeight)
                ) {
                    Avatar(imageHeight, userProfile.avatar)
                }
                Spacer(modifier = Modifier.height(imageHeight / 2))
                Text(
                    text = userProfile.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = userProfile.login)
                Spacer(modifier = Modifier.height(10.dp))
                userProfile.bio?.let {
                    Text(text = userProfile.bio)
                }
            }
        }
    }
}

@Composable
fun Avatar(height: Dp, url: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = "Avatar",
        modifier = Modifier
            .offset(y = height / 2)
            .size(height)
            .clip(CircleShape),
    )
}

