package com.kmp.template.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.GridView
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kmp.template.database.Room
import com.kmp.template.shared.Res
import com.kmp.template.shared.app_name
import com.kmp.template.shared.room_last_update_format
import com.kmp.template.ui.home.RoomListDisplayConfigDialog
import org.jetbrains.compose.resources.stringResource

/**
 * @author Lian
 * @date 2025/12/3
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ListPage(
    onDetailClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RoomViewModel = viewModel { RoomViewModel() }
) {
    val displayRoomList by viewModel.roomList.collectAsStateWithLifecycle()
    val showAddRoomDialog = remember { mutableStateOf(false) }
    val showListDisplayConfigDialog = remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier.then(Modifier.fillMaxSize().systemBarsPadding()),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.app_name),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                actions = {
                    IconButton(
                        onClick = { showListDisplayConfigDialog.value = true },
                        content = {
                            Icon(
                                imageVector = Icons.Rounded.GridView,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    )
                    IconButton(
                        onClick = { showAddRoomDialog.value = true },
                        content = {
                            Icon(
                                imageVector = Icons.Rounded.Add,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    )
                }
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = if (displayRoomList.columnCount == 0) GridCells.Adaptive(minSize = 140.dp) else GridCells.Fixed(displayRoomList.columnCount),
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val cardModifier = if (displayRoomList.columnCount == 1) {
                Modifier.fillMaxWidth().defaultMinSize(minHeight = 80.dp)
            } else {
                Modifier.aspectRatio(if (displayRoomList.columnCount == 1) 5.div(2F) else 3.div(2F))
            }
            items(items = displayRoomList.roomList, key = { it.hashCode() }) {
                RoomBriefInfoCard(
                    briefInfo = it,
                    modifier = cardModifier.combinedClickable(
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
    }

    if (showAddRoomDialog.value) {

    }

    if (showListDisplayConfigDialog.value) {
        RoomListDisplayConfigDialog(
            onDismissRequest = { showListDisplayConfigDialog.value = false },
            onColumnValueChange = {
                viewModel.updateRoomListDisplayConfig(it)
            }
        )
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
                Text(
                    text = briefInfo.name,
                    modifier = Modifier.weight(1F),
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.MiddleEllipsis
                )

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(Res.string.room_last_update_format, briefInfo.updateTime),
                    style = MaterialTheme.typography.labelSmall
                        .copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                )
                Spacer(modifier = Modifier.width(8.dp))
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