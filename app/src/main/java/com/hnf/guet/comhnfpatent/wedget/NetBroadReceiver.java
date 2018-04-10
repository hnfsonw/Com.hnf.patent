package com.hnf.guet.comhnfpatent.wedget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

import com.hnf.guet.comhnfpatent.util.NetEvent;
import com.hnf.guet.comhnfpatent.util.NetUtil;

/**
 * Created by Administrator on 2018/3/25.
 * 自定义检查手机网络的广播接收器
 */

public class NetBroadReceiver extends BroadcastReceiver {

    private static final String TAG = "NetBroadReceiver";
    private NetEvent netEvent;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
            int netWorkState = NetUtil.getNetWorkState(context);
            Log.e(TAG+"网络状态：",netWorkState + "");
            if (netEvent != null){
                netEvent.onNetChange(netWorkState);
            }
        }
    }

    public void setNetEvent(NetEvent netEvent){
        this.netEvent = netEvent;
    }
}
