package com.leadrdrk.umapatcherkr.ui.patcher

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.leadrdrk.umapatcherkr.R
import com.leadrdrk.umapatcherkr.patcher.LyricsPatcher
import com.leadrdrk.umapatcherkr.ui.screen.destinations.LyricsPatcherOptionsScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun LyricsPatcherCard(navigator: DestinationsNavigator, showConfirmRestoreDialog: (() -> Unit) -> Unit) {
    val context = LocalContext.current
    AssetsPatcherCardBase(
        navigator,
        showConfirmRestoreDialog,
        label = stringResource(R.string.lyrics_patcher_label),
        icon = { Icon(painterResource(R.drawable.ic_lyrics), null) },
        translationsPath = LyricsPatcher.LYRICS_TRANSLATIONS_PATH,
        optionsScreenDest = LyricsPatcherOptionsScreenDestination,
        restorePatcher = { LyricsPatcher(restoreMode = true) },
        isRestoreAvailable = remember { LyricsPatcher.isRestoreAvailable(context) }
    )
}