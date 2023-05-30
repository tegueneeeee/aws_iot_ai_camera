package com.ktw.android_camera_client.presentation

import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ktw.android_camera_client.domain.repository.CameraRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.Executor
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: CameraRepository
): ViewModel() {

    fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner
    ) {
        viewModelScope.launch {
            repository.showCameraPreview(
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
            repository.setImageAnalysis(executor, lumaListener)
        }
    }
}