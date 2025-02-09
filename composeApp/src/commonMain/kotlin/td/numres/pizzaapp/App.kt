package td.numres.pizzaapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform.getKoinOrNull
import td.numres.pizzaapp.di.initKoin
import td.numres.pizzaapp.screens.*
import td.numres.pizzaapp.viewmodel.CartViewModel
import td.numres.pizzaapp.viewmodel.OrderViewModel
import td.numres.pizzaapp.viewmodel.PizzaViewModel
import test_kmp.composeapp.generated.resources.Res
import test_kmp.composeapp.generated.resources.compose_multiplatform

@Composable
fun App() {
    LaunchedEffect(Unit) {
        if (getKoinOrNull() == null) {
            initKoin()
        }
    }


    MaterialTheme {
        var currentScreen = remember { mutableStateOf("welcome") }
        val pizzaViewModel = remember { PizzaViewModel() }
        val cartViewModel = remember { CartViewModel() }
        val orderViewModel = remember { OrderViewModel() }

        when (val screen = currentScreen.value) {
            "welcome" -> WelcomeScreen(currentScreen)
            "menu" -> PizzaMenu(pizzaViewModel, currentScreen)
            "cart" -> CartScreen(cartViewModel, orderViewModel, currentScreen, modifier = Modifier)
            "orderHistory" -> OrderHistoryScreen(currentScreen, orderViewModel)
            else -> {
                if (screen.startsWith("detail/")) {
                    val pizzaId = screen.removePrefix("detail/").toIntOrNull()
                    val pizza = pizzaViewModel.getPizzaById(pizzaId ?: -1)

                    if (pizza != null) {
                        DetailPizza(pizza, currentScreen, cartViewModel)
                    } else {
                        Text("Pizza introuvable", modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}