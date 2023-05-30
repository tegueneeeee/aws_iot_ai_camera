package com.ktw.android_camera_client.infrastructure.repository

import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.ktw.android_camera_client.domain.repository.FaceDetectionRepository
import java.util.concurrent.Executor
import javax.inject.Inject


@ExperimentalGetImage
class FaceDetectionRepositoryImpl @Inject constructor(
    private val imageAnalysis: ImageAnalysis,
    private val faceDetectorOptions: FaceDetectorOptions
) : FaceDetectionRepository {


    override suspend fun getFacePosition(
        executor: Executor,
        facesListener: (faces: List<Face>) -> Unit
    ) {
        try {
            imageAnalysis.setAnalyzer(executor, ImageAnalysis.Analyzer { imageProxy ->
                val detector = FaceDetection.getClient(faceDetectorOptions)
                val mediaImage = imageProxy.image
                val rotationDegrees = imageProxy.imageInfo.rotationDegrees
                if (mediaImage != null) {
                    val image = InputImage.fromMediaImage(mediaImage, rotationDegrees)
                    detector.process(image).addOnSuccessListener {
                            faces -> facesListener(faces)
                    }.addOnFailureListener {
                            e -> e.printStackTrace()
                    }
                }
                imageProxy.close()
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}