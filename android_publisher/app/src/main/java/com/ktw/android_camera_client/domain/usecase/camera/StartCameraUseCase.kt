package com.ktw.android_camera_client.domain.usecase.camera

import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraSelector.LensFacing
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.ktw.android_camera_client.domain.repository.CameraRepository

class StartCameraUseCase(private val cameraRepository: CameraRepository) {
    fun execute(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
    ) {
        val lensFacing = CameraSelector.LENS_FACING_BACK
        return cameraRepository.startCamera(
            previewView,
            lifecycleOwner,
            lensFacing
        )
    }
}