package com.leadrdrk.umapatcherkr.ui.patcher

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.leadrdrk.umapatcherkr.R
import com.leadrdrk.umapatcherkr.core.GameChecker
import com.leadrdrk.umapatcherkr.patcher.AppPatcher
import com.leadrdrk.umapatcherkr.ui.component.LastCommitTimeEffect
import com.leadrdrk.umapatcherkr.ui.screen.destinations.AppPatcherOptionsScreenDestination
import com.leadrdrk.umapatcherkr.utils.showToast
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun AppPatcherCard(navigator: DestinationsNavigator) {
    val context = LocalContext.current
    val translationLastUpdated = remember { mutableStateOf("N/A") }
    val isMountInstalled = remember { AppPatcher.isMountInstalled(context) }
    var isApkMounted by remember { mutableStateOf(if (isMountInstalled) AppPatcher.isApkMounted(context) else false) }
    val isGameInstalled = remember { GameChecker.isPackageInstalled(context.packageManager) }
    val requestDocumentTree = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocumentTree()
    ) { result ->
        if (result != null) {
            PatcherLauncher.launch(context, navigator,
                AppPatcher(
                    fileUri = result,
                    runInstallData = true
                )
            )
        }
    }

    LastCommitTimeEffect(translationLastUpdated, AppPatcher.APP_TRANSLATIONS_PATH)

    PatcherCard(
        label = stringResource(R.string.app_patcher_label),
        icon = { Icon(painterResource(R.drawable.ic_apk_install), null) },
        buttons = {
            AssistChip(
                onClick = { navigator.navigate(AppPatcherOptionsScreenDestination) },
                label = { Text(stringResource(R.string.patch)) },
                leadingIcon = { Icon(Icons.Outlined.Build, stringResource(R.string.patch)) }
            )
            AssistChip(
                onClick = {
                    context.showToast(context.getString(R.string.allow_data_access_notice), Toast.LENGTH_LONG)
                    AppPatcher.requestDataPermission(requestDocumentTree)
                },
                label = { Text(stringResource(R.string.install_data)) },
                leadingIcon = { Icon(painterResource(R.drawable.ic_install_data), null) },
                enabled = isGameInstalled
            )
            if (isMountInstalled) {
                AssistChip(
                    onClick = {
                        if (isApkMounted) {
                            if (AppPatcher.unmountApk(context)) isApkMounted = false
                        }
                        else {
                            if (AppPatcher.runMountScript(context)) isApkMounted = true
                        }
                    },
                    label = {
                        Text(stringResource(
                            if (isApkMounted) R.string.unmount
                            else R.string.mount
                        ))
                    },
                    leadingIcon = {
                        Icon(painterResource(
                            if (isApkMounted) R.drawable.ic_eject
                            else R.drawable.ic_mount
                        ), null)
                    }
                )
            }
        }
    ) {
        Text(
            text = stringResource(R.string.translation_last_updated_prefix) + translationLastUpdated.value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}