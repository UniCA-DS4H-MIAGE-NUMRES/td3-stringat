package td.numres.pizzaapp.data

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import td.numres.pizzaapp.model.OrderWithPizzas
import java.io.File

actual class OrderStorage actual constructor(private val context: Any?) {

    private val file = File("orders.json")

    actual fun saveOrders(orders: List<OrderWithPizzas>) {
        file.writeText(Json.encodeToString(orders))
    }

    actual fun loadOrders(): List<OrderWithPizzas> {
        return if (file.exists()) {
            Json.decodeFromString(file.readText())
        } else {
            emptyList()
        }
    }
}