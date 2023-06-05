package com.ktw.android_camera_client.di.core

import com.ktw.android_camera_client.domain.repository.CameraRepository
import com.ktw.android_camera_client.domain.usecase.camera.CheckCameraSwitchButtonUseCase
import com.ktw.android_camera_client.domain.usecase.camera.StartCameraUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideStartCameraUseCase(cameraRepository: CameraRepository): StartCameraUseCase {
        return StartCameraUseCase(cameraRepository)
    }

    @Provides
    fun provideCheckCameraSwitchButtonUseCase(cameraRepository: CameraRepository): CheckCameraSwitchButtonUseCase {
        return CheckCameraSwitchButtonUseCase(cameraRepository)
    }
}