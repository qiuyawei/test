package com.wm.example.test.myapplication

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.dialog_view.view.*
import java.util.zip.Inflater

class DialogTestActivity : AppCompatActivity() {
    var dialog: AlertDialog? = null;
    var dialog2: Dialog? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_test)
        ButterKnife.bind(this)
    }


    @OnClick(R.id.bt_dialog)
    fun onClick(view: View) {
        when (view.id) {
            R.id.bt_dialog -> {
               Handler().postDelayed(Runnable {
                   if(dialog2!=null){
                       dialog2!!.dismiss()
                       Log.i("TAG","......................sssssss")
                   }
                   var view=View.inflate(applicationContext,R.layout.dialog_view,null)
                   view.tv_sure.setOnClickListener {
                       Log.i("TAG","?????????")
                       dialog2!!.dismiss()
                   }
                   dialog2= Dialog(this)
                   dialog2!!.setContentView(view)

                   dialog2!!.setCanceledOnTouchOutside(false)
                   dialog2!!.show()

                   //设置全屏
                   val window = dialog2!!.getWindow()
                   window!!.decorView.setPadding(0, 0, 0, 0)
                   window.setGravity(Gravity.BOTTOM)
                   val lp = window.attributes
                   lp.width = WindowManager.LayoutParams.FILL_PARENT
                   lp.height = WindowManager.LayoutParams.WRAP_CONTENT
                   window.attributes = lp

                   var tv_content=view.tv_content
               },1000)
            }
        }
    }
}
