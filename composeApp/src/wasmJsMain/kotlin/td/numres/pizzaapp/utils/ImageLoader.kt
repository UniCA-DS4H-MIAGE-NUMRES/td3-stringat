package td.numres.pizzaapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi

@OptIn(InternalResourceApi::class)
@Composable
actual fun getImageResource(path: String): Painter {
    val drawableResource: DrawableResource = DrawableResource(path, emptySet())
    return painterResource(drawableResource)
}