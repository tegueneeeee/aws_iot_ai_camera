package com.ktw.android_camera_client.domain.repository

import androidx.camera.core.CameraSelector
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import java.util.concurrent.Executor

interface CameraRepository  {

    fun startCamera(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
        lensFacing: Int
    )

    fun checkCameraSwitchButtonEnable(): Boolean

    fun switchCamera()

}