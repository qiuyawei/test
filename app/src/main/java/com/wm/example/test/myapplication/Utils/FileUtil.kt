package com.wm.example.test.myapplication.Utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.util.Log
import java.io.*

/**
 * Created by Author:qyw
on 2018/9/13.
描述：
 */
object FileUtil {
    fun compressFile(filePath: String, context: Context) {
        var fileOutputStream: FileOutputStream? = null
        var fileInputStream: FileInputStream? = null
        var bitmap: Bitmap? = null

        var newFilePath: String = Environment.getExternalStorageDirectory().absolutePath + AppUtil.getPackName(context) + System.currentTimeMillis().toString() + ".jpg";
        var orginFile = File(filePath);
        var newFile = File(newFilePath)
        if (!orginFile.exists()) {
            return;
        }
        if (!newFile.exists()) {
            newFile.parentFile.mkdirs()
        }
        fileOutputStream = FileOutputStream(newFile)
        bitmap = BitmapFactory.decodeFile(filePath)
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, fileOutputStream)

    }

    fun compressBitmapFile(bitmap: Bitmap, context: Context) {
        Log.i("TAG","compressBitmapFile")
        var newFilePath: String = Environment.getExternalStorageDirectory().absolutePath +"/qiuyawei_files/" + System.currentTimeMillis().toString() + ".jpg";
        var newFile = File(newFilePath)
        Log.i("TAG","newFilePath:"+newFilePath)
        //1.检查是否有目录，没有去创建
        if (!newFile.exists()) {
           var b= newFile.parentFile.mkdirs()
            Log.i("TAG","parent mkdir:"+b)
        }
        //2.创建该文件
        newFile.createNewFile();
        var fileOutputStream = FileOutputStream(newFile)
        var b = bitmap!!.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream)
        Log.i("TAG","compress result:"+b);
        fileOutputStream.close()
    }
}