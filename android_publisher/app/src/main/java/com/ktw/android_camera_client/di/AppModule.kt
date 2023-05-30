//package com.ktw.android_camera_client.di
//
//import android.app.Application
//import androidx.camera.core.CameraSelector
//import androidx.camera.core.ImageAnalysis
//import androidx.camera.core.Preview
//import androidx.camera.lifecycle.ProcessCameraProvider
//import com.ktw.android_camera_client.infrastructure.repository.CameraRepositoryImpl
//import com.ktw.android_camera_client.domain.repository.CameraRepository
//import com.ktw.android_camera_client.infrastructure.data_source.local.camera.CameraXApi
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import java.util.concurrent.Executor
//import java.util.concurrent.Executors
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
////    val cameraxApi = CameraXApi()
////
////    @Provides
////    @Singleton
////    fun provideCameraSelector(): CameraSelector {
////        return cameraxApi.getCameraSelector()
////    }
////
////    @Provides
////    @Singleton
////    fun provideCameraProvider(application: Application): ProcessCameraProvider {
////        return cameraxApi.getProcessCameraProvider(application)
////    }
////
////    @Provides
////    @Singleton
////    fun provideCameraPreview(): Preview {
////        return cameraxApi.getCameraPreview()
////    }
////
////    @Provides
////    @Singleton
////    fun provideImageAnalysis(executor: Executor): ImageAnalysis {
////        return cameraxApi.getImageAnalysis(Executors.newSingleThreadExecutor())
////    }
//
////    @Provides
////    @Singleton
////    fun provideCameraRepository(
////        cameraProvider: ProcessCameraProvider,
////        selector: CameraSelector,
////        preview: Preview,
////        imageAnalysis: ImageAnalysis
////    ): CameraRepository {
////        return CameraRepositoryImpl(
////            cameraProvider,
////            selector,
////            preview,
////            imageAnalysis
////        )
////    }
//
//}