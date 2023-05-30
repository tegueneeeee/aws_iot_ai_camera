package com.ktw.android_camera_client.infrastructure.data_source.local.camera

import android.app.Application
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import com.ktw.android_camera_client.domain.repository.CameraRepository
import com.ktw.android_camera_client.infrastructure.repository.CameraRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CameraXApi {

    @Provides
    @Singleton
    fun provideCameraSelector(): CameraSelector {
        return CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()
    }

    @Provides
    @Singleton
    fun provideCameraProvider(application: Application): ProcessCameraProvider {
        return ProcessCameraProvider.getInstance(application).get()
    }

    @Provides
    @Singleton
    fun provideCameraPreview(): Preview {
        return Preview.Builder().build()
    }

    @Provides
    @Singleton
    fun provideImageAnalysis(): ImageAnalysis {
        return ImageAnalysis.Builder().build()
    }

    @Provides
    @Singleton
    fun provideCameraRepository(
        cameraProvider: ProcessCameraProvider,
        selector: CameraSelector,
        preview: Preview,
        imageAnalysis: ImageAnalysis
    ): CameraRepository {
        return CameraRepositoryImpl(
            cameraProvider,
            selector,
            preview,
            imageAnalysis
        )
    }

}