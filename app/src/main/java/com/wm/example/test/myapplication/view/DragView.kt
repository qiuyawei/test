package com.wm.example.test.myapplication.view

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.view.animation.TranslateAnimation
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Scroller

/**
 * Created by Author:qyw
  on 2018/9/20.
  描述：
 */
class DragView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    var mScroller:Scroller= Scroller(context)
    var  mLastY=0;


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var currentY= event!!.rawY.toInt();
        when(event.action){
            MotionEvent.ACTION_DOWN->{
                if(mScroller!!.isFinished){
                    mScroller.abortAnimation()
                }
                mLastY=currentY;
            }
            MotionEvent.ACTION_MOVE->{
                var distanceY=event.rawY.toInt()-mLastY;
                mLastY=currentY;
                scrollBy(0,-distanceY)

            }
            MotionEvent.ACTION_UP->{
                mScroller.startScroll(0,scrollY,0,-scrollY,500)
                invalidate()
            }
        }
        return true
    }

    override fun computeVerticalScrollRange(): Int {
        return super.computeVerticalScrollRange()
    }

    override fun computeScroll() {
//        super.computeScroll()
        if(mScroller.computeScrollOffset()){
            scrollBy(mScroller.currX,mScroller.currY)
            invalidate()
        }
    }
}