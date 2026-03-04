package com.taskmanagerpro.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Primary = Color(0xFF6366F1)
private val Secondary = Color(0xFF64748B)
private val Success = Color(0xFF10B981)
private val Error = Color(0xFFEF4444)
private val Warning = Color(0xFFF59E0B)

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    secondary = Secondary,
    error = Error,
    background = Color(0xFFF9FAFB),
    surface = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    secondary = Secondary,
    error = Error,
    background = Color(0xFF111827),
    surface = Color(0xFF1F2937)
)

@Composable
fun TaskManagerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        content = content
    )
}