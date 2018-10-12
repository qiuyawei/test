package com.wm.example.test.myapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AppCompatCallback
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.wm.example.test.myapplication.Utils.FileUtil
import java.net.HttpURLConnection
import java.net.URL

class NetBaseRequestTestActivity : AppCompatActivity() ,AppCompatCallback{
    @BindView(R.id.imageView)
    public lateinit var imageView: ImageView;
    @BindView(R.id.toolBar)
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net_base_request_test)
        ButterKnife.bind(this)
        toolbar.setNavigationIcon(R.mipmap.dilog_pin_back)
        toolbar.title="标题"
        toolbar.setLogo(R.mipmap.ic_launcher)
        toolbar.left=100
        toolbar.right=100
        toolbar.navigationContentDescription="返回"
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })
//        getNetImage();
        getNetdata()
    }

    //静态方法
    companion object {
        //跳转
        fun lanuchActivity(activity: MainActivity) {
            var intent: Intent = Intent();
            intent.setClass(activity, NetBaseRequestTestActivity::class.java)
            activity.startActivity(intent)
        }
    }


    fun getNetImage() {
        Thread(
                Runnable {
                    var url = URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536896765&di=8578294a6b9cb1d7f867af6fe45cb58b&imgtype=jpg&er=1&src=http%3A%2F%2Fpic16.photophoto.cn%2F20100717%2F0036036381650739_b.jpg");
                    var connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                    connection.requestMethod = "GET"
                    connection.readTimeout = 10 * 1000
                    connection.connectTimeout = 10 * 1000
                    connection.connect()

                    var inputStrem = connection.inputStream
                    var bitmap = BitmapFactory.decodeStream(inputStrem)
                    inputStrem.close()

                    //ui线程更新ui
                    if (bitmap != null) {
                        runOnUiThread(Runnable {
                            imageView.setImageBitmap(bitmap)
                        })

                    }
                }
        ).start()
    }

    var bitmap: Bitmap? = null;
    //抽象类的初始化
    fun getNetdata() {
        var asyncTask = object : AsyncTask<String, Int, String>() {
            override fun doInBackground(vararg params: String?): String {
                var url = URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536896765&di=8578294a6b9cb1d7f867af6fe45cb58b&imgtype=jpg&er=1&src=http%3A%2F%2Fpic16.photophoto.cn%2F20100717%2F0036036381650739_b.jpg");
                var connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.readTimeout = 10 * 1000
                connection.connectTimeout = 10 * 1000
                connection.connect()

                var inputStrem = connection.inputStream
                bitmap = BitmapFactory.decodeStream(inputStrem)
                inputStrem.close()

                return "";
            }

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                imageView.setImageBitmap(bitmap);
                if(bitmap!=null){
                     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                         var permissionVarlue= checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                         if(permissionVarlue==PackageManager.PERMISSION_DENIED){
                             requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),100)
                         }else{
                             FileUtil.compressBitmapFile(bitmap!!,applicationContext);
                         }
                    } else {
                        TODO("VERSION.SDK_INT < M")
                    };
                }
            }

        };

        asyncTask.execute();
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
            FileUtil.compressBitmapFile(bitmap!!,applicationContext);
        }
    }

}
