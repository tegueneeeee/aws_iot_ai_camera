package com.ktw.android_camera_client.domain.usecase.camera

import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraSelector.LensFacing
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.ktw.android_camera_client.domain.repository.CameraRepository

class StartCameraUseCase(private val cameraRepository: CameraRepository) {
    suspend fun execute(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
    ) {
        val lensFacing = when {
            cameraRepository.hasBackCamera() -> CameraSelector.LENS_FACING_BACK
            cameraRepository.hasFrontCamera() -> CameraSelector.LENS_FACING_FRONT
            else -> throw IllegalStateException("Back and Front camera are unavailable")
        }
        return cameraRepository.startCamera(
            previewView,
            lifecycleOwner,
            lensFacing
        )
    }
}