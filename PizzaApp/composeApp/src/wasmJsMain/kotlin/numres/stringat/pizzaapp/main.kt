package numres.stringat.pizzaapp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document


fun main() {
    val repository = LocalStorageOrderRepository() // LocalStorage
    ComposeViewport(document.body!!) {
        MyApp(orderViewModel = OrderViewModel(repository))
    }
}