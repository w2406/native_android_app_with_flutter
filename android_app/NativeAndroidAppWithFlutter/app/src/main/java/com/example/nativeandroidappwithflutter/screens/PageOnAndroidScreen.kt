package com.example.nativeandroidappwithflutter.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.nativeandroidappwithflutter.Screen

@Composable
fun PageOnAndroidScreen(naviController: NavController) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = "ネイティブ(Kotlin)で作られた画面",
            )
            BackButton(
                naviController = naviController
            )
        }

    }
}

@Composable
fun BackButton(naviController: NavController) {
    Button(
        onClick = { naviController.navigate(Screen.Main.name) }
    ) {
        Text(text = "戻る")
    }
}