package com.ktw.android_camera_client.di.core

import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import com.ktw.android_camera_client.infrastructure.repository.camera.datasource.CameraLocalDataSource
import com.ktw.android_camera_client.infrastructure.repository.camera.datasourceImpl.CameraLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideCameraLocalDataSource(
        cameraProvider: ProcessCameraProvider,
        preview: Preview,
        imageAnalysis: ImageAnalysis,
        selector: CameraSelector
    ): CameraLocalDataSource {
        return CameraLocalDataSourceImpl(
            cameraProvider,
            preview,
            imageAnalysis,
            selector
        )
    }
}