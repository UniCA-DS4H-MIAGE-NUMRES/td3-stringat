package numres.stringat.pizzaapp

import androidx.compose.runtime.Composable
import numres.stringat.pizzaapp.screens.WelcomeScreen
import numres.stringat.pizzaapp.viewmodel.OrderViewModel

@Composable
fun App(orderViewModel: OrderViewModel) {
    WelcomeScreen(orderViewModel = orderViewModel)
}