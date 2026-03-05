package com.kmp.template.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import com.kmp.template.shared.Res
import com.kmp.template.shared.nav_home
import com.kmp.template.shared.nav_settings
import com.kmp.template.ui.ListPage
import com.kmp.template.ui.composable.NavigationItem
import com.kmp.template.ui.settings.SettingsPage
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource

@Serializable
object HomePage : NavKey

@Composable
fun HomePage(
    onDetailClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo()
) {
    var selectedItem by rememberSaveable { mutableStateOf(0) }

    val navigationItemList = listOf(
        NavigationItem(label = stringResource(Res.string.nav_home), icon = Icons.Rounded.Home),
        NavigationItem(
            label = stringResource(Res.string.nav_settings),
            icon = Icons.Rounded.Settings
        )
    )

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            navigationItemList.forEachIndexed { index, item ->
                item(
                    selected = selectedItem == index,
                    onClick = { selectedItem = index },
                    icon = {
                        Icon(imageVector = item.icon, contentDescription = item.label)
                    },
                    label = {
                        Text(text = item.label)
                    }
                )
            }
        },
        modifier = modifier,
        layoutType = NavigationSuiteScaffoldDefaults.navigationSuiteType(windowAdaptiveInfo)
    ) {
        when (selectedItem) {
            0 -> ListPage(
                onDetailClick = onDetailClick,
                modifier = Modifier.fillMaxSize()
            )

            1 -> SettingsPage(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}