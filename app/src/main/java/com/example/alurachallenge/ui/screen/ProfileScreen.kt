package com.example.alurachallenge.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.alurachallenge.models.Repository
import com.example.alurachallenge.retrofit.webclient.GitHubWebClient
import com.example.alurachallenge.ui.components.RepositoryCard

@Composable
fun ProfileScreen(username: String, webClient: GitHubWebClient = GitHubWebClient()) {
    val uiState = webClient.uiState
    LaunchedEffect(null) {
        webClient.findProfileByUsername(username)
    }
    Profile(state = uiState)
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
    LazyColumn {
        item {
            ProfileHeader(state)
        }
        item {
            if (state.repositories.isNotEmpty()) {
                Text(
                    text = "Repositories", Modifier.padding(8.dp),
                    fontSize = 24.sp
                )
            }
        }
        items(state.repositories) {
            RepositoryCard(repo = it)
        }
    }
}

@Composable
private fun ProfileHeader(state: ProfileUiState) {
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

@Preview(showBackground = true)
@Composable
fun ProfileWithRepositoriesPreview() {
    Profile(
        state = ProfileUiState(
            user = "CarlosHenr1que",
            image = "https://avatars.githubusercontent.com/u/48557266?v=4",
            name = "Carlos henrique",
            bio = "Mobile Software Develoer at Compass UOL",
            repositories = listOf(
                Repository(
                    name = "github-compose"
                ),
                Repository(
                    name = "ceep-compose",
                    description = "Sample project to practice the Jetpack Compose Apps"
                ),
                Repository(
                    name = "orgs-jetpack-compose",
                    description = "Projeto de simulação do e-commerce de produtos naturais com a finalidade de treinar o Jetpack Compose"
                )
            )
        )
    )
}

data class ProfileUiState(
    val user: String = "",
    val image: String = "",
    val name: String = "",
    val bio: String = "",
    val repositories: List<Repository> = emptyList()
)