package td.numres.pizzaapp.data

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import td.numres.pizzaapp.model.OrderWithPizzas
import java.io.File


actual class OrderStorage actual constructor(context: Any?) {

    private val json = Json { prettyPrint = true }
    private val file = File("orders.json")

    actual fun saveOrders(orders: List<OrderWithPizzas>) {
        // Charge les commandes existantes si le fichier existe, sinon une liste vide
        val existingOrders = if (file.exists()) {
            try {
                json.decodeFromString<List<OrderWithPizzas>>(file.readText())
            } catch (e: Exception) {
                emptyList()
            }
        } else {
            emptyList()
        }
        // Combine les commandes existantes avec les nouvelles
        val combinedOrders = existingOrders + orders
        // Réécrit le fichier avec la liste combinée
        file.writeText(json.encodeToString(combinedOrders))
    }

    actual fun loadOrders(): List<OrderWithPizzas> {
        return if (file.exists()) {
            try {
                json.decodeFromString(file.readText())
            } catch (e: Exception) {
                emptyList()
            }
        } else {
            emptyList()
        }
    }
}