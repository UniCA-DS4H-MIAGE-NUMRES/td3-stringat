package td.numres.pizzaapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "test_KMP",
    ) {
        App()
    }
}