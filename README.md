# PizzaApp

PizzaApp est une application multiplateforme développée en **Kotlin Multiplatform (KMP)** et **Jetpack Compose Multiplatform**.

## Prérequis

- **Gradle 8.9** (utilisé personnellement)
- **JDK 11+**
- **IntelliJ IDEA**
- **Node.js** (pour WASM)

## Lancer le projet

### Android

Lancer **MainActivity** depuis Android Studio.

### Desktop (Windows, macOS, Linux)

```sh
./gradlew run
```

### Web (WASM)

```sh
./gradlew wasmJsBrowserRun
```

Puis ouvrir :

```
http://localhost:8080
```

## Structure du projet

- **commonMain/** : Code partagé
- **androidMain/** : Android
- **desktopMain/** : Desktop
- **wasmJsMain/** : Web

## Technologies utilisées

- **Kotlin Multiplatform (KMP)**
- **Jetpack Compose**
- **SQLDelight** (Désactivé temporairement sur Android)
- **Koin** (Injection de dépendances)

## Author

- **Théo STRINGAT**

## Lien de la vidéo

https://drive.google.com/file/d/1jH6ED5m1kGNV7RwMjJ53Va8bMCEQUYPC/view?usp=sharing