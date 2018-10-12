package com.wm.example.test.myapplication

import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.view.ViewPager
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.wm.example.test.myapplication.adapter.BannerAdapter
import kotlinx.android.synthetic.main.activity_banner.*
import java.util.*
import javax.security.auth.callback.Callback
import kotlin.concurrent.schedule

class BannerActivity : AppCompatActivity() {
    @BindView(R.id.tv_larg2)
    lateinit var tv_larg2: TextView
    @BindView(R.id.ll_parent)
    lateinit var ll_parent: LinearLayout;
    @BindView(R.id.viewPage)
    lateinit var viewPage: ViewPager;
    var adapter: BannerAdapter? = null
    var mPics = ArrayList<String>();
    var mCurrentPostion = 0;
    var timer: Timer? = null;
    //自动滚动实现
    var mHander = Handler(
            object : Handler.Callback {
                override fun handleMessage(msg: Message?): Boolean {
                    if (msg!!.what == 100) {
                        mCurrentPostion++;
                        viewPage.setCurrentItem(mCurrentPostion)
                    }
                    return false;
                }

            }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)
        ButterKnife.bind(this);
        tv_larg2.text = getMeataValue()
        mPics.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1537263286&di=0de9abba76e83ac174fa6d76413f2b65&imgtype=jpg&er=1&src=http%3A%2F%2Fi1.umei.cc%2Fuploads%2Ftu%2F201802%2F9999%2F5154a1b3d0.jpg")
        mPics.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1537263758&di=82b33b5a2aadd612861e7a05ed73a1ef&imgtype=jpg&er=1&src=http%3A%2F%2Fs13.sinaimg.cn%2Fmiddle%2F7e489919tb12e225caaec%26amp%3B690")
        mPics.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1537263683&di=eb15c2f6d43825eb2eab408c38daef83&imgtype=jpg&er=1&src=http%3A%2F%2Fs7.sinaimg.cn%2Fmiddle%2F94bb8341hb4f22055d0b6%26amp%3B690")
        adapter = BannerAdapter(this, mPics)
        viewPage.adapter = adapter

        viewPage.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                mCurrentPostion = p0;
                changeDotColor(p0)
                Log.i("TAG", "mCurrentPostion:" + mCurrentPostion)
            }

        })
        timer = Timer();
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                mHander.sendEmptyMessage(100);
            }
        }, 3000, 4000);
        innitDot()

    }

    fun innitDot() {
        for (i in 0..mPics.size - 1) {
            var imageView = ImageView(this)
            if (i == 0) {
                imageView.setImageResource(R.drawable.shape_dot_red)
            } else {
                imageView.setImageResource(R.drawable.shape_gray_dot)
            }
            ll_parent.addView(imageView)
            var layoutParms = LinearLayout.LayoutParams(imageView.layoutParams)
            layoutParms.setMargins(10, 0, 0, 0)
            imageView.layoutParams = layoutParms
        }
    }

    fun changeDotColor(currentPostion: Int) {
        for (i in 0..mPics.size - 1) {
            var imageView: ImageView = ll_parent.getChildAt(i) as ImageView
            if (i == currentPostion % mPics.size) {
                imageView.setImageResource(R.drawable.shape_dot_red)
            } else {
                imageView.setImageResource(R.drawable.shape_gray_dot)

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (timer != null) {
            timer!!.cancel()
            timer!!.purge()
        }
    }

    fun getMeataValue(): String {
        var activityInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
        if (activityInfo == null)
            return "activityInfor==null"
        else {
            var value = activityInfo.metaData //.getString("test_meta_value")
            if (value == null) {
                return "metaData==null"
            } else
                return value.getString("test_meta_value")
        }
//        var value = activityInfo!!.metaData.get("test_meta_value").toString()
//        if (value != null)
//            return value;
//        else
//            return "没找到"
    }
}
