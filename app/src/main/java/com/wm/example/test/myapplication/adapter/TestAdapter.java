package com.wm.example.test.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wm.example.test.myapplication.R;
import com.wm.example.test.myapplication.bean.RecyleListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Author:qyw
 * on 2018/9/18.
 * 描述：
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {
    private Context mContext;
    private List<RecyleListBean> mdata;

    public TestAdapter(Context context, List<RecyleListBean> data) {
        this.mContext = context;
        this.mdata = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycle_test, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if(viewHolder.tv_goodsName==null){
            Log.i("TAG","null");
        }
        viewHolder.tv_goodsName.setText(mdata.get(i).getName());
        Glide.with(mContext).load(mdata.get(i).getPicUrl()).apply(RequestOptions.overrideOf(400, 400).centerCrop()).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_goodsName)
        TextView tv_goodsName;
        @BindView(R.id.imageView)
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
