package numres.stringat.pizzaapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import numres.stringat.pizzaapp.model.Pizza

class CartViewModel {
    private val _cartItems = mutableStateListOf<Triple<Pizza, Int, Int>>()
    val cartItems: List<Triple<Pizza, Int, Int>> get() = _cartItems

    fun addPizza(pizza: Pizza, quantity: Int, extraCheese: Int) {
        _cartItems.add(Triple(pizza, quantity, extraCheese))
    }

    fun clearCart() {
        _cartItems.clear()
    }
}