package com.wm.example.test.myapplication

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.wm.example.test.myapplication.Utils.DisplayUtil

class TestDpAndPxActivity : AppCompatActivity() {
    @BindView(R.id.et_dpValue)
    lateinit var et_dpValue: EditText;
    @BindView(R.id.tv_convertValue)
    lateinit var tv_convertValue:TextView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_dp_and_px)
        ButterKnife.bind(this)
    }

    //静态方法
    companion object {
        fun lanuchActivity(activity: Activity) {
            var intent = Intent(activity, TestDpAndPxActivity::class.java)
            activity.startActivity(intent)
        }
    }

    @OnClick(R.id.bt_convert)
    fun onClick(view:View) {
        when (view.id) {
            R.id.bt_convert -> {
                var value = et_dpValue.text.toString().toInt()
                Log.i("TAG","value:"+DisplayUtil.dip2px(value.toDouble()))
                var newValue = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value.toFloat(), resources.displayMetrics)
                tv_convertValue.setText(newValue.toString())
            }
        }
    }
}
