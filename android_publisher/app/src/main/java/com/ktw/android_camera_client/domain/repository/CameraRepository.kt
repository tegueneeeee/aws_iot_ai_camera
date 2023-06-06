package com.ktw.android_camera_client.domain.repository

import androidx.camera.core.CameraSelector
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import java.util.concurrent.Executor

interface CameraRepository  {

    suspend fun startCamera(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
        lensFacing: Int
    )

    fun hasBackCamera(): Boolean

    fun hasFrontCamera(): Boolean
    fun currentLesFacing(): Int

}