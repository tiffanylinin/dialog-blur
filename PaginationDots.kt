package com.yourapp.ui.focus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * PaginationDots
 *
 * A row of circular indicators matching the design:
 *  - Active dot:   #3FAB94 (teal)
 *  - Inactive dot: #000000 30% opacity
 *  - Dot size:     6×6dp
 *  - Gap:          6dp
 *
 * @param total     Total number of dots.
 * @param selected  Index of the currently active dot (0-based).
 * @param dotSize   Diameter of each dot. Defaults to 6dp.
 * @param spacing   Gap between dots. Defaults to 6dp.
 */
@Composable
fun PaginationDots(
    total: Int,
    selected: Int,
    dotSize: Dp = 6.dp,
    spacing: Dp = 6.dp
) {
    Row(horizontalArrangement = Arrangement.spacedBy(spacing)) {
        repeat(total) { index ->
            Box(
                modifier = Modifier
                    .size(dotSize)
                    .clip(CircleShape)
                    .background(
                        if (index == selected) Color(0xFF3FAB94)
                        else Color.Black.copy(alpha = 0.30f)
                    )
            )
        }
    }
}
