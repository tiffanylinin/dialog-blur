package com.yourapp.ui.focus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild

/**
 * FocusScreen
 *
 * Full-screen layout that hosts the app background and the blur dialog overlay.
 *
 * Layer order (bottom → top):
 *  1. AppBackground  — the actual app content, registered as the haze blur source
 *  2. Dim overlay    — full-screen hazeChild that darkens + blurs the background
 *  3. FocusDialog    — the glassmorphic card that also blurs whatever is behind it
 */
@Composable
fun FocusScreen() {
    val hazeState = remember { HazeState() }
    var showDialog by remember { mutableStateOf(true) }

    Box(modifier = Modifier.fillMaxSize()) {

        // ① Background content — register as the blur source
        AppBackground(
            modifier = Modifier
                .fillMaxSize()
                .haze(state = hazeState)
        )

        // ② Full-screen dim + blur overlay (shown only when dialog is visible)
        if (showDialog) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .hazeChild(
                        state = hazeState,
                        style = HazeStyle(
                            backgroundColor = Color.Black.copy(alpha = 0.10f),
                            blurRadius = 22.dp,
                            tints = emptyList()
                        )
                    )
            )

            // ③ Glassmorphic dialog card
            FocusDialog(
                hazeState = hazeState,
                onDismiss = { showDialog = false },
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 47.dp)
            )
        }
    }
}

/**
 * Placeholder for your existing app background content.
 * Replace the body with your actual screen composable.
 */
@Composable
private fun AppBackground(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.background(Color(0xFF3FAB94))
    ) {
        // TODO: replace with your real background content
        // e.g. TimerScreen(), PlantScene(), etc.
    }
}
