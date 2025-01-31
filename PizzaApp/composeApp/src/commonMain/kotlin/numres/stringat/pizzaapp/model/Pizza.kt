package numres.stringat.pizzaapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pizzas")
data class Pizza(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val price: Double,
    val image: Int
)