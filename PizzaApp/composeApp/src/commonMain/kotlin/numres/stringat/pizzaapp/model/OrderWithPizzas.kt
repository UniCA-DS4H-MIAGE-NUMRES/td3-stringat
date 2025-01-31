package numres.stringat.pizzaapp.model

import androidx.room.Embedded
import androidx.room.Relation

data class OrderWithPizzas(
    @Embedded val order: Order,
    @Relation(
        parentColumn = "id",
        entityColumn = "orderId"
    )
    val pizzas: List<PizzaOrder>
)