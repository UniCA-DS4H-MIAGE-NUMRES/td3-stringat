# PizzaApp

PizzaApp est une application multiplateforme développée en **Kotlin Multiplatform (KMP)** et **Jetpack Compose Multiplatform**.

---

## Prérequis

- **Gradle 8.9** (utilisé personnellement)
- **JDK 11+**
- **IntelliJ IDEA**
- **Node.js** (pour WASM)

---

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

---

## Structure du projet

- **commonMain/** : Code partagé
- **androidMain/** : Android
- **desktopMain/** : Desktop
- **wasmJsMain/** : Web

---

## Technologies utilisées

- **Kotlin Multiplatform (KMP)**
- **Jetpack Compose**
- **SQLDelight** (Désactivé temporairement sur Android)
- **Koin** (Injection de dépendances)


---

## Author

- **Théo STRINGAT**
- **Sawsen EL BAHRI**

---

## Lien de la vidéo

https://drive.google.com/file/d/1jH6ED5m1kGNV7RwMjJ53Va8bMCEQUYPC/view?usp=sharing


---

## Difficultés rencontrées et solutions apportées

Durant le développement de cette application **Compose Multiplatform**, j’ai pu rencontrer plusieurs difficultés.



### 1. Problèmes avec Koin (Injection de dépendances)

Problème :

- L'erreur `Koin has not been started` apparaissait lors de la tentative d'accès à une dépendance injectée (`OrderStorage`).
- Impossible d'injecter correctement `OrderStorage` sur **Android**.

Solution :

- Ajout de l'initialisation de **Koin** dans `PizzaApp.kt` (Android) :
  ```kotlin
  class PizzaApp : Application() {
      override fun onCreate() {
          super.onCreate()

          val koin = startKoin {
              androidContext(this@PizzaApp)
              modules(appModule)
          }.koin

          val orderStorage: OrderStorage = koin.get()
      }
  }
  ```
- Vérification du module Koin (`appModule.kt`) :
  ```kotlin
  val appModule = module {
      single { (context: Any?) -> OrderStorage(context) }
  }
  ```



### 2. Gestion du routage et navigation entre les écrans

Problème :

- Lors de la navigation entre les écrans (`WelcomeScreen`, `PizzaMenu`, `CartScreen`), l'état de l'application ne se mettait pas à jour correctement.
- L'affichage de l’écran de détail (`DetailPizza`) ne prenait pas en compte l’ID de la pizza.

Solution :

- Utilisation d’une **MutableState** pour gérer l’état de navigation :
  ```kotlin
  var currentScreen = remember { mutableStateOf("welcome") }
  ```
- Passage des paramètres à l'écran de détail :
  ```kotlin
  "detail/{pizzaId}" -> {
      val pizzaId = it.arguments?.getString("pizzaId")?.toIntOrNull()
      if (pizzaId != null) {
          DetailPizza(pizzaViewModel.getPizzaById(pizzaId), currentScreen, cartViewModel)
      }
  }
  ```



### 3. Erreurs liées aux ressources (et autres images)

Problème :

- Le fichier `logo.png` n'était pas trouvé malgré sa présence dans `src/commonMain/resources`.
- IntelliJ affichait des erreurs en rouge sur l'importation des images (`painterResource`).

Solution :

- Vérification de l'utilisation de **Compose Multiplatform Resources** :
  ```kotlin
  import org.jetbrains.compose.resources.painterResource
  ```
- Ajout d'une fonction multiplateforme pour charger les images :
  ```kotlin
  @Composable
  actual fun getImageResource(path: String): Painter {
      val resId = getDrawableResourceId(path)
      return painterResource(id = resId)
  }
  ```



### **Conclusion**

Ces difficultés ont permis de mieux comprendre les défis du développement **Kotlin Multiplatform**, notamment :\
\- L'initialisation correcte de **Koin** pour l’injection de dépendances.\
\- La gestion de la navigation et du passage de paramètres dans **Compose Multiplatform**.

Grâce à ces correctifs, le projet fonctionne sur Android, Desktop pour le moment.

