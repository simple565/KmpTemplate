package com.kmp.template

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable

/**
 * @author Lian
 * @date 2025/12/3
 */
@Serializable
object ListPage

@Composable
fun ListPage(
    onDetailClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    windowInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo()
) {
    val dataList = remember { mutableStateOf(emptyList<String>()) }
    LaunchedEffect(Unit) {
        val new = mutableListOf<String>()
        repeat(10) {
            new.add("This is Data $it")
        }
        dataList.value = new.toList()
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 120.dp),// Fixed(minColumnCount),
        modifier = modifier.safeContentPadding(),
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(
            12.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = dataList.value, key = { it.hashCode() }) {
            Row(
                modifier = Modifier.clickable { onDetailClick(it) },
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = it
                )
            }
        }
    }
}