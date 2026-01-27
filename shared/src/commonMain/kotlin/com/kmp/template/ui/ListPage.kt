package com.kmp.template.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kmp.template.database.Room
import com.kmp.template.shared.app_name
import com.kmp.template.shared.room_last_update_format
import com.kmp.template.ui.composable.AdaptiveDialog
import org.jetbrains.compose.resources.stringResource

/**
 * @author Lian
 * @date 2025/12/3
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPage(
    onDetailClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RoomViewModel = viewModel { RoomViewModel() }
) {
    val dataList by viewModel.roomList.collectAsStateWithLifecycle()
    val showBottomSheet = remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(com.kmp.template.shared.Res.string.app_name),
                        style = MaterialTheme.typography.titleLarge.copy(fontStyle = FontStyle.Italic)
                    )
                },
                actions = {
                    IconButton(
                        onClick = { showBottomSheet.value = true },
                        content = {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null
                            )
                        }
                    )
                }
            )
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 140.dp),
            modifier = Modifier.padding(padding).fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items = dataList, key = { it.hashCode() }) {
                RoomBriefInfoCard(
                    briefInfo = it,
                    modifier = Modifier.aspectRatio(3.div(2F)).combinedClickable(
                        indication = null,
                        interactionSource = null,
                        onClick = { onDetailClick(it.id) },
                        onLongClick = {
                            // TODO 删除编辑等更多操作菜单
                        }
                    )
                )
            }
        }
        if (showBottomSheet.value) {
            AdaptiveDialog(
                onDismissRequest = { showBottomSheet.value = false },
                usePlatformDefaultWidth = false,
                content = {
                    Column(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.95F),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp)
                        )
                        Text(
                            text = "Add Room",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            )
        }
    }
}

@Composable
private fun RoomBriefInfoCard(briefInfo: Room, modifier: Modifier = Modifier) {
    ElevatedCard(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = briefInfo.name, style = MaterialTheme.typography.titleSmall)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(
                        com.kmp.template.shared.Res.string.room_last_update_format,
                        briefInfo.updateTime
                    ),
                    style = MaterialTheme.typography.labelSmall
                        .copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                )
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(24.dp),
                    shape = RoundedCornerShape(50),
                    content = {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.surfaceVariant,
                                    shape = RoundedCornerShape(50)
                                )
                        )
                    }
                )
            }
        }
    }
}