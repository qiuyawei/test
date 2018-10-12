package com.wm.example.test.myapplication

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.activity_scrolling.*

class ScrollingActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        ButterKnife.bind(this)
    }

    companion object {
        fun lanuchActivity(activity: Activity){
            var intent= Intent(activity,ScrollingActivity::class.java);
            activity.startActivity(intent)
        }
    }


   /* @OnClick(R.id.bt_test)
    fun onClick(view: View){
        when(view.id){
            R.id.bt_test->{
                Toast.makeText(this,"被点击了",Toast.LENGTH_LONG).show();
            }
        }
    }*/
}
