package com.example.alurachallenge.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alurachallenge.models.Repository

@Composable
fun RepositoryCard(repo: Repository) {
    Card(
        elevation = 12.dp,
        backgroundColor = Color(0xFF24292E),
        modifier = Modifier.padding(16.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                repo.name,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 18.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))
            if (repo.description.isNotBlank()) {
                Text(
                    repo.description,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.width(10.dp))
            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Outlined.Star, contentDescription = "star icon", tint = Color.Yellow)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = repo.stars, color = Color.White)
                Spacer(modifier = Modifier.width(10.dp))
                Icon(Icons.Outlined.Build, contentDescription = "star icon", tint = Color.White)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = repo.language, color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepositoryItemPreview() {
    RepositoryCard(
        repo = Repository(
            name = "facebook",
            description = "facebook repository",
            stars = "32",
            language = "TypeScript"
        )
    )
}