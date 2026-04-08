package com.k1031oct.nfa.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFF5E62),
    secondary = Color(0xFF625b71),
    tertiary = Color(0xFF7D5260),
    background = Color(0xFF1A1A1A),
    surface = Color(0xFF2D2D2D)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFFF5E62),
    secondary = Color(0xFF625b71),
    tertiary = Color(0xFF7D5260),
    background = Color(0xFFFBFBFB),
    surface = Color.White
)

@Composable
fun NotebookTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
