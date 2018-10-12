package com.wm.example.test.myapplication.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.DimenRes;
import android.support.annotation.Dimension;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.wm.example.test.myapplication.R;

/**
 * Created by Author:qyw
 * on 2018/9/10.
 * 描述：
 */
public class Indictor extends View {
    private RectF mRectF;
    private Rect mRect;
    private Paint mPaint, textPain,whiteTextPaint,blueTextPaint,blackTextPaint,yellowTextPain,readTextPaint;
    private int mCount = 10;
    private int mWidth;
    private int mRectHeight;
    private int mRectWidth;
    private LinearGradient mLinearGradient;
    private double mRandom;
    private float mcurrentHeight;
    private float[] mFloat;
    public static final int OFFSET = 5;

    public Indictor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);

    }

    private void initView(Context context, AttributeSet attrs) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.YELLOW);
        mPaint.setStyle(Paint.Style.FILL);
        textPain=new Paint(Paint.ANTI_ALIAS_FLAG);
        textPain.setColor(Color.DKGRAY);
        textPain.setTextSize(20);
//        textPain.setStyle(Paint.Style.STROKE);
        blackTextPaint=new Paint();
        blackTextPaint.setColor(Color.BLACK);
        blackTextPaint.setTextAlign(Paint.Align.CENTER);
        blackTextPaint.setTextSize(40);

        whiteTextPaint=new Paint();
        whiteTextPaint.setColor(Color.WHITE);
        whiteTextPaint.setTextAlign(Paint.Align.CENTER);
        whiteTextPaint.setTextSize(40);

        yellowTextPain=new Paint();
        yellowTextPain.setColor(Color.YELLOW);
        yellowTextPain.setTextAlign(Paint.Align.CENTER);
        yellowTextPain.setTextSize(40);

        blueTextPaint=new Paint();
        blueTextPaint.setColor(Color.BLUE);
        blueTextPaint.setTextAlign(Paint.Align.CENTER);
        blueTextPaint.setTextSize(40);

    }

    public Indictor(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Indictor(Context context) {
        this(context, null);
    }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawARGB(100, 100, 100, 100);
        //两个点确定一个矩形 即左上和右下
//        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
        canvas.drawRect(getWidth()/2-100,getHeight()/2-100,getWidth()/2+100,getHeight()/2+100,textPain);
        canvas.drawCircle(getWidth()/2,getHeight()/2,120,textPain);
        canvas.drawText("cat",getWidth()/2,getHeight()/2,whiteTextPaint);
        canvas.drawLine(getWidth()/2-80,getHeight()/2-80,getWidth()/2-40,getHeight()/2-80,whiteTextPaint);
        canvas.drawLine(getWidth()/2+80,getHeight()/2-80,getWidth()/2+40,getHeight()/2-80,whiteTextPaint);
        canvas.drawRect(getWidth()/2-30,getHeight()/2+20,getWidth()/2+30,getHeight()/2+30,whiteTextPaint);
//        canvas.drawArc(new RectF(100,100,200,200),0,180,false,mPaint);


        canvas.drawArc(new RectF(100,100,300,300),0,360,true,yellowTextPain);
        textPain.setColor(Color.BLUE);
        canvas.drawArc(new RectF(100,100,300,300),0,270,true,blackTextPaint);

        canvas.drawText("75%",200,200,blueTextPaint);
    }
}
