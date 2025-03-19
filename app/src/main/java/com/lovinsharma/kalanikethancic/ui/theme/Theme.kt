package com.lovinsharma.kalanikethancic.ui.theme

import android.app.Activity
import android.hardware.lights.Light
import android.os.Build
import android.sax.Element
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = LightPrimary,
    secondary = LightSecondary,
    tertiary = LightHighlight
)

private val LightColorScheme = lightColorScheme(
    primary = LightPrimary, // This is for the sideBar
    secondary = LightSecondary, // This is for the title bar
    tertiary = ElementBackground, // This is for text fields
    background = LightBackground, // This is for the background
    onPrimary = ButtonPrimary, // This is for buttons.
    onSecondary = LightHighlight, // This is for when buttons are highlighted.



    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun KalanikethanCICTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+: Dynamic color is disabled to maintain a consistent theme
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}