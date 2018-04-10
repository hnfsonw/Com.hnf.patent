package com.hnf.guet.comhnfpatent.util;

import android.app.Application;
import android.os.Build;

/**
 * Created by Administrator on 2018/3/23.
 * 程序入口，初始化信息
 */

public class MyApplication extends Application {

    public static  MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();


        /**
         * 判断当前系统版本是否是1.5以上，开启服务进程
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

        }

    }
}
