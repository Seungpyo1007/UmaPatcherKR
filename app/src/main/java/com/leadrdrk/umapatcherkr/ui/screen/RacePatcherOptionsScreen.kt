package com.leadrdrk.umapatcherkr.ui.screen

import androidx.compose.runtime.Composable
import com.leadrdrk.umapatcherkr.patcher.RacePatcher
import com.leadrdrk.umapatcherkr.ui.component.DefaultAssetsPatcherOptionsScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun RacePatcherOptionsScreen(navigator: DestinationsNavigator) {
    DefaultAssetsPatcherOptionsScreen(
        navigator = navigator,
        patcher = { skipMachineTl, nThreads, forcePatch, makeBackup ->
            RacePatcher(
                skipMachineTl,
                nThreads,
                forcePatch,
                makeBackup,
            )
        }
    )
}