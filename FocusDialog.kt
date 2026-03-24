package com.yourapp.ui.focus

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeChild

/**
 * FocusDialog
 *
 * A glassmorphic bottom-sheet-style dialog card.
 *
 * Visual recipe (matches the Pencil design):
 *  - hazeChild  → blurs everything behind the card (background_blur, radius 70dp)
 *  - White semi-transparent fill (#ffffff 56% opacity)
 *  - ColorDodge tint layer to lighten edges
 *  - Gradient border: white 30% → white 10%, linear, 2dp
 *  - Corner radius: 32dp
 *
 * @param hazeState   The HazeState registered on the background content.
 * @param onDismiss   Called when the user taps X or the CTA button.
 * @param imageUrl    URL for the hero image inside the card.
 * @param modifier    Modifier for positioning from the parent.
 */
@Composable
fun FocusDialog(
    hazeState: HazeState,
    onDismiss: () -> Unit,
    imageUrl: String = "",
    modifier: Modifier = Modifier
) {
    val cardShape = RoundedCornerShape(32.dp)

    val glassBorderBrush = Brush.linearGradient(
        colors = listOf(
            Color.White.copy(alpha = 0.30f),
            Color.White.copy(alpha = 0.10f)
        )
    )

    Box(
        modifier = modifier
            .width(300.dp)
            .clip(cardShape)
            // Frosted glass effect — blurs content behind this card
            .hazeChild(
                state = hazeState,
                shape = cardShape,
                style = HazeStyle(
                    backgroundColor = Color.White.copy(alpha = 0.56f),
                    blurRadius = 70.dp,
                    tints = listOf(
                        HazeTint(
                            color = Color(0xFF2C2C2C).copy(alpha = 0.08f),
                            blendMode = BlendMode.ColorDodge
                        )
                    )
                )
            )
            // Gradient border
            .border(
                width = 2.dp,
                brush = glassBorderBrush,
                shape = cardShape
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Hero image
            AsyncImage(
                model = imageUrl,
                contentDescription = "Focus illustration",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            // Title + description
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Customize Your Focus",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF333333),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Fine-tune your focus settings for optimal productivity.",
                    fontSize = 16.sp,
                    color = Color(0xFF666666),
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp
                )
            }

            // Pagination dots
            PaginationDots(total = 4, selected = 0)

            // CTA button
            Button(
                onClick = onDismiss,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF176361)
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
            ) {
                Text(
                    text = "Understand",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        // Close button — absolutely positioned top-right
        CloseButton(
            onClick = onDismiss,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 8.dp, end = 8.dp)
        )
    }
}

/**
 * Frosted-glass icon button used as the dialog's dismiss control.
 * Matches the design: circular, glass fill, gradient border, 42×42dp.
 */
@Composable
private fun CloseButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderBrush = Brush.linearGradient(
        colors = listOf(
            Color.White.copy(alpha = 0.30f),
            Color.White.copy(alpha = 0.10f)
        )
    )

    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(42.dp)
            .clip(CircleShape)
            .border(width = 1.75.dp, brush = borderBrush, shape = CircleShape),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Color.White.copy(alpha = 0.56f)
        )
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close dialog",
            tint = Color(0xFF333333),
            modifier = Modifier.size(20.dp)
        )
    }
}
