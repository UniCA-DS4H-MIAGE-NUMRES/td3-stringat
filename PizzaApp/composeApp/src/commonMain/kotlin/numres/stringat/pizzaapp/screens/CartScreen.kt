package numres.stringat.pizzaapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import numres.stringat.pizzaapp.model.PizzaOrder
import numres.stringat.pizzaapp.viewmodel.CartViewModel
import numres.stringat.pizzaapp.viewmodel.OrderViewModel

@Composable
fun CartScreen(cartViewModel: CartViewModel, orderViewModel: OrderViewModel, navController: NavController, modifier: Modifier) {
    val cartItems by cartViewModel.cartItems.collectAsState()
    val totalPrice = cartItems.sumOf { it.first.price * it.second }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Votre Panier",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (cartItems.isEmpty()) {
            Text(
                text = "Votre panier est vide.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 16.dp),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 1f)
            )
        } else {
            cartItems.forEach { (pizza, quantity, extraCheese) ->
                Text(text = "${pizza.name} x$quantity (Extra cheese: $extraCheese) - €${pizza.price * quantity}")
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Prix Total : €$totalPrice", style = MaterialTheme.typography.headlineSmall)

            Button(
                onClick = {
                    if (cartItems.isNotEmpty()) {
                        val totalPrice = cartItems.sumOf { it.first.price * it.second }

                        // Insérer l'order dans la base de données et récupérer son ID
                        orderViewModel.addOrderWithPizzas(cartItems, totalPrice)

                        cartViewModel.clearCart()
                        navController.navigate("orderHistory")
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            ) {
                Text(text = "Passer la commande")
            }
        }
    }
}