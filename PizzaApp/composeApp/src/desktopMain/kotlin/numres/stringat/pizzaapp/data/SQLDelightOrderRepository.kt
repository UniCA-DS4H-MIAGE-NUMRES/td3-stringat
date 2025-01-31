package numres.stringat.pizzaapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import numres.stringat.pizzaapp.model.OrderWithPizzas
import numres.stringat.pizzaapp.model.PizzaOrder
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver

class SQLDelightOrderRepository(driver: SqlDriver) : OrderRepository {
    private val database = Database(driver) // Assurez-vous que Database est généré par SQLDelight
    private val orderQueries = database.orderQueries

    private val ordersFlow = MutableStateFlow<List<OrderWithPizzas>>(emptyList())

    override fun getAllOrdersWithPizzas(): Flow<List<OrderWithPizzas>> {
        return orderQueries.getOrdersWithPizzas()
            .asFlow()
            .mapToList()
    }

    override suspend fun insertOrder(pizzas: List<PizzaOrder>, totalPrice: Double) {
        orderQueries.transaction {
            orderQueries.insertOrder(totalPrice)
            val orderId = orderQueries.lastInsertRowId().executeAsOne().toInt()

            pizzas.forEach { pizza ->
                orderQueries.insertPizzaOrder(orderId, pizza.name, pizza.quantity, pizza.extraCheese)
            }
        }
    }
}

fun createDesktopDatabase(): SQLDelightOrderRepository {
    val driver = JdbcSqliteDriver("jdbc:sqlite:pizza.db")

    // Vérifie si la base est déjà initialisée
    try {
        Database.Schema.create(driver)
    } catch (e: Exception) {
        println("Base de données déjà existante.")
    }

    return SQLDelightOrderRepository(driver)
}