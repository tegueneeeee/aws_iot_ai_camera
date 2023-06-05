package com.ktw.android_camera_client.presentation

import androidx.camera.core.CameraSelector
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.vision.face.Face
import com.ktw.android_camera_client.domain.repository.CameraRepository
import com.ktw.android_camera_client.domain.usecase.camera.CheckCameraSwitchButtonUseCase
import com.ktw.android_camera_client.domain.usecase.camera.StartCameraUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.Executor
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val startCameraUseCase: StartCameraUseCase,
//    private val checkCameraSwitchButtonUseCase: CheckCameraSwitchButtonUseCase
): ViewModel() {
    fun startCamera(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
    ) {
        viewModelScope.launch {
            startCameraUseCase.execute(
                previewView,
                lifecycleOwner
            )
        }
    }
}