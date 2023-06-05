package com.ktw.android_camera_client.di.core

import com.ktw.android_camera_client.domain.repository.CameraRepository
import com.ktw.android_camera_client.infrastructure.repository.camera.CameraRepositoryImpl
import com.ktw.android_camera_client.infrastructure.repository.camera.datasource.CameraLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCameraRepository(
        cameraLocalDataSource: CameraLocalDataSource
    ): CameraRepository {
        return CameraRepositoryImpl(cameraLocalDataSource)
    }
}