package com.ktw.android_camera_client.infrastructure.repository.camera

import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraSelector.LensFacing
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.ktw.android_camera_client.domain.repository.CameraRepository
import com.ktw.android_camera_client.infrastructure.repository.camera.datasource.CameraLocalDataSource
import java.nio.ByteBuffer
import java.util.concurrent.Executor
import javax.inject.Inject

class CameraRepositoryImpl (
    private val cameraLocalDataSource: CameraLocalDataSource
) : CameraRepository {
    override suspend fun startCamera(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
        lensFacing: Int
    ) {
        cameraLocalDataSource.startCameraFromDevice(
            previewView,
            lifecycleOwner,
            lensFacing
        )
    }

    override fun hasBackCamera(): Boolean {
        return cameraLocalDataSource.hasBackCamera()
    }

    override fun hasFrontCamera(): Boolean {
        return cameraLocalDataSource.hasFrontCamera()
    }

    override fun currentLesFacing(): Int {
        return cameraLocalDataSource.getLensFacing()
    }


}