package numres.stringat.pizzaapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import numres.stringat.pizzaapp.model.Pizza
import numres.stringat.pizzaapp.viewmodel.PizzaViewModel

@Composable
fun PizzaCard(pizza: Pizza, modifier: Modifier = Modifier, onClickPizza : () -> Unit) {
    Card(
        modifier = modifier,
        onClick = onClickPizza
    ) {
        Column(modifier = modifier) {
            Image(
                painter = painterResource(id = pizza.image),
                contentDescription = pizza.name,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = pizza.name,
                modifier = modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
                    .width(300.dp),
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Prix = ${pizza.price} €",
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}


@Composable
fun PizzaMenu(pizzaViewModel: PizzaViewModel = viewModel(), modifier: Modifier = Modifier, navController: NavController) {
    val pizzas by pizzaViewModel.pizzas.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(pizzas) { pizza ->
                PizzaCard(
                    pizza = pizza,
                    modifier = Modifier.padding(16.dp),
                    onClickPizza = {
                        navController.navigate("detail/${pizzas.indexOf(pizza)}")
                    }
                )
            }
        }

        Button(
            onClick = { navController.navigate("cart") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Text(text = "Voir le panier", fontSize = 18.sp)
        }
    }
}