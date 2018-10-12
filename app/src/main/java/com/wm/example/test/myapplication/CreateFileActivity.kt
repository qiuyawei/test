package com.wm.example.test.myapplication

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatCallback
import android.util.Log
import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick
import java.io.File

class CreateFileActivity : AppCompatActivity(), AppCompatCallback {
    var permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_file)
        ButterKnife.bind(this)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            createNewFolder()
        }
    }


    @OnClick(R.id.bt_cretate)
    fun onClick(view: View) {
        when (view.id) {
            R.id.bt_cretate -> {
                //检查权限 6.0
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    var permison_value = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    Log.i("TAG", "permison_value:" + permison_value)
                    if (permison_value == PackageManager.PERMISSION_DENIED) {
                        requestPermissions(permissions, 100)
                    } else {
                        createNewFolder()
                    }
                } else {
                    createNewFolder()
                }

            }
        }
    }

    //创建新的文件夹，名字随机
    fun createNewFolder() {
        //根目录
        var parentDirectory = Environment.getExternalStorageDirectory().absolutePath;
        Log.i("TAG", "parentDirectory:" + parentDirectory)
        var newFolder = File(parentDirectory + "/qiuyawei3/" + "d/"+"a.txt");
        Log.i("TAG", "newFolderName:" + newFolder.absolutePath)
        //创建目录
        if (newFolder.exists()) {
            Log.i("TAG", "目录已经有了，无需重复创建")
        } else{
            newFolder.parentFile.mkdirs()
        }
        //目录有了 接着创建文件
        newFolder.createNewFile()

    }



}
