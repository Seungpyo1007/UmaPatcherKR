package com.leadrdrk.umapatcherkr.ui.patcher

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.leadrdrk.umapatcherkr.R
import com.leadrdrk.umapatcherkr.patcher.StoryPatcher
import com.leadrdrk.umapatcherkr.ui.screen.destinations.StoryPatcherOptionsScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun StoryPatcherCard(navigator: DestinationsNavigator, showConfirmRestoreDialog: (() -> Unit) -> Unit) {
    val context = LocalContext.current
    AssetsPatcherCardBase(
        navigator,
        showConfirmRestoreDialog,
        label = stringResource(R.string.story_patcher_label),
        icon = { Icon(painterResource(R.drawable.ic_message), null) },
        translationsPath = StoryPatcher.STORY_TRANSLATIONS_PATH,
        optionsScreenDest = StoryPatcherOptionsScreenDestination,
        restorePatcher = { StoryPatcher(restoreMode = true) },
        isRestoreAvailable = remember { StoryPatcher.isRestoreAvailable(context) }
    )
}