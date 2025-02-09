package td.numres.pizzaapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import td.numres.pizzaapp.model.Pizza
import td.numres.pizzaapp.viewmodel.PizzaViewModel
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import td.numres.pizzaapp.utils.getImageResource


@Composable
fun PizzaCard(pizza: Pizza, modifier: Modifier = Modifier, onClickPizza: () -> Unit) {
    Card(
        modifier = modifier,
        onClick = onClickPizza
    ) {
        Column(modifier = modifier) {
            Image(
                painter = getImageResource(pizza.image),
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
fun PizzaMenu(pizzaViewModel: PizzaViewModel, currentScreen: MutableState<String>, modifier: Modifier = Modifier) {
    val pizzas by pizzaViewModel.pizzas.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(pizzas) { pizza ->
                PizzaCard(
                    pizza = pizza,
                    modifier = Modifier.padding(16.dp),
                    onClickPizza = {
                        currentScreen.value = "detail/${pizzas.indexOf(pizza)}"
                    }
                )
            }
        }

        Button(
            onClick = { currentScreen.value = "cart" },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Text(text = "Voir le panier", fontSize = 18.sp)
        }
    }
}