package numres.stringat.pizzaapp.data

import kotlinx.coroutines.flow.Flow
import numres.stringat.pizzaapp.model.Order
import numres.stringat.pizzaapp.model.OrderWithPizzas
import numres.stringat.pizzaapp.model.PizzaOrder

class RoomOrderRepository(private val orderDao: OrderDao) : OrderRepository {
    override fun getAllOrdersWithPizzas(): Flow<List<OrderWithPizzas>> {
        return orderDao.getAllOrdersWithPizzas()
    }

    override suspend fun insertOrder(order: PizzaOrder): Long {
        return orderDao.insertPizzaOrders(listOf(order)) // Insère une pizza dans la commande
        return 1L // Retourne un ID factice (à améliorer)
    }
}