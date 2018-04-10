package com.hnf.guet.comhnfpatent.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.security.Principal;
import java.sql.Connection;

/**
 * Created by Administrator on 2018/3/24.
 */

public class NetUtil {
    /**
     * 没有网络连接
     */
    private static final int NETWORK_NONE = -1;

    /**
     * 移动网络
     */
    private static final int NETWORK_MOBILE = 0;

    /**
     * wifi网络
     */
    private static final int NETWORK_WIFI = 1;


    /**
     * 获取网络状态
     */
    public static  int getNetWorkState(Context context){
       ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null&&networkInfo.isConnected()){
            if (networkInfo.getType() == (ConnectivityManager.TYPE_WIFI)){
                return NETWORK_WIFI;
            }else if (networkInfo.getType() == (ConnectivityManager.TYPE_MOBILE)){
                return NETWORK_MOBILE;
            }
        }else {
            return NETWORK_NONE;//没有网络
        }
        return NETWORK_NONE;
    }
















}
