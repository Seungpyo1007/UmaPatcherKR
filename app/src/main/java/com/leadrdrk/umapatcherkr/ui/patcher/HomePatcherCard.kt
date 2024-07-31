package com.leadrdrk.umapatcherkr.ui.patcher

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.leadrdrk.umapatcherkr.R
import com.leadrdrk.umapatcherkr.patcher.HomePatcher
import com.leadrdrk.umapatcherkr.ui.screen.destinations.HomePatcherOptionsScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun HomePatcherCard(navigator: DestinationsNavigator, showConfirmRestoreDialog: (() -> Unit) -> Unit) {
    val context = LocalContext.current
    AssetsPatcherCardBase(
        navigator,
        showConfirmRestoreDialog,
        label = stringResource(R.string.home_patcher_label),
        icon = { Icon(Icons.Filled.Home, null) },
        translationsPath = HomePatcher.HOME_TRANSLATIONS_PATH,
        optionsScreenDest = HomePatcherOptionsScreenDestination,
        restorePatcher = { HomePatcher(restoreMode = true) },
        isRestoreAvailable = remember { HomePatcher.isRestoreAvailable(context) }
    )
}