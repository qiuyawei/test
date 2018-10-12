package com.wm.example.test.mylibry;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Author:qyw
 * on 2018/9/7.
 * 描述：
 */
public class NormalJava {
    public static void main(String[] args) {
        String url="http://qa-h5.wm-icenter.net";
        try {
            URL url1=new URL(url);
            System.out.print(url1.getHost());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
