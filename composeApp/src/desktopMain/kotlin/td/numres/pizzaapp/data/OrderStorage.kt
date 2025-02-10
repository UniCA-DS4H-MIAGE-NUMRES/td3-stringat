package td.numres.pizzaapp.data

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import td.numres.pizzaapp.model.OrderWithPizzas
import java.io.File


actual class OrderStorage actual constructor(context: Any?) {

    private val json = Json { prettyPrint = true }

    actual fun saveOrders(orders: List<OrderWithPizzas>) {
        val jsonString = json.encodeToString(orders)
        File("orders.json").writeText(jsonString)
    }

    actual fun loadOrders(): List<OrderWithPizzas> {
        val jsonString = File("orders.json").readText()
        return json.decodeFromString<List<OrderWithPizzas>>(jsonString)
    }
}