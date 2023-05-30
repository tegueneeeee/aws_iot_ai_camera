package com.ktw.android_camera_client.infrastructure.repository

import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.ktw.android_camera_client.domain.repository.CameraRepository
import java.nio.ByteBuffer
import java.util.concurrent.Executor
import javax.inject.Inject

class CameraRepositoryImpl @Inject constructor(
    private val cameraProvider: ProcessCameraProvider,
    private val selector: CameraSelector,
    private val preview: Preview,
    private val imageAnalysis: ImageAnalysis,
) : CameraRepository {
    override suspend fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
    ) {
        try {
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

    override suspend fun setImageAnalysis(
        executor: Executor,
        lumaListener: (luma: Double) -> Unit
    ) {
        try {
            imageAnalysis.setAnalyzer(executor, ImageAnalysis.Analyzer { image ->
                fun ByteBuffer.toByteArray(): ByteArray {
                    rewind()
                    val data = ByteArray(remaining())
                    get(data)
                    return data
                }
                val buffer = image.planes[0].buffer
                val data = buffer.toByteArray()
                val pixels = data.map { it.toInt() and 0xFF }
                val luma = pixels.average()
                lumaListener(luma)
                image.close()
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}