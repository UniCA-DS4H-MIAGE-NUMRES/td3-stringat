package numres.stringat.pizzaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import numres.stringat.pizzaapp.ui.theme.PizzaAppTheme
import numres.stringat.pizzaapp.viewmodel.OrderViewModel
import numres.stringat.pizzaapp.data.OrderRepositoryImpl
import numres.stringat.pizzaapp.data.PizzaDatabase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = PizzaDatabase.getDatabase(applicationContext)
        val repository = RoomOrderRepository(database.orderDao()) // Utilisation de Room

        setContent {
            PizzaAppTheme {
                MyApp(orderViewModel = viewModel { OrderViewModel(repository) })
            }
        }
    }
}