package numres.stringat.pizzaapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import numres.stringat.pizzaapp.data.OrderRepository
import numres.stringat.pizzaapp.model.OrderWithPizzas
import numres.stringat.pizzaapp.model.PizzaOrder

class OrderViewModel(private val repository: OrderRepository) : ViewModel() {
    private val _orders = MutableStateFlow<List<OrderWithPizzas>>(emptyList())
    val orders: Flow<List<OrderWithPizzas>> = _orders.asStateFlow()

    fun getAllOrdersWithPizzas() {
        _orders.value = repository.getAllOrdersWithPizzas()
    }

    suspend fun addOrder(pizzas: List<PizzaOrder>, totalPrice: Double) {
        repository.insertOrder(pizzas, totalPrice)
    }
}