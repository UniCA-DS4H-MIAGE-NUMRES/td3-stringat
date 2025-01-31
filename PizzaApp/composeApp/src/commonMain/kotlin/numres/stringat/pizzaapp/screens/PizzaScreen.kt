package numres.stringat.pizzaapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import numres.stringat.pizzaapp.model.Pizza
import numres.stringat.pizzaapp.viewmodel.CartViewModel

@Composable
fun DetailPizza(pizza: Pizza, navController: NavController, cartViewModel: CartViewModel, modifier: Modifier = Modifier) {
    var extraCheese by remember { mutableStateOf(50) } // Quantité de fromage supplémentaire

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                cartViewModel.addPizza(pizza, 1, extraCheese)
                navController.navigate("cart")
            }) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Ajouter au caddy"
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
                    painter = painterResource(id = pizza.image),
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