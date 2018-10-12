package com.wm.example.test.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife

class MyTestViewActivity : AppCompatActivity() {
    @BindView(R.id.tv_versionCode)
    lateinit var tv_versionCode:TextView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_test_view)
        ButterKnife.bind(this)
        var packageManager=packageManager;
        var packageInfo=packageManager.getPackageInfo(packageName,0);
        var version_name=packageInfo.versionName;
        var version_code=packageInfo.versionCode;
        tv_versionCode.text=version_name+"==="+version_code
    }
}
