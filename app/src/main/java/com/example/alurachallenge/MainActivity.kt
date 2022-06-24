package com.example.alurachallenge

import android.os.Bundle
import android.util.Log
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
import androidx.lifecycle.lifecycleScope
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.alurachallenge.models.Profile
import com.example.alurachallenge.retrofit.RetrofitConfig
import com.example.alurachallenge.ui.theme.AluraChallengeTheme
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getProfile()
        setContent {
            ProfileScreen()
        }
    }

    @Composable
    private fun ProfileScreen() {
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
                            )
                            .height(boxHeight)
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

    private fun getProfile() {
        lifecycleScope.launch {
            val call = RetrofitConfig().profileService().list("CarlosHenr1que")
            call.enqueue(object : Callback<Profile?> {
                override fun onResponse(call: Call<Profile?>, response: Response<Profile?>) {
                    response.body()?.let { Log.d("response", it.name) }
                }

                override fun onFailure(call: Call<Profile?>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}

@Composable
fun Avatar(height: Dp) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://avatars.githubusercontent.com/u/48557266?v=4")
            .crossfade(true)
            .build(),
        contentDescription = "Avatar",
        modifier = Modifier
            .offset(y = height / 2)
            .size(height)
            .clip(CircleShape),
    )
}

