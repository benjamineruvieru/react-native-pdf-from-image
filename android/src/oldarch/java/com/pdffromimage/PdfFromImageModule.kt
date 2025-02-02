package com.pdffromimage

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.module.annotations.ReactModule
import java.io.File

@ReactModule(name = PdfFromImageModuleImpl.NAME)
class PdfFromImageModule(
  context: ReactApplicationContext,
) : ReactContextBaseJavaModule(context) {
  private var implementation: PdfFromImageModuleImpl = PdfFromImageModuleImpl()

  override fun getName(): String = PdfFromImageModuleImpl.NAME

  @ReactMethod
  fun createPdf(
    imageObject: ReadableMap,
    promise: Promise,
  ) {
    val documentName = imageObject.getString("name") ?: "document"
    val documentFile = getTempFile(documentName)
    implementation.createPdf(imageObject, documentFile, promise)
  }

  private fun getTempFile(name: String): File {
    val outputDir = getReactApplicationContext().getExternalCacheDir()
    return File(outputDir, "$name.pdf")
  }
}
