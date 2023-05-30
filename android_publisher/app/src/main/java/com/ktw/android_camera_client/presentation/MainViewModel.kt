package com.ktw.android_camera_client.presentation

import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.vision.face.Face
import com.ktw.android_camera_client.domain.repository.CameraRepository
import com.ktw.android_camera_client.domain.repository.FaceDetectionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.Executor
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val cameraRepository: CameraRepository,
    private val faceDetectionRepository: FaceDetectionRepository
): ViewModel() {

    fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner
    ) {
        viewModelScope.launch {
            cameraRepository.showCameraPreview(
                previewView,
                lifecycleOwner,
            )
        }
    }

    fun setImageAnalysis(
        executor: Executor,
        lumaListener: (luma: Double) -> Unit
    ) {
        viewModelScope.launch {
            cameraRepository.setImageAnalysis(executor, lumaListener)
        }
    }

    fun setFaceDetector(
        executor: Executor,
        facesListener: (faces: List<Face>) -> Unit
    ) {
        viewModelScope.launch {
            faceDetectionRepository.getFacePosition(executor, facesListener)
        }
    }
}