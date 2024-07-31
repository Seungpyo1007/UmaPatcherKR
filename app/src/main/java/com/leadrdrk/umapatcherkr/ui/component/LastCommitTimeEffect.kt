package com.leadrdrk.umapatcherkr.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import com.leadrdrk.umapatcherkr.R
import com.leadrdrk.umapatcherkr.git.GitRepo
import com.leadrdrk.umapatcherkr.utils.unixTimestampToString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun LastCommitTimeEffect(state: MutableState<String>, path: String) {
    val loadingStr = stringResource(R.string.loading)
    LaunchedEffect(
        key1 = GitRepo.ready.value,
        key2 = GitRepo.lastSynced.longValue
    ) {
        withContext(Dispatchers.IO) {
            if (GitRepo.ready.value) {
                state.value = loadingStr
                val time = GitRepo.getLastCommitTimeOfPath(path)
                state.value = if (time != null)
                    unixTimestampToString(time) else "N/A"
            }
        }
    }
}