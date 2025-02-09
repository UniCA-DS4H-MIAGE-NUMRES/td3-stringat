package td.numres.pizzaapp.data

import kotlinx.browser.localStorage
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import td.numres.pizzaapp.model.OrderWithPizzas

actual class OrderStorage actual constructor(private val context: Any?) {

    actual fun saveOrders(orders: List<OrderWithPizzas>) {
        localStorage.setItem("orders", Json.encodeToString(orders))
    }

    actual fun loadOrders(): List<OrderWithPizzas> {
        val data = localStorage.getItem("orders")
        return if (data != null) {
            Json.decodeFromString(data)
        } else {
            emptyList()
        }
    }
}