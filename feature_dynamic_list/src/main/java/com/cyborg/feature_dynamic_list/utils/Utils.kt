package com.cyborg.feature_dynamic_list.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Environment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
@Throws(IOException::class)
fun createImageFile(context: Context): File {

  val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
  val imageFileName = "JPEG_" + timeStamp + "_"
  val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

  return File.createTempFile(imageFileName, ".jpg", storageDir)
}
