package com.wm.example.test.myapplication.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.location.LocationManager;
import android.os.Environment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by vinson.ma on 2017/9/22
 *
 * @Description 屏幕相关参数获取工具
 */

public class DisplayUtil {

    private DisplayUtil() {
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(double dipValue) {
        return (int) (dipValue * getDisplayDensity() + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(double pxValue) {
        return (int) (pxValue / getDisplayDensity() + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public static int px2sp(float pxValue) {
        return (int) (pxValue / getScaledDensity() + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(float spValue) {
        return (int) (spValue * getScaledDensity() + 0.5f);
    }

    /**
     * 获取手机屏幕的像素高
     */
    public static int getDisplayHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取手机屏幕的像素宽
     */
    public static int getDisplayWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕密度
     */
    public static float getDisplayDensity() {
        return Resources.getSystem().getDisplayMetrics().density;
    }

    /**
     * 获取字体密度
     */
    public static float getScaledDensity() {
        return Resources.getSystem().getDisplayMetrics().scaledDensity;
    }

    /**
     * 获取手机状态栏的高度
     */
    public static int getStatusBarHeight(Context context) {
        if (context == null || ((Activity) context).getWindow().getDecorView() == null) {
            return 0;
        }
        int statusHeight;
        Rect localRect = new Rect();
        ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = context.getResources().getDimensionPixelSize(i5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

    /**
     * 获得View在屏幕的位置
     */
    public static Rect getRectOnScreen(View v) {
        if (v == null) {
            return new Rect();
        }
        int[] point = new int[2];
        v.getLocationOnScreen(point);
        return new Rect(point[0], point[1], point[0] + v.getWidth(), point[1] + v.getHeight());
    }

    public static void hideKeyboard(Activity context) {
        if (context != null && context.getCurrentFocus() != null)
            ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void showKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive())
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private static Map<String, Bitmap> carChartMap = new HashMap<String, Bitmap>();
    public static void recycleBitmaps() {
        try {
            Iterator iter = carChartMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Object val = entry.getValue();
                if(val!=null){
                    ((Bitmap)val).recycle();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        carChartMap.clear();
    }


}
