package com.wm.example.test.myapplication

import android.app.KeyguardManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.wm.example.test.myapplication.adapter.TestAdapter
import com.wm.example.test.myapplication.bean.RecyleListBean

class RecycleListTestActivity : AppCompatActivity() {
    @BindView(R.id.recycleView)
    lateinit var recycleView:RecyclerView;
    var data=ArrayList<RecyleListBean>();
    var adapter:TestAdapter?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_list_test)
        ButterKnife.bind(this);
        inntData()
        recycleView.layoutManager=GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false)//Item的布局。
        //添加分割线
        recycleView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        recycleView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL))

        adapter=TestAdapter(this,data);
        recycleView.adapter=adapter;
        

    }


    fun inntData(){
        data.clear()
        for(i in 0..10){
            var item=RecyleListBean();
            item.name="item"+i;
            item.picUrl="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1537263758&di=82b33b5a2aadd612861e7a05ed73a1ef&imgtype=jpg&er=1&src=http%3A%2F%2Fs13.sinaimg.cn%2Fmiddle%2F7e489919tb12e225caaec%26amp%3B690";
            data.add(item);
        }
    }
}
