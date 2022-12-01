package com.example.studyapplication.util

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.text.TextUtils
import java.io.File
import java.io.FileOutputStream

object FileUtil {

    var sAlbumDirPath: String? = null
    const val ALBUM_SAVE_PATH = "share_content_cache"

    const val CACHE_SAVE_PATH = "share_content_cache"
    fun saveFile(str: String?, filePath: String) {
        if (str == null) return
        val file = File(filePath)
        if (!file.exists()) {
            val dir = File(file.parent!!)
            dir.mkdirs()
            file.createNewFile()
        }
        val outStream = FileOutputStream(file)
        outStream.write(str.toByteArray())
        outStream.close()
    }

    fun delete(filePath:String){
        val file =  File(filePath)
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile) {
            file.delete()
        }
    }

    fun getAlbumDirPath(context:Context): String? {
        if (!TextUtils.isEmpty(sAlbumDirPath)) {
            return sAlbumDirPath
        }

        // android Q开始分区存储，只能访问应用自身专有目录
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            //File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DCIM), CACHE_SAVE_PATH);
            val file = context.getExternalFilesDir(Environment.DIRECTORY_DCIM)
            if (file != null && file.exists()) {
                //file.mkdirs();
                sAlbumDirPath = file.absolutePath
            }
            return sAlbumDirPath
        }
        val dcimDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
        val camera = File(dcimDir, "Camera")
        if (camera.exists()) {
            sAlbumDirPath = camera.absolutePath
            return sAlbumDirPath
        }
        val file = File(dcimDir, "100MEDIA")
        if (file.exists()) {
            sAlbumDirPath = file.absolutePath
            return sAlbumDirPath
        }
        val sonyDCIMDir = File(dcimDir, "100ANDRO")
        if (sonyDCIMDir.exists()) {
            sAlbumDirPath = sonyDCIMDir.absolutePath
            return sAlbumDirPath
        }
        val articleDirPath = Environment.getExternalStorageDirectory().absolutePath +
                File.separator + ALBUM_SAVE_PATH
        val articleDir = File(articleDirPath)
        if (!articleDir.exists()) {
            articleDir.mkdirs()
        }
        sAlbumDirPath = articleDirPath
        return sAlbumDirPath
    }

    fun getCacheFilePathDir(context: Context): String? {
        val tempCacheFile = File(context.externalCacheDir, CACHE_SAVE_PATH)
        if (!tempCacheFile.exists()) {
            tempCacheFile.mkdirs()
        }
        return tempCacheFile.path
    }


    fun saveBitmapToSD(bitmap: Bitmap, dir: String?, savePath: String): Boolean {
        if (TextUtils.isEmpty(dir) || TextUtils.isEmpty(savePath)) {
            return false
        }
        return if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            val path = File(dir)
            if (!path.exists()) {
                path.mkdirs()
            }
            val saveFile = File("$path/$savePath")
            if (!saveFile.exists()) {
                try {
                    saveFile.createNewFile()
                } catch (e: Exception) {
                    e.printStackTrace()
                    return false
                }
            }
            var fileOutputStream: FileOutputStream? = null
            try {
                fileOutputStream = FileOutputStream(saveFile)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
                fileOutputStream.flush()
            } catch (e: Exception) {
                e.printStackTrace()
                return false
            } finally {
                try {
                    fileOutputStream!!.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            true
        } else {
            false
        }
    }

    fun saveBitmapToSDTest(bitmap: Bitmap, dir: String?, savePath: String,quality:Int): Boolean {
        if (TextUtils.isEmpty(dir) || TextUtils.isEmpty(savePath)) {
            return false
        }
        return if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            val path = File(dir)
            if (!path.exists()) {
                path.mkdirs()
            }
            val saveFile = File("$path/$savePath")
            if (!saveFile.exists()) {
                try {
                    saveFile.createNewFile()
                } catch (e: Exception) {
                    e.printStackTrace()
                    return false
                }
            }
            var fileOutputStream: FileOutputStream? = null
            try {
                fileOutputStream = FileOutputStream(saveFile)
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, fileOutputStream)
                fileOutputStream.flush()
            } catch (e: Exception) {
                e.printStackTrace()
                return false
            } finally {
                try {
                    fileOutputStream!!.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            true
        } else {
            false
        }
    }

}