package com.example.alurachallenge.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.alurachallenge.models.toProfileUiState
import com.example.alurachallenge.retrofit.webclient.GitHubWebClient

@Composable
fun ProfileScreen(username: String, webClient: GitHubWebClient = GitHubWebClient()) {
    val foundUser by webClient.findProfileByUsername(username)
        .collectAsState(initial = null)
    foundUser?.let { userProfile ->
        Profile(state = userProfile.toProfileUiState())
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

@Composable
private fun Profile(state: ProfileUiState) {
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
            Avatar(imageHeight, state.image)
        }
        Spacer(modifier = Modifier.height(imageHeight / 2))
        Text(
            text = state.name,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = state.user)
        Spacer(modifier = Modifier.height(10.dp))
        state.bio?.let {
            Text(text = state.bio)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Profile(
        state = ProfileUiState(
            user = "CarlosHenr1que",
            image = "https://avatars.githubusercontent.com/u/48557266?v=4",
            name = "Carlos henrique",
            bio = "Mobile Software Develoer at Compass UOL"
        )
    )
}

data class ProfileUiState(
    val user: String = "",
    val image: String = "",
    val name: String = "",
    val bio: String = ""
)