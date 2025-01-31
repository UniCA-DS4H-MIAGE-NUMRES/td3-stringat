package numres.stringat.pizzaapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "pizza_orders",
    foreignKeys = [ForeignKey(
        entity = Order::class,
        parentColumns = ["id"],
        childColumns = ["orderId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class PizzaOrder(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val orderId: Int,  // Clé étrangère vers Order
    val name: String,
    val quantity: Int,
    val extraCheese: Int
)