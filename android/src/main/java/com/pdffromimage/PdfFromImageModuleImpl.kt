package com.pdffromimage

import android.graphics.BitmapFactory
import android.graphics.pdf.PdfDocument
import android.graphics.pdf.PdfDocument.PageInfo
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.WritableMap
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import com.facebook.react.bridge.Promise

class PdfFromImageModuleImpl {

  fun createPdf(
    imageObject: ReadableMap,
    documentFile: File,
    promise: Promise? = null
  ):WritableMap  {
    val resultMap: WritableMap = Arguments.createMap()

    try {
      val imagePaths = imageObject.getArray("imagePaths")
      val documentName = imageObject.getString("name") ?: "document"

      val document = PdfDocument()

      // Determine paper size
      val paperSize =
        if (imageObject.hasKey("paperSize")) {
          val paperSizeMap = imageObject.getMap("paperSize")
          val width = paperSizeMap?.getDouble("width")?.toInt() ?: 0
          val height = paperSizeMap?.getDouble("height")?.toInt() ?: 0
          Pair(width, height)
        } else {
          Pair(0, 0) // Default to zero if no paper size is provided
        }
      if (imagePaths != null) {
        for (i in 0 until imagePaths.size()) {
          val imagePath = imagePaths.getString(i)

          val bitmap = BitmapFactory.decodeFile(imagePath)

          if (bitmap != null) {
            val pageWidth = if (paperSize.first > 0) paperSize.first else bitmap.width
            val pageHeight = if (paperSize.second > 0) paperSize.second else bitmap.height
            val pageInfo = PageInfo.Builder(pageWidth, pageHeight, i + 1).create()
            val page = document.startPage(pageInfo)
            val canvas = page.canvas

            if (paperSize.first > 0 && paperSize.second > 0) {
              // Calculate scaling factors
              val widthRatio = pageWidth.toFloat() / bitmap.width
              val heightRatio = pageHeight.toFloat() / bitmap.height
              val scale = minOf(widthRatio, heightRatio)

              val scaledWidth = bitmap.width * scale
              val scaledHeight = bitmap.height * scale
              val xOffset = (pageWidth - scaledWidth) / 2
              val yOffset = (pageHeight - scaledHeight) / 2

              canvas.save()
              canvas.translate(xOffset, yOffset)
              canvas.scale(scale, scale)
              canvas.drawBitmap(bitmap, 0f, 0f, null)
              canvas.restore()
            } else {
              // Draw image at its original size
              canvas.drawBitmap(bitmap, 0f, 0f, null)
            }

            document.finishPage(page)
            bitmap.recycle()
          }
        }
      }

      document.writeTo(FileOutputStream(documentFile))
      document.close()

      // create result map
      val filePath = documentFile.path
      resultMap.putString("filePath", filePath)
    } catch (e: FileNotFoundException) {
      e.printStackTrace()
    } catch (e: IOException) {
      e.printStackTrace()
    } catch (e: Exception) {
      e.printStackTrace()
    }

    
    if (promise != null) {
      promise.resolve(resultMap)
    }
      return resultMap
    


  }

  companion object {
    const val NAME = "PdfFromImage"
  }
}
