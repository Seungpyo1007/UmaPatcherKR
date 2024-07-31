package com.leadrdrk.umapatcherkr.ui.screen

import androidx.compose.runtime.Composable
import com.leadrdrk.umapatcherkr.patcher.HomePatcher
import com.leadrdrk.umapatcherkr.ui.component.BaseStoryPatcherOptionsScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun HomePatcherOptionsScreen(navigator: DestinationsNavigator) {
    BaseStoryPatcherOptionsScreen(
        navigator = navigator,
        patcher = { skipMachineTl, nThreads, forcePatch, makeBackup, cps, fps ->
            HomePatcher(
                skipMachineTl,
                nThreads,
                forcePatch,
                makeBackup,
                restoreMode = false,
                cps,
                fps
            )
        }
    )
}