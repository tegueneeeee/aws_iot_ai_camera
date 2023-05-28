package com.ktw.android_publisher.camera

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.camera.core.*
import androidx.camera.core.ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import com.ktw.android_publisher.core.util.Permission

@Composable
fun CameraCapture(
    modifier: Modifier = Modifier,
    cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA,
) {
    val context = LocalContext.current
    Permission(
        permission = Manifest.permission.CAMERA,
        rationalText = "I'm going to have to ask for permission of Camera",
        permissionNotAvailableContent = {
            Column(modifier) {
                Text("NoCamera!")
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        context.startActivity(
                            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                data = Uri.fromParts("package", context.packageName, null)
                            }
                        )
                    }
                ) {
                    Text("open Settings")
                }
            }
        }
    ) {
        Box(modifier = modifier) {
            val lifecycleOwner = LocalLifecycleOwner.current
            val coroutineScope = rememberCoroutineScope()
            var previewUseCase by remember {
                mutableStateOf<UseCase>(Preview.Builder().build())
            }
            var imageAnalyzerUseCase by remember {
                mutableStateOf<UseCase>(ImageAnalysis.Builder().build())
            }
            Box {
                CameraPreview(
                    onUseCase = {
                        previewUseCase = it
                    }
                )
                ImageAnalyzer(
                    onUseCase = {
                        imageAnalyzerUseCase = it
                    }
                )
            }
            LaunchedEffect(previewUseCase, imageAnalyzerUseCase) {
                val cameraProvider = context.getCameraProvider()
                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner, cameraSelector, previewUseCase, imageAnalyzerUseCase
                    )
                } catch (ex: Exception) {
                    Log.e("CameraCapture", "Failed to bind camera use cases", ex)
                }
            }
        }

    }
}