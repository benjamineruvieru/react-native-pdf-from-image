package com.pdffromimage

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.WritableMap
import java.io.File

class PdfFromImageModule(
  reactContext: ReactApplicationContext,
) : NativePdfFromImageSpec(reactContext) {
  private var implementation: PdfFromImageModuleImpl = PdfFromImageModuleImpl()

  override fun getName(): String = PdfFromImageModuleImpl.NAME

  override fun createPdf(imageObject: ReadableMap): WritableMap {
    val documentName = imageObject.getString("name") ?: "document"
    val documentFile = getTempFile(documentName)
    return implementation.createPdf(imageObject, documentFile)
  }

  private fun getTempFile(name: String): File {
    val outputDir = getReactApplicationContext().getExternalCacheDir()
    return File(outputDir, "$name.pdf")
  }
}
