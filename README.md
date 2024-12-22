# Add-to-appでネイティブアプリにFlutterで開発した画面を追加

## 環境
```
Flutter 3.27.1 • channel stable • https://github.com/flutter/flutter.git
Framework • revision 17025dd882 (6 days ago) • 2024-12-17 03:23:09 +0900
Engine • revision cb4b5fff73
Tools • Dart 3.6.0 • DevTools 2.40.2

openjdk 21.0.5 2024-10-15
OpenJDK Runtime Environment Homebrew (build 21.0.5)
OpenJDK 64-Bit Server VM Homebrew (build 21.0.5, mixed mode, sharing)
```

## build
```
/flutter_module

flutter pub get
```

`.android/gradle/wrapper/gradle-wrapper.properties`に変更が入ったら、以下のように修正(`gradle-8.5-bin.zip`)

※ gradleでエラーが起きた場合は、キャッシュクリアや端末再起動を試す
```
distributionUrl=https\://services.gradle.org/distributions/gradle-8.5-bin.zip
```

aar生成
```
flutter build aar
```