package numres.stringat.pizzaapp.data

import kotlinx.coroutines.flow.Flow
import numres.stringat.pizzaapp.model.OrderWithPizzas
import numres.stringat.pizzaapp.model.PizzaOrder

interface OrderRepository {
    fun getAllOrdersWithPizzas(): Flow<List<OrderWithPizzas>>

    // Correction : Retourne maintenant un Long (ID de la commande créée)
    suspend fun insertOrder(pizzas: List<PizzaOrder>, totalPrice: Double): Long

    // Nouvelle méthode pour récupérer l'ID du dernier ordre inséré
    suspend fun getLastInsertedOrderId(): Long
}