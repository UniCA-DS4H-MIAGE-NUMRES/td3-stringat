package td.numres.pizzaapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

/**
 * Fonction `expect` pour charger une image en fonction de la plateforme.
 */

@Composable
expect fun getImageResource(path: String): Painter