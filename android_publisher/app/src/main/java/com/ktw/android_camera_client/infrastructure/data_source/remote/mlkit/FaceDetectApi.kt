package com.ktw.android_camera_client.infrastructure.data_source.remote.mlkit

import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.ktw.android_camera_client.domain.repository.CameraRepository
import com.ktw.android_camera_client.domain.repository.FaceDetectionRepository
import com.ktw.android_camera_client.infrastructure.repository.CameraRepositoryImpl
import com.ktw.android_camera_client.infrastructure.repository.FaceDetectionRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FaceDetectApi {

    @Provides
    @Singleton
    fun provideFaceDetectorOptions(): FaceDetectorOptions {
        return FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
            .setMinFaceSize(0.15f)
            .enableTracking()
            .build()
    }

    @Provides
    @Singleton
    fun provideFaceDetectionRepository(
        imageAnalysis: ImageAnalysis,
        faceDetectorOptions: FaceDetectorOptions
    ): FaceDetectionRepository {
        return FaceDetectionRepositoryImpl(
            imageAnalysis,
            faceDetectorOptions
        )
    }

}