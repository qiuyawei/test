package com.wm.example.test.myapplication.view

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import android.view.animation.OvershootInterpolator
import android.view.animation.TranslateAnimation
import android.widget.ScrollView
import kotlin.math.abs

/**
 * Created by Author:qyw
on 2018/9/20.
描述：
 */
class CustomScroller(context: Context?, attrs: AttributeSet?) : ScrollView(context, attrs) {
    var childView: View? = null;//scrollView的唯一子View
    var y = 0;//点击时y的坐标
    var rect = Rect();//矩形 用来保存 inner的初始状态
    var maxMoveDistance = 0;//可以拉伸的最大移动距离
    /**
     * xml绘制完成后调用
     * 获取ScrollView的唯一子view
     */
    override fun onFinishInflate() {
        if (childCount > 0)
            childView = getChildAt(0);
        super.onFinishInflate()
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
//        handleTouchEvent(ev)
        return super.onTouchEvent(ev)
    }


    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        handleTouchEvent(ev)
        return super.dispatchTouchEvent(ev)
    }

    fun handleTouchEvent(ev: MotionEvent) {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                y = ev.y.toInt();
            }
            MotionEvent.ACTION_MOVE -> {
                var nowY = ev.y.toInt();
                var delaY = nowY - y;
                y = nowY;
                if (isNeedMove()) {
                    if (rect!!.isEmpty) {
                        //rect保存childView的初始位置信息
                        rect = Rect(childView!!.left, childView!!.top, childView!!.right, childView!!.bottom)
                    }
//                    if (abs(maxMoveDistance) <= 200) {
                        //超过最大移动距离不再移动
                        childView!!.layout(childView!!.left, childView!!.top + delaY, childView!!.right, childView!!.bottom+delaY)
//                        maxMoveDistance = maxMoveDistance + delaY;
//                    }
                }

            }
            MotionEvent.ACTION_UP -> {
                if (!rect.isEmpty) {
                    startAnimation()
                }
            }
        }
    }

    /**
     * 判断是否需要移动布局
     */
    fun isNeedMove(): Boolean {
        var offSet = childView!!.measuredHeight - height;
        Log.i("TAG", "measuredHeight:" + childView!!.measuredHeight)
        Log.i("TAG", "height:" + height)
        Log.i("TAG", "scrollY:" + scrollY)
        Log.i("TAG", "offSet:" + offSet)

        var scrollY = scrollY;
        //0是顶部 后面的是底部
        if (scrollY == 0 || offSet == scrollY) {
            return true;
        }
        return false;
    }

    /**
     * 回弹动画
     *
     */
    fun startAnimation() {
        var translateAnimation = TranslateAnimation(0f, 0f, childView!!.top.toFloat(), rect!!.top.toFloat());
        translateAnimation.duration = 600;
        translateAnimation.interpolator = OvershootInterpolator();
        childView!!.startAnimation(translateAnimation);

        //inner 重新回到原位置
        childView!!.layout(rect.left, rect.top, rect.right, rect.bottom)
        rect.setEmpty();
    }
}