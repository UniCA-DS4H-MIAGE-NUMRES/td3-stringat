package td.numres.pizzaapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import td.numres.pizzaapp.model.Order
import td.numres.pizzaapp.model.OrderWithPizzas
import td.numres.pizzaapp.model.PizzaOrder
import td.numres.pizzaapp.viewmodel.CartViewModel
import td.numres.pizzaapp.viewmodel.OrderViewModel

@Composable
fun CartScreen(
    cartViewModel: CartViewModel,
    orderViewModel: OrderViewModel,
    currentScreen: MutableState<String>,
    modifier: Modifier
) {
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
                        val order = Order(
                            id = 0,
                            totalPrice = totalPrice
                        )
                        val pizzas = cartItems.mapIndexed { index, (pizza, quantity, extraCheese) ->
                            PizzaOrder(
                                id = index + 1,
                                orderId = order.id,
                                name = pizza.name,
                                quantity = quantity,
                                extraCheese = extraCheese
                            )
                        }
                        val orderWithPizzas = OrderWithPizzas(order, pizzas)

                        orderViewModel.addOrder(orderWithPizzas)

                        cartViewModel.clearCart()
                        currentScreen.value = "orderHistory"
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            ) {
                Text(text = "Passer la commande")
            }
        }
    }
}