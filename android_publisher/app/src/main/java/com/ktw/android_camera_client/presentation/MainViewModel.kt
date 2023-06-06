package com.ktw.android_camera_client.presentation

import android.Manifest
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.view.Surface
import androidx.camera.core.CameraSelector
import androidx.camera.core.processing.SurfaceProcessorNode.In
import androidx.camera.view.PreviewView
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.mlkit.vision.face.Face
import com.ktw.android_camera_client.domain.repository.CameraRepository
import com.ktw.android_camera_client.domain.usecase.camera.CheckCameraSwitchButtonUseCase
import com.ktw.android_camera_client.domain.usecase.camera.StartCameraUseCase
import com.ktw.android_camera_client.domain.usecase.camera.SwitchCameraUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.Executor
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalPermissionsApi::class)
class MainViewModel @Inject constructor(
    private val startCameraUseCase: StartCameraUseCase,
//    private val checkCameraSwitchButtonUseCase: CheckCameraSwitchButtonUseCase,
    private val switchCameraUseCase: SwitchCameraUseCase
): ViewModel() {

    private val _previewView = mutableStateOf<PreviewView?>(null)
    val previewView: State<PreviewView?> get() = _previewView
    fun setPreviewView(context: Context) {
        _previewView.value = PreviewView(context)
    }

    private val _lifecycleOwner = mutableStateOf<LifecycleOwner?>(null)
    fun setLifecycleOwner(lifecycleOwner: LifecycleOwner) {
        _lifecycleOwner.value = lifecycleOwner
    }

    private val _cameraPermissionState = mutableStateOf<PermissionState?>(null)
    val cameraPermissionState: State<PermissionState?> get() = _cameraPermissionState
    fun setCameraPermissionState(cameraPermissionState: PermissionState) {
        _cameraPermissionState.value = cameraPermissionState
    }

    private val _screenHeight = mutableStateOf<Dp?>(null)
    val screenHeight: State<Dp?> get() = _screenHeight
    fun setScreenHeight(configuration: Configuration) {
        _screenHeight.value = configuration.screenHeightDp.dp
    }

    private val _screenWidth = mutableStateOf<Dp?>(null)
    val screenWidth: State<Dp?> get() = _screenWidth
    fun setScreenWidth(configuration: Configuration) {
        _screenWidth.value = configuration.screenWidthDp.dp
    }

    private val _rotation = mutableStateOf(Surface.ROTATION_0)
    val rotation: State<Int> get() = _rotation
    fun setRotation(rotation: Int) {
        _rotation.value = rotation
    }


    fun startCamera() {
        viewModelScope.launch {
            startCameraUseCase.execute(
                _previewView.value!!,
                _lifecycleOwner.value!!
            )
        }
    }

    fun switchCamera() {
        viewModelScope.launch {
            switchCameraUseCase.execute(
                _previewView.value!!,
                _lifecycleOwner.value!!
            )
        }
    }
}