package com.wm.example.test.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wm.example.test.myapplication.R;

import java.util.ArrayList;

/**
 * Created by Author:qyw
 * on 2018/9/11.
 * 描述：
 */
public class BannerAdapter extends PagerAdapter {
    private ArrayList<String> mPics;
    private Context mContext;

    public BannerAdapter(Context context,ArrayList<String> pics){
        this.mContext=context;
        this.mPics=pics;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        Log.i("TAG","isViewFromObject:"+(view==o));
        return view==o;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        position=position%mPics.size();
        View view=View.inflate(mContext,R.layout.item_banner,null);
        ImageView imageView=view.findViewById(R.id.ivImage);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setImageResource(R.mipmap.ic_launcher);
        Glide.with(mContext).applyDefaultRequestOptions(RequestOptions.placeholderOf(R.mipmap.ic_launcher)).load(mPics.get(position)).into(imageView);
        container.addView(view);
        Log.i("TAG","instantiateItem");
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Log.i("TAG","destroyItem");

        container.removeView((View) object);
    }


}
