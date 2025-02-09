package td.numres.pizzaapp.data

import td.numres.pizzaapp.model.OrderWithPizzas

expect class OrderStorage(context: Any? = null) {
    fun saveOrders(orders: List<OrderWithPizzas>)
    fun loadOrders(): List<OrderWithPizzas>
}