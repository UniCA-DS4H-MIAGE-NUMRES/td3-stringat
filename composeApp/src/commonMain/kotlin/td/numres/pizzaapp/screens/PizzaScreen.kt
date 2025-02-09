package td.numres.pizzaapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.painterResource
import androidx.compose.ui.unit.dp
import td.numres.pizzaapp.model.Pizza
import td.numres.pizzaapp.utils.getImageResource
import td.numres.pizzaapp.viewmodel.CartViewModel

@Composable
fun DetailPizza(
    pizza: Pizza,
    currentScreen: MutableState<String>,
    cartViewModel: CartViewModel,
    modifier: Modifier = Modifier
) {
    var extraCheese by remember { mutableStateOf(50) } // Quantité de fromage supplémentaire

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                cartViewModel.addPizza(pizza, 1, extraCheese)
                currentScreen.value = "cart"
            }) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Ajouter au panier"
                )
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = getImageResource(pizza.image),
                    contentDescription = pizza.name,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(400.dp)
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = pizza.name,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Price: €${pizza.price}")
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Extra Cheese: $extraCheese")
                Slider(
                    value = extraCheese.toFloat(),
                    onValueChange = { extraCheese = it.toInt() },
                    valueRange = 0f..100f,
                    steps = 4
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    )
}