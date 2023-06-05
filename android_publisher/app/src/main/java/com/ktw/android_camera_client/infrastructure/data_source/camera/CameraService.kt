package com.ktw.android_camera_client.infrastructure.data_source.camera

import androidx.camera.core.CameraSelector

interface CameraService {

    suspend fun getCameraSelector(
        
    ): CameraSelector
}