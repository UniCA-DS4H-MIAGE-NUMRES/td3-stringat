package td.numres.pizzaapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Pizza(
    val id: Int = 0,
    val name: String,
    val price: Double,
    val image: String
)