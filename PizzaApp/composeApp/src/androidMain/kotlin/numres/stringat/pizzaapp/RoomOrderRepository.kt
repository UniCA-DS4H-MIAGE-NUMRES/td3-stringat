package numres.stringat.pizzaapp.data

import kotlinx.coroutines.flow.Flow
import numres.stringat.pizzaapp.model.OrderWithPizzas
import numres.stringat.pizzaapp.model.PizzaOrder

class RoomOrderRepository(private val orderDao: OrderDao) : OrderRepository {

    override fun getAllOrdersWithPizzas(): Flow<List<OrderWithPizzas>> {
        return orderDao.getAllOrdersWithPizzas()
    }

    override suspend fun insertOrder(pizzas: List<PizzaOrder>, totalPrice: Double): Long {
        val orderId = orderDao.insertOrder(Order(totalPrice = totalPrice))

        // Insère chaque pizza associée à la commande
        pizzas.forEach { pizza ->
            orderDao.insertPizzaOrders(
                listOf(pizza.copy(orderId = orderId.toInt())) // Associe l'ID de commande
            )
        }
        return orderId
    }

    override suspend fun getLastInsertedOrderId(): Long {
        return orderDao.getLastInsertedOrderId()
    }
}