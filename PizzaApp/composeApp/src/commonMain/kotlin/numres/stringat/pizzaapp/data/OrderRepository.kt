package numres.stringat.pizzaapp.data

import kotlinx.coroutines.flow.Flow
import numres.stringat.pizzaapp.model.OrderWithPizzas
import numres.stringat.pizzaapp.model.PizzaOrder

interface OrderRepository {
    fun getAllOrdersWithPizzas(): Flow<List<OrderWithPizzas>>
    suspend fun insertOrder(pizzas: List<PizzaOrder>, totalPrice: Double)
}