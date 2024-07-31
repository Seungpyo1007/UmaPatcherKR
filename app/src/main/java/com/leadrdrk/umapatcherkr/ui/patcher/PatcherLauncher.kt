package com.leadrdrk.umapatcherkr.ui.patcher

import android.content.Context
import com.leadrdrk.umapatcherkr.MainActivity
import com.leadrdrk.umapatcherkr.git.GitRepo
import com.leadrdrk.umapatcherkr.patcher.Patcher
import com.leadrdrk.umapatcherkr.ui.screen.destinations.PatchingScreenDestination
import com.leadrdrk.umapatcherkr.utils.getActivity
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object PatcherLauncher {
    var patcher: Patcher? = null
    var patching = false

    fun launch(context: Context, navigator: DestinationsNavigator, patcher: Patcher) {
        if (patching) return

        // Start syncing git repo if its not cloned already and let patching screen wait for it
        if (!GitRepo.ready.value && !GitRepo.syncing.value) {
            val activity = context.getActivity() as MainActivity
            activity.startRepoSync()
        }

        this.patcher = patcher
        navigator.navigate(PatchingScreenDestination)
    }
    
    suspend fun runPatcher(context: Context, callback: (Boolean) -> Unit) {
        val patcher = this.patcher
        if (patcher == null || patching) return

        val activity = context.getActivity() as MainActivity
        withContext(Dispatchers.IO) {
            activity.useKeepScreenOn {
                patching = true
                callback(patcher.safeRun(context))
                patching = false
            }
        }
    }
}