package td.numres.pizzaapp.utils

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import td.numres.pizzaapp.R

@Composable
actual fun getImageResource(path: String): Painter {
    val resId = getDrawableResourceId(path)
    return painterResource(id = resId)
}

fun getDrawableResourceId(name: String): Int {
    return when (name) {
        "logo.png" -> R.drawable.logo
        else -> R.drawable.pizza1 // Ajoute une image par défaut
    }
}