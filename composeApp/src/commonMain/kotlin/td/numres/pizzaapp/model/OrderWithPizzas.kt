package td.numres.pizzaapp.model

import kotlinx.serialization.Serializable

@Serializable
data class OrderWithPizzas(
    val order: Order,
    val pizzas: List<PizzaOrder>
)