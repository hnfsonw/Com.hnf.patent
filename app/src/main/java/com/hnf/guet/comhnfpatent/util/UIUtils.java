package com.hnf.guet.comhnfpatent.util;

import android.content.Context;

/**
 * Created by Administrator on 2018/3/21.
 */

public class UIUtils {


    /**
     * 得到上下文
     */
    public static Context getContext(){
        return MyApplication.instance;
    }


    /**
     * 获取应用程序的包名
     */
    public static String getPackageName(){
        return getContext().getPackageName();
    }
}
