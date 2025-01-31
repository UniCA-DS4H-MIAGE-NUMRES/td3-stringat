package numres.stringat.pizzaapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import numres.stringat.pizzaapp.model.Order
import numres.stringat.pizzaapp.model.PizzaOrder
import numres.stringat.pizzaapp.model.OrderWithPizzas

@Dao
interface OrderDao {

    @Insert
    suspend fun insertOrder(order: Order): Long

    @Insert
    suspend fun insertPizzaOrders(pizzaOrders: List<PizzaOrder>)

    @Transaction
    @Query("SELECT * FROM orders")
    fun getAllOrdersWithPizzas(): Flow<List<OrderWithPizzas>>

    // Nouvelle requête pour récupérer le dernier ID inséré
    @Query("SELECT last_insert_rowid()")
    suspend fun getLastInsertedOrderId(): Long
}