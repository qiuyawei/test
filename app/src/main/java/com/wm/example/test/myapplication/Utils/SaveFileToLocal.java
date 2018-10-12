package com.wm.example.test.myapplication.Utils;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Author:qyw
 * on 2018/9/17.
 * 描述：
 */
public class SaveFileToLocal {
    public static void saveImageToLocal(Bitmap bitmap) {
        FileOutputStream fileOutputStream;
        //首先要确定保存文件的目录
        String fileSaveDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        //确定文件的名字和后缀
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(fileSaveDir + "/" + fileName);
        if (file.exists()) {
            file.delete();
        } else {
            //检查上级目录是否存在，不存在则创建
            file.getParentFile().mkdirs();
        }
        //准备工作完成，创建改文件
        try {
            file.createNewFile();
            fileOutputStream=new FileOutputStream(file);
            //把文件流输入到文件，就是保存了
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
            //关闭流
            if(fileOutputStream!=null){
                fileOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
