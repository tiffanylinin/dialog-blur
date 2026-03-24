# Frame Blur Dialog — Compose Multiplatform (KMP)

Glassmorphic blur dialog implementation for the Focus Timer app, built with Kotlin Multiplatform / Compose Multiplatform.

---

## Preview

| Background | Dialog open |
|---|---|
| App screen with timer | Frosted glass card overlaid with background blur |

---

## Files

| File | Purpose |
|---|---|
| `FocusScreen.kt` | Top-level screen — wires the blur state between background and dialog |
| `FocusDialog.kt` | Glassmorphic dialog card — blur, frosted fill, gradient border, image, CTA |
| `PaginationDots.kt` | Reusable dot indicator row |
| `build.gradle.kts.snippet` | Dependencies to add to the project |

---

## Setup

### 1. Add dependencies

Open `build.gradle.kts` and paste the following inside `commonMain.dependencies { }`:

```kotlin
// Haze — cross-platform blur / glassmorphism for Compose Multiplatform
implementation("dev.chrisbanes.haze:haze:1.0.3")

// Coil 3 — async image loading (KMP-compatible)
implementation("io.coil-kt.coil3:coil-compose:3.1.0")
implementation("io.coil-kt.coil3:coil-network-ktor:3.1.0")

// Compose Material 3 (if not already included)
implementation(compose.material3)
implementation(compose.materialIconsExtended)
```

### 2. Copy the Kotlin files

Place the three `.kt` files into the `commonMain` source set:

```
composeApp/src/commonMain/kotlin/com/yourapp/ui/focus/
├── FocusScreen.kt
├── FocusDialog.kt
└── PaginationDots.kt
```

### 3. Update the package name

At the top of each file, replace:
```kotlin
package com.yourapp.ui.focus
```
with your project's actual package name.

### 4. Connect your content

In `FocusScreen.kt`, replace the `AppBackground()` placeholder with your real screen composable:
```kotlin
// Before
AppBackground(modifier = Modifier.fillMaxSize().haze(hazeState))

// After
YourActualScreen(modifier = Modifier.fillMaxSize().haze(hazeState))
```

### 5. Pass the image URL

In `FocusDialog.kt`, pass the hero image URL when calling the composable:
```kotlin
FocusDialog(
    hazeState = hazeState,
    onDismiss = { showDialog = false },
    imageUrl = "https://your-image-url.com/plant.jpg"
)
```

---

## How the blur effect works

The glassmorphic effect is built from three layers:

```
┌─────────────────────────────┐
│  ③ FocusDialog              │  hazeChild (blurs behind the card)
│   ┌─────────────────────┐   │  + white 56% fill + gradient border
│   │  hero image         │   │
│   │  title + desc       │   │
│   │  • • • •            │   │
│   │  [ Understand ]     │   │
│   └─────────────────────┘   │
│                             │
│  ② Dim overlay              │  hazeChild full-screen (darkens bg)
│                             │
│  ① App background           │  .haze(hazeState)  ← blur source
└─────────────────────────────┘
```

| Design property | Compose equivalent |
|---|---|
| `background_blur` rect | `Box + hazeChild` full-screen |
| Dialog `background_blur` + white fill | `hazeChild` with `backgroundColor` + `blurRadius` |
| Gradient border stroke | `Modifier.border(brush = Brush.linearGradient(...))` |
| `layoutPosition: absolute` close button | `Box + Modifier.align(Alignment.TopEnd)` |
| `cornerRadius: 32` | `RoundedCornerShape(32.dp) + Modifier.clip()` |

---

## Requirements

- Kotlin Multiplatform / Compose Multiplatform project
- Minimum Android API 21 (blur renders best on API 31+)
- iOS 15+
