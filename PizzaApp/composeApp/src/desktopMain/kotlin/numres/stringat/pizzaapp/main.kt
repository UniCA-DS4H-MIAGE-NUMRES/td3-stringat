package numres.stringat.pizzaapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "PizzaApp",
    ) {
        val database = Database("pizza.db") // SQLDelight pour Desktop
        val repository = SQLDelightOrderRepository(database)
        val viewModel = OrderViewModel(repository)

        App(orderViewModel = viewModel)
    }
}