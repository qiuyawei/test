package com.wm.example.test.mylibry

import sun.misc.Version.print
import sun.misc.Version.println
import java.util.*

/**
 * Created by Author:qyw
on 2018/9/7.
描述：
 */
object MyKbtTest {
    fun main(args: ArrayList<String>){
        var max:Int=10;
        var myArray=ArrayList<String>(10);
        for(item in myArray){
            System.out.println(max)
            max++;
        }
        for(i in 0..9){
            System.out.println(max)
            max++;
        }
    }
}