package numres.stringat.pizzaapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import numres.stringat.pizzaapp.R
import numres.stringat.pizzaapp.model.Pizza

class PizzaViewModel : ViewModel() {
    private val _pizzas = MutableStateFlow<List<Pizza>>(emptyList())
    val pizzas: StateFlow<List<Pizza>> = _pizzas.asStateFlow()

    init {
        loadPizzas()
    }

    private fun loadPizzas() {
        _pizzas.value = listOf(
            Pizza(id = 1, name = "Margherita", price = 8.0, image = R.drawable.pizza1),
            Pizza(id = 2, name = "Capricciosa", price = 10.0, image = R.drawable.pizza2),
            Pizza(id = 3, name = "Diavola", price = 9.0, image = R.drawable.pizza3),
            Pizza(id = 4, name = "Quattro Stagioni", price = 11.0, image = R.drawable.pizza4),
            Pizza(id = 5, name = "Quattro Formaggi", price = 12.0, image = R.drawable.pizza5),
            Pizza(id = 6, name = "Marinara", price = 7.0, image = R.drawable.pizza6),
            Pizza(id = 7, name = "Pepperoni", price = 9.0, image = R.drawable.pizza7),
            Pizza(id = 8, name = "Prosciutto", price = 10.0, image = R.drawable.pizza8),
            Pizza(id = 9, name = "Frutti di Mare", price = 13.0, image = R.drawable.pizza9)
        )
    }

    fun getPizzaById(id: Int): Pizza? {
        return _pizzas.value.find { it.id == id }
    }
}