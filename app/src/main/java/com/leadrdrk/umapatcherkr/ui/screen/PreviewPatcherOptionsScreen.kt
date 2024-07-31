package com.leadrdrk.umapatcherkr.ui.screen

import androidx.compose.runtime.Composable
import com.leadrdrk.umapatcherkr.patcher.PreviewPatcher
import com.leadrdrk.umapatcherkr.ui.component.DefaultAssetsPatcherOptionsScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun PreviewPatcherOptionsScreen(navigator: DestinationsNavigator) {
    DefaultAssetsPatcherOptionsScreen(
        navigator = navigator,
        patcher = { skipMachineTl, nThreads, forcePatch, makeBackup ->
            PreviewPatcher(
                skipMachineTl,
                nThreads,
                forcePatch,
                makeBackup,
            )
        }
    )
}