package td.numres.pizzaapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.jetbrains.compose.resources.painterResource
import td.numres.pizzaapp.model.Pizza

class PizzaViewModel : ViewModel() {
    private val _pizzas = MutableStateFlow<List<Pizza>>(emptyList())
    val pizzas: StateFlow<List<Pizza>> = _pizzas.asStateFlow()

    init {
        loadPizzas()
    }

    private fun loadPizzas() {
        _pizzas.value = listOf(
            Pizza(id = 1, name = "Margherita", price = 8.0, image = "pizza1.png"),
            Pizza(id = 2, name = "Capricciosa", price = 10.0, image = "pizza2.png"),
            Pizza(id = 3, name = "Diavola", price = 9.0, image = "pizza3.png"),
            Pizza(id = 4, name = "Quattro Stagioni", price = 11.0, image = "pizza4.png"),
            Pizza(id = 5, name = "Quattro Formaggi", price = 12.0, image = "pizza5.png"),
            Pizza(id = 6, name = "Marinara", price = 7.0, image = "pizza6.png"),
            Pizza(id = 7, name = "Pepperoni", price = 9.0, image = "pizza7.png"),
            Pizza(id = 8, name = "Prosciutto", price = 10.0, image = "pizza8.png"),
            Pizza(id = 9, name = "Frutti di Mare", price = 13.0, image = "pizza9.png"),
        )
    }

    fun getPizzaById(id: Int): Pizza? {
        return _pizzas.value.find { it.id == id + 1 }
    }
}