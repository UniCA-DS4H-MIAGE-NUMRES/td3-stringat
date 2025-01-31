package numres.stringat.pizzaapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import numres.stringat.pizzaapp.model.OrderWithPizzas
import numres.stringat.pizzaapp.viewmodel.OrderViewModel

@Composable
fun OrderHistoryScreen(
    navController: NavController,
    orderViewModel: OrderViewModel
) {
    val orders by orderViewModel.getAllOrdersWithPizzas().collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Historique des commandes",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (orders.isEmpty()) {
            Text(
                text = "Aucune commande passée pour le moment.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(orders) { orderWithPizzas ->
                    OrderCard(orderWithPizzas)
                }
            }
        }

        Button(
            onClick = { navController.navigate("welcome") },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text(text = "Retour à l'accueil", fontSize = 18.sp)
        }
    }
}

@Composable
fun OrderCard(orderWithPizzas: OrderWithPizzas) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE0B2))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Commande #${orderWithPizzas.order.id}", style = MaterialTheme.typography.titleMedium)
            orderWithPizzas.pizzas.forEach { pizzaOrder ->
                Text(text = "${pizzaOrder.name} x${pizzaOrder.quantity} (Fromage: ${pizzaOrder.extraCheese})")
            }
            Text(text = "Total: €${orderWithPizzas.order.totalPrice}")
        }
    }
}