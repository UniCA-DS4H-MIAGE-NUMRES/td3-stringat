package td.numres.pizzaapp.viewmodel

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import td.numres.pizzaapp.model.Pizza

class CartViewModel : KoinComponent {

    private val _cartItems = MutableStateFlow<List<Triple<Pizza, Int, Int>>>(emptyList())
    val cartItems: StateFlow<List<Triple<Pizza, Int, Int>>> = _cartItems

    fun addPizza(pizza: Pizza, quantity: Int, extraCheese: Int) {
        val currentItems = _cartItems.value.toMutableList()
        currentItems.add(Triple(pizza, quantity, extraCheese))
        _cartItems.value = currentItems
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }
}