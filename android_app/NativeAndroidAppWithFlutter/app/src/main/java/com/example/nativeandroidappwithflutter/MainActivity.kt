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
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nativeandroidappwithflutter.screens.PageOnAndroidScreen
import com.example.nativeandroidappwithflutter.ui.theme.NativeAndroidAppWithFlutterTheme
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

enum class Screen {
    Main,
    Android,
    Flutter
}

// FlutterEngineのキャッシュキー
const val flutterEngineId = "flutter_engine_id"

class MainActivity : ComponentActivity() {
    private lateinit var flutterEngine: FlutterEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 毎回FlutterEngineが作られることで、リソースが無駄・起動が遅くになるのでFlutterEngineをキャッシュに保持
        // Flutterエンジンを事前にインスタンス化
        flutterEngine = FlutterEngine(this)

        // FlutterEngineに初期ルートを設定
        flutterEngine.navigationChannel.setInitialRoute("/");

        // FlutterEngineの初期化
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        // FlutterEngineを保持して使いまわせるようにする
        FlutterEngineCache
            .getInstance()
            .put(flutterEngineId, flutterEngine)

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
        PageOnFlutterButton()
    }
}

@Composable
fun PageOnAndroidButton(navController: NavController) {
    Button(onClick = { navController.navigate(Screen.Android.name) }) {
        Text("Androidの画面")
    }
}

@Composable
fun PageOnFlutterButton() {
    val context = LocalContext.current
    Button(onClick = {
        // 遷移処理
        context.startActivity(
            FlutterActivity
                .withCachedEngine(flutterEngineId)
                .build(context)
        )
    }) {
        Text("Flutterで作成された画面")
    }
}