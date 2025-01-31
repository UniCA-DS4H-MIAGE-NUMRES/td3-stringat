package numres.stringat.pizzaapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import numres.stringat.pizzaapp.model.Order
import numres.stringat.pizzaapp.model.PizzaOrder

@Database(entities = [Order::class, PizzaOrder::class], version = 1, exportSchema = false)
abstract class PizzaDatabase : RoomDatabase() {
    abstract fun orderDao(): OrderDao

    companion object {
        @Volatile
        private var INSTANCE: PizzaDatabase? = null

        fun getDatabase(context: Context): PizzaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PizzaDatabase::class.java,
                    "pizza_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}