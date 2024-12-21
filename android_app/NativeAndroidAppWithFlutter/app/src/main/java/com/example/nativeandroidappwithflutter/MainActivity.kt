package com.example.nativeandroidappwithflutter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nativeandroidappwithflutter.screens.PageOnAndroidScreen
import com.example.nativeandroidappwithflutter.ui.theme.NativeAndroidAppWithFlutterTheme

enum class Screen {
    Main,
    Android,
    Flutter
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NativeAndroidAppWithFlutterTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = Screen.Main.name
                    ) {
                        composable(route = Screen.Main.name) {
                            MainScreen(navController)
                        }
                        composable(route = Screen.Android.name) {
                            PageOnAndroidScreen(naviController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    Column {
        PageOnAndroidButton(navController)
        PageOnFlutterButton(navController)
    }
}

@Composable
fun PageOnAndroidButton(navController: NavController) {
    Button(onClick = { navController.navigate(Screen.Android.name) }) {
        Text("Androidの画面")
    }
}

@Composable
fun PageOnFlutterButton(navController: NavController) {
    Button(onClick = {
        // 遷移処理
    }) {
        Text("Flutterで作成された画面")
    }
}