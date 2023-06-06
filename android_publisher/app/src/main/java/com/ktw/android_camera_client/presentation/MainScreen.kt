package com.ktw.android_camera_client.presentation

import android.Manifest
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    viewModel.setLifecycleOwner(lifecycleOwner)
    val cameraPermission = rememberPermissionState(Manifest.permission.CAMERA)
    viewModel.setCameraPermissionState(cameraPermission)
    val configuration = LocalConfiguration.current
    viewModel.setScreenHeight(configuration)
    viewModel.setScreenWidth(configuration)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (viewModel.cameraPermissionState.value!!.status.isGranted) {
            Box(
                modifier = Modifier
                    .height(viewModel.screenHeight.value!! * 0.85f)
                    .width(viewModel.screenWidth.value!!)
            ) {
                AndroidView(
                    factory = {
                        viewModel.setPreviewView(it)
                        viewModel.startCamera()
                        viewModel.previewView.value!!
                    },
                    update = {

                    },
                    modifier = Modifier
                        .height(viewModel.screenHeight.value!! * 0.85f)
                        .width(viewModel.screenWidth.value!!)
                )
                Button(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    onClick = {
                        viewModel.switchCamera()
                    }) {
                    
                }
            }
        } else {
            Column {
                val textToShow = if (viewModel.cameraPermissionState.value!!.status.shouldShowRationale) {
                    "The camera is important for this app. Please grant the permission."
                } else {
                    "Camera permission required for this feature to be available. " +
                            "Please grant the permission"
                }
                Text(textToShow)
                Button(onClick = { viewModel.cameraPermissionState.value!!.launchPermissionRequest() }) {
                    Text("Request permission")
                }
            }
        }
    }
}
