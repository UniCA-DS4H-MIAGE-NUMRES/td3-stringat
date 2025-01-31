package numres.stringat.pizzaapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.browser.localStorage
import numres.stringat.pizzaapp.model.OrderWithPizzas
import numres.stringat.pizzaapp.model.PizzaOrder
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString

class LocalStorageOrderRepository : OrderRepository {
    private val ordersFlow = MutableStateFlow(loadOrders())

    override fun getAllOrdersWithPizzas(): Flow<List<OrderWithPizzas>> {
        return ordersFlow
    }

    override suspend fun insertOrder(pizzas: List<PizzaOrder>, totalPrice: Double) {
        val newOrder = OrderWithPizzas(order = OrderWithPizzas.Order(pizzas = pizzas, totalPrice = totalPrice))
        val newList = ordersFlow.value + newOrder
        ordersFlow.value = newList
        saveOrders(newList)
    }

    private fun saveOrders(orders: List<OrderWithPizzas>) {
        localStorage.setItem("orders", Json.encodeToString(orders))
    }

    private fun loadOrders(): List<OrderWithPizzas> {
        return localStorage.getItem("orders")?.let {
            Json.decodeFromString(it)
        } ?: emptyList()
    }
}