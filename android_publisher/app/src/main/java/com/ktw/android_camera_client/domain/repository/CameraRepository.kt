package com.ktw.android_camera_client.domain.repository

import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import java.util.concurrent.Executor

interface CameraRepository  {
    suspend fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner
    )

    suspend fun setImageAnalysis(
        executor: Executor,
        lumaListener: (luma: Double) -> Unit
    )
}