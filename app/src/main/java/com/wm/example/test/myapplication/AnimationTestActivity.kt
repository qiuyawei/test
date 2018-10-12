package com.wm.example.test.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.RotateAnimation
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

class AnimationTestActivity : AppCompatActivity() {
    @BindView(R.id.bt1)
    lateinit var bt_roteAnimation: Button;
    @BindView(R.id.bt2)
    lateinit var bt_translateAnimation: Button;
    @BindView(R.id.bt3)
    lateinit var bt_three: Button;
    @BindView(R.id.ivImage)
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_test)
        ButterKnife.bind(this)

    }

    @OnClick(R.id.bt1, R.id.bt2, R.id.bt3)
    fun onClick(view: View) {
        when (view.id) {
            R.id.bt1 -> {
                var translateAnimation = TranslateAnimation(0f, 100f, 0f, 0f);
                translateAnimation.duration = 500;
                translateAnimation.repeatCount = 0;
                imageView.startAnimation(translateAnimation)
            }
            R.id.bt2 -> {
                var translateAnimation = RotateAnimation(0f, 360f, 0f, 0f);
                translateAnimation.duration = 500;
                translateAnimation.repeatCount = 0;
                imageView.startAnimation(translateAnimation)
            }
            R.id.bt3 -> {
                var translateAnimation = AlphaAnimation(0.1f, 0.5f);
                translateAnimation.duration = 500;
                translateAnimation.repeatCount = 0;
                imageView.startAnimation(translateAnimation);
            }
        }
    }
}
