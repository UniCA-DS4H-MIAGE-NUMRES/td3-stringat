package td.numres.pizzaapp.data

import android.content.Context
//import app.cash.sqldelight.db.SqlDriver
//import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import td.numres.pizzaapp.model.Order
import td.numres.pizzaapp.model.OrderWithPizzas
import td.numres.pizzaapp.model.PizzaOrder
import java.util.Collections.emptyList

actual class OrderStorage actual constructor(private val context: Any?) {

    // Désactivation temporaire de la base de données
    /*
    private val driver: SqlDriver = AndroidSqliteDriver(PizzaDatabase.Schema, context as Context, "PizzaDatabase.db")
    private val database = PizzaDatabase(driver)
    private val orderQueries = database.orderEntityQueries
    private val pizzaOrderQueries = database.pizzaOrderQueries
    */

    actual fun saveOrders(orders: List<OrderWithPizzas>) {
        return;
        // Désactivé temporairement
        /*
        orders.forEach { orderWithPizzas ->
            orderQueries.insertOrder(orderWithPizzas.order.id, orderWithPizzas.order.totalPrice)
            orderWithPizzas.pizzas.forEach { pizzaOrder ->
                pizzaOrderQueries.insertPizzaOrder(
                    pizzaOrder.id,
                    orderWithPizzas.order.id,
                    pizzaOrder.name,
                    pizzaOrder.quantity,
                    pizzaOrder.extraCheese
                )
            }
        }
        */
    }

    actual fun loadOrders(): List<OrderWithPizzas> {
        // Désactivé temporairement, retourne une liste vide
        return emptyList()

        /*
        return orderQueries.selectAllOrders().executeAsList().map { orderEntity ->
            val pizzas = pizzaOrderQueries.selectPizzasByOrder(orderEntity.id).executeAsList().map { pizzaEntity ->
                PizzaOrder(
                    id = pizzaEntity.id,
                    orderId = orderEntity.id,
                    name = pizzaEntity.name,
                    quantity = pizzaEntity.quantity,
                    extraCheese = pizzaEntity.extraCheese
                )
            }
            OrderWithPizzas(
                order = Order(id = orderEntity.id, totalPrice = orderEntity.price),
                pizzas = pizzas
            )
        }
        */
    }
}