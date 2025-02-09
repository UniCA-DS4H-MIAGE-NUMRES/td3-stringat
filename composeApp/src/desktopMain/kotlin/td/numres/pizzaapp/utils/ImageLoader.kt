package td.numres.pizzaapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import org.jetbrains.compose.resources.painterResource

@Composable
actual fun getImageResource(path: String): Painter {
    return painterResource(path)
}