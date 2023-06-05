package com.ktw.android_camera_client.infrastructure.repository.camera.datasourceImpl

import androidx.camera.core.CameraInfoUnavailableException
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.ktw.android_camera_client.infrastructure.repository.camera.datasource.CameraLocalDataSource

class CameraLocalDataSourceImpl(
    private val cameraProvider: ProcessCameraProvider,
    private val preview: Preview,
    private val imageAnalysis: ImageAnalysis,
    private val selector: CameraSelector,
): CameraLocalDataSource {
    override fun startCameraFromDevice(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
        lensFacing: Int
    ) {
        try {
            val selector = CameraSelector.Builder()
                .requireLensFacing(lensFacing)
                .build()
            preview.setSurfaceProvider(previewView.surfaceProvider)
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                selector,
                preview,
                imageAnalysis
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun hasBackCamera(): Boolean {
        try {
            return cameraProvider.hasCamera(CameraSelector.DEFAULT_BACK_CAMERA)
        } catch (e: CameraInfoUnavailableException) {
            return false
        }
    }

    override fun hasFrontCamera(): Boolean {
        try {
            return cameraProvider.hasCamera(CameraSelector.DEFAULT_FRONT_CAMERA)
        } catch (e: CameraInfoUnavailableException) {
            return false
        }
    }

    override fun switchCameraFromDevice() {
        cameraProvider.unbindAll()

//        startCameraFromDevice()
    }
}