package com.wm.example.test.myapplication

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

class WebViewTestActivity : AppCompatActivity() {
//    var url = "http://eas.lenovo.com.cn/weixin/index.php/ShopScan/scanIndex?extinfo=20J5A0NSCDPF10KQ26_thinkpadr";
    var url="m.wm-icenter.com"
    @BindView(R.id.webView)
    lateinit var webView: WebView;
    @BindView(R.id.contentProgressBar)
    lateinit var processBar: ProgressBar;
    var webSettings: WebSettings? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_test)
        ButterKnife.bind(this)
        innitWebViewSettings();
    }

    private fun innitWebViewSettings() {
        processBar.max=100
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            processBar.min=0
        }
        webSettings = webView.settings;
        webSettings!!.setAppCacheEnabled(true)
        webSettings!!.blockNetworkImage=true//图片下载阻塞
        webSettings!!.javaScriptEnabled = true;
        webSettings!!.cacheMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        webSettings!!.useWideViewPort = true
        webSettings!!.loadWithOverviewMode = true
        runOnUiThread(Runnable {
            webView.loadUrl(url)
        })
        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                webSettings!!.blockNetworkImage=true
                processBar.progress=view!!.progress;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    processBar.tooltipText=view!!.progress.toString()
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                Log.i("TAG","onPageFinished:"+webView.progress);
                processBar.visibility= View.INVISIBLE
                webSettings!!.blockNetworkImage=false
            }



            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Log.i("TAG","onReceivedError:"+error!!.errorCode);

                    Log.i("TAG","onReceivedError:"+error!!.description)
                };

            }

//            override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse {
////                if(request!!.isForMainFrame&&request!!.url.path.toLowerCase().contains("favicon.ico")){
////                    Log.i("TAG","shouldInterceptRequest:"+request.url.path);
////                }
//                return super.shouldInterceptRequest(view, request)
//            }

        }
       webView.webChromeClient=object : WebChromeClient(){
           override fun onProgressChanged(view: WebView?, newProgress: Int) {
               super.onProgressChanged(view, newProgress)
               Log.i("TAG","process:"+newProgress);
               if(newProgress<100){
                   processBar.progress=newProgress
               }
           }

           override fun onPermissionRequestCanceled(request: PermissionRequest?) {
               super.onPermissionRequestCanceled(request)
               Log.i("TAG","onPermissionRequestCanceled:");

           }

       }

    }

    @OnClick(R.id.button)
    fun onClick(view:View){
        when(view.id){
            R.id.button->{
                webView.reload()
            }
        }
    }
}



