package com.wm.example.test.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    var permissions: Array<String> = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
    var int_value: Array<Int> = arrayOf(1, 2, 3)
    @BindView(R.id.bt_one)
    lateinit var bt_one: Button;

    @BindView(R.id.bt_two)
    lateinit var bt_two: Button;
    var unbinder: Unbinder? = null;
    var max_value = 100;
    var countDownTimer: CountDownTimer? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        //注册ButterKnife
//        unbinder = ButterKnife.bind(this)
        innitCounter()
        Log.i("TAG", "packName:" + this.packageName)
        System.out.print("packName:" + this.packageName)
    }

    @OnClick(R.id.bt_one, R.id.bt_two, R.id.bt_three, R.id.bt_four, R.id.bt_five, R.id.bt_six, R.id.bt_seven, R.id.bt_eight,
            R.id.bt_nine,R.id.bt_ten,R.id.bt_eleven,R.id.bt_tweleve,R.id.bt_thirten)
    public fun onClick(view: View) {
        when (view.id) {
            R.id.bt_one -> {
                NetBaseRequestTestActivity.lanuchActivity(this)
            }
            R.id.bt_two -> {
                this.countDownTimer!!.start()
            }
            R.id.bt_three -> {
                startActivity(Intent(this, MyTestViewActivity::class.java))
            }
            R.id.bt_four -> {
                startActivity(Intent(this, BannerActivity::class.java))
            }
            R.id.bt_five -> {
                startActivity(Intent(this, WebViewTestActivity::class.java))
            }
            R.id.bt_six -> {
                startActivity(Intent(this, CreateFileActivity::class.java))
            }
            R.id.bt_seven -> {
                TestDpAndPxActivity.lanuchActivity(this)
            }
            R.id.bt_eight -> {
                ScrollingActivity.lanuchActivity(this)
            }
            R.id.bt_nine->{
                startActivity(Intent(this,ItemListActivity::class.java))
            }
            R.id.bt_ten->{
                startActivity(Intent(this,RecycleListTestActivity::class.java))

            }
            R.id.bt_eleven->{
                startActivity(Intent(this,CardViewTestActivity::class.java))

            }
            R.id.bt_tweleve->{
                startActivity(Intent(this,AnimationTestActivity::class.java))

            }
            R.id.bt_thirten->{
                startActivity(Intent(this,DialogTestActivity::class.java))

            }
        }
    }


    fun innitCounter() {
        countDownTimer = object : CountDownTimer(60 * 1000, 1000) {

            override fun onFinish() {
                bt_two.setText("重新开始倒计时")
                bt_two.isEnabled = true
            }

            override fun onTick(millisUntilFinished: Long) {
                bt_two.setText((millisUntilFinished / 1000).toString())
                bt_two.isEnabled = false
            }


        }
    }


}
