package com.ktw.android_camera_client.infrastructure.repository.camera.datasource

import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner

interface CameraLocalDataSource {
    fun startCameraFromDevice(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
        lensFacing: Int
    )

    fun hasBackCamera(): Boolean

    fun hasFrontCamera(): Boolean

    fun switchCameraFromDevice()
}