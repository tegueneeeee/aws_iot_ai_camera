package com.ktw.android_camera_client.domain.usecase.camera

import androidx.camera.core.CameraSelector
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.ktw.android_camera_client.domain.repository.CameraRepository

class SwitchCameraUseCase(private val cameraRepository: CameraRepository) {

    suspend fun execute(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner
    ) {
        var lensFacing = cameraRepository.currentLesFacing()
        if (lensFacing.equals(CameraSelector.LENS_FACING_BACK)) {
            lensFacing = CameraSelector.LENS_FACING_FRONT
        } else {
            lensFacing = CameraSelector.LENS_FACING_BACK
        }
        return cameraRepository.startCamera(
            previewView,
            lifecycleOwner,
            lensFacing
        )
    }
}