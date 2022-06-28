package com.example.alurachallenge.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
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
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF24292E)
    ) {
        Profile(state = uiState)
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
                    text = "Repositories",
                    Modifier.padding(8.dp),
                    color = Color.White,
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
   Card( elevation = 12.dp,
       backgroundColor = Color(0xFF24292E),
       modifier = Modifier.padding(16.dp)) {
       Row(
           modifier = Modifier
               .fillMaxWidth()
               .height(180.dp)
               .padding(16.dp),
           horizontalArrangement = Arrangement.SpaceBetween,
           verticalAlignment = Alignment.CenterVertically


       ) {
           val boxHeight = remember {
               100.dp
           }

           val imageHeight = remember {
               boxHeight
           }

           Box(
               modifier = Modifier
                   .height(180.dp)
                   .height(boxHeight),
               contentAlignment = Alignment.Center

           ) {
               Avatar(imageHeight, state.image)
           }
           Spacer(modifier = Modifier.width(10.dp))
           Column {
               Text(
                   text = state.name,
                   color = Color.White,
                   fontSize = 22.sp,
                   fontWeight = FontWeight.Bold
               )
               Text(text = state.user, color = Color.White,    fontSize = 10.sp)
               Spacer(modifier = Modifier.height(10.dp))
               state.bio?.let {
                   Text(text = state.bio, color = Color.White, fontSize = 14.sp)
               }
           }
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
                    name = "github-compose",
                    stars = "32",
                    language = "TypeScript"
                ),
                Repository(
                    name = "ceep-compose",
                    description = "Sample project to practice the Jetpack Compose Apps",
                    stars = "500",
                    language = "Kotlin"
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