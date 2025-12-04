package com.kmp.template

import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.window.core.layout.WindowSizeClass


/**
 * @author lianml
 * @date 2025/11/6
 */
fun <T> WindowAdaptiveInfo.adaptiveValue(compact: T, medium: T, expanded: T): T {
    val windowSizeClass = this.windowSizeClass

    return when {
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> expanded
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> medium
        else -> compact
    }
}