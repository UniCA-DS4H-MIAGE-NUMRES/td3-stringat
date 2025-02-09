package td.numres.pizzaapp.model

import kotlinx.serialization.Serializable

@Serializable
data class PizzaOrder(
    val id: Int = 0,
    val orderId: Int,
    val name: String,
    val quantity: Int,
    val extraCheese: Int
)