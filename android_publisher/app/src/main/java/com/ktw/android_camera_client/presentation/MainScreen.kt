package com.ktw.android_camera_client.presentation

import android.Manifest
import android.content.Context
import android.util.Log
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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val cameraPermissionState = rememberPermissionState(
        Manifest.permission.CAMERA
    )

    val lifecycleOwner = LocalLifecycleOwner.current
    val configuration = LocalConfiguration.current
    val context = LocalContext.current
    val executor = ContextCompat.getMainExecutor(context)
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    var previewView: PreviewView
    var analysisView: TextView

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (cameraPermissionState.status.isGranted) {
            Box(
                modifier = Modifier
                    .height(screenHeight * 0.85f)
                    .width(screenWidth)
            ) {
                AndroidView(
                    factory = {
                        previewView = PreviewView(it)
                        viewModel.showCameraPreview(previewView, lifecycleOwner)
                        previewView
                    },
                    modifier = Modifier
                        .height(screenHeight * 0.85f)
                        .width(screenWidth)
                )
//                AndroidView(
//                    factory = {
//                        analysisView = TextView(it)
//                        viewModel.setImageAnalysis(executor) {
//                            luma -> Log.d("ImageAnalyzer", "Average luminosity: $luma")
//                        }
//                        analysisView
//                    }
//                )
                AndroidView(
                    factory = {
                        analysisView = TextView(it)
                        viewModel.setFaceDetector(executor) { faces ->
                            for (face in faces) {
                                Log.d("ImageAnalyzer", "Bounds: ${face.boundingBox}")
                            }
                        }
                        analysisView
                    }
                )
            }
        } else {
            Column {
                val textToShow = if (cameraPermissionState.status.shouldShowRationale) {
                    "The camera is important for this app. Please grant the permission."
                } else {
                    "Camera permission required for this feature to be available. " +
                            "Please grant the permission"
                }
                Text(textToShow)
                Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
                    Text("Request permission")
                }
            }
        }
    }
}
