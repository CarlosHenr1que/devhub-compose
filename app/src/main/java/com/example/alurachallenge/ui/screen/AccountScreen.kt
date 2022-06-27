package com.example.alurachallenge.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.alurachallenge.ui.navigation.Screen

@Composable
fun AccountScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    val toast = Toast(LocalContext.current)

    fun handleEnterPress() {
        if (username.isNotEmpty()) {
            navController.navigate(route = Screen.Profile.route + "/" + username)
        } else {
            toast.setText("This field can't be empty")
            toast.duration = Toast.LENGTH_SHORT
            toast.show()
        }
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "DevHub",
                fontSize = 64.sp,
                modifier = Modifier.paddingFromBaseline(bottom = 64.sp)
            )
            OutlinedTextField(value = username, onValueChange = { username = it }, label = {
                Text(text = "Type your username")
            })
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { handleEnterPress() }, Modifier
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    )
                    .fillMaxWidth()
            ) {
                Text(text = "Enter")
            }
        }
    }
}

