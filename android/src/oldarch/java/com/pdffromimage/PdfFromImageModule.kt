package com.pdffromimage

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.WritableMap
import com.facebook.react.module.annotations.ReactModule
import java.io.File

@ReactModule(name = PdfFromImageModuleImpl.NAME)
class PdfFromImageModule(
  context: ReactApplicationContext,
) : ReactContextBaseJavaModule(context) {
  // declare an instance of the implementation and use it in all the methods
  private var implementation: PdfFromImageModuleImpl = PdfFromImageModuleImpl()

  override fun getName(): String = PdfFromImageModuleImpl.NAME

  @ReactMethod
  fun createPdf(imageObject: ReadableMap): WritableMap {
    val documentName = imageObject.getString("name") ?: "document"
    val documentFile = getTempFile(documentName)
    return implementation.createPdf(imageObject, documentFile)
  }

  private fun getTempFile(name: String): File {
    val outputDir = getReactApplicationContext().getExternalCacheDir()
    return File(outputDir, "$name.pdf")
  }
}
