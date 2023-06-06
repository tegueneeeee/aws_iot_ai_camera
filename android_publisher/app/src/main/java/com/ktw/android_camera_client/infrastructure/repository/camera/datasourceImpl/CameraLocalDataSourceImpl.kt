package com.ktw.android_camera_client.infrastructure.repository.camera.datasourceImpl

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.CameraInfo
import androidx.camera.core.CameraInfoUnavailableException
import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraSelector.LensFacing
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.ktw.android_camera_client.infrastructure.repository.camera.datasource.CameraLocalDataSource
import kotlin.properties.Delegates

class CameraLocalDataSourceImpl(
    private val cameraProvider: ProcessCameraProvider,
    private val preview: Preview,
    private val imageAnalysis: ImageAnalysis,
): CameraLocalDataSource {
    private lateinit var selector: CameraSelector
    private var currentLensFacing by Delegates.notNull<Int>()
    override suspend fun startCameraFromDevice(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
        lensFacing: Int
    ){
        try {
            currentLensFacing = lensFacing
            selector = CameraSelector.Builder()
                .requireLensFacing(currentLensFacing)
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

    override fun getLensFacing(): Int {
        return currentLensFacing
    }

    override fun switchCameraFromDevice() {
        cameraProvider.unbindAll()

//        startCameraFromDevice()
    }
}