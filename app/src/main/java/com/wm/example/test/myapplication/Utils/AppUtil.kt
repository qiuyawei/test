package com.wm.example.test.myapplication.Utils

import android.content.Context
import android.util.Log

/**
 * Created by Author:qyw
on 2018/9/13.
描述：
 */
object AppUtil {
    fun getPackName(context:Context):String{
        var packManager=context.packageManager;
        var packInfor=packManager.getPackageInfo(context.packageName,0);
        var packName=packInfor.packageName;
        Log.i("TAG","packName:"+packName);
        return packName;
    }
}