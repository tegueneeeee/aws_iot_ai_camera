package com.ktw.android_publisher.camera

import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.UseCase
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import java.nio.ByteBuffer

typealias LumaListener = (luma: Double) -> Unit

@Composable
fun ImageAnalyzer(
    onUseCase: (UseCase) -> Unit = { }
) {
    AndroidView(
        factory = { context ->
            val analysisView = TextView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
            onUseCase(
                ImageAnalysis.Builder()
                    .build()
                    .also {
                        it.setAnalyzer(context.executor, LuminosityAnalyzer{ luma ->
                            Log.d("ImageAnalyzer", "Average luminosity: $luma")
                        })
                    }
            )
            analysisView
        }
    )
}

private class LuminosityAnalyzer(private val listener: LumaListener): ImageAnalysis.Analyzer {
    private fun ByteBuffer.toByteArray(): ByteArray {
        rewind()
        val data = ByteArray(remaining())
        get(data)
        return data
    }
    override fun analyze(image: ImageProxy) {

        val buffer = image.planes[0].buffer
        val data = buffer.toByteArray()
        val pixels = data.map { it.toInt() and 0xFF }
        val luma = pixels.average()

        listener(luma)

        image.close()
    }

}