package com.ktw.android_camera_client.domain.repository

import com.google.mlkit.vision.face.Face
import java.util.concurrent.Executor

interface FaceDetectionRepository {
    suspend fun getFacePosition(
        executor: Executor,
        facesListener: (faces: List<Face>) -> Unit
    )
}