package uk.ac.tees.mad.stayfinder.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = PrimaryOrange,
    secondary = LightOrange,
    background = WarmBeige,
    surface = SoftWhite,

    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Charcoal,
    onSurface = Charcoal
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimaryOrange,
    secondary = DarkSecondaryOrange,
    background = DarkBackground,
    surface = DarkSurface,

    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = DarkOnBackground,
    onSurface = DarkOnSurface
)

@Composable
fun StayFinderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = StayFinderShapes,
        content = content
    )
}