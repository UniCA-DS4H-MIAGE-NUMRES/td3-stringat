package td.numres.pizzaapp.viewmodel

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import td.numres.pizzaapp.data.OrderStorage
import td.numres.pizzaapp.model.OrderWithPizzas

class OrderViewModel : KoinComponent {

    private val orderStorage: OrderStorage by inject()

    private val _orders = MutableStateFlow<List<OrderWithPizzas>>(emptyList())
    val orders: StateFlow<List<OrderWithPizzas>> = _orders.asStateFlow()

    fun addOrder(order: OrderWithPizzas) {
        _orders.value += order
        orderStorage.saveOrders(_orders.value)
    }

    fun loadOrders() {
        val loadedOrders = orderStorage.loadOrders()
        println("Commandes chargées : $loadedOrders")
        _orders.value = orderStorage.loadOrders()
    }
}