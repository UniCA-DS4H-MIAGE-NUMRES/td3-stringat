package td.numres.pizzaapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Order (
    val id: Int,
    val totalPrice: Double
)