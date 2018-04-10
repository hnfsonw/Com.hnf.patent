package com.hnf.guet.comhnfpatent.ui.activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.hnf.guet.comhnfpatent.util.ActivityManagerUtils;
import com.hnf.guet.comhnfpatent.util.NetEvent;
import com.hnf.guet.comhnfpatent.wedget.NetBroadReceiver;

import java.util.Timer;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/25.
 * activity的基本类，用来放置共同的方法
 */

public abstract class BaseActivity extends AppCompatActivity implements NetEvent{

    private static final String TAG = "BaseActivity";

    //网络状态
    private int netMobile;

    //网络是否经历了由断开到链接的过程
    private boolean isNetChanges;

    //监控网络的广播
    private NetBroadReceiver netBroadReceiver;

    public static BaseActivity mContext;
    protected BroadcastForBaseActivity broadcastForBaseActivity;
    private IntentFilter intentFilter;
    protected long time = 0;

    private Toast myToast;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ActivityManagerUtils.getAppManager().addActivityToSatck(this);
        mContext = this;
        ButterKnife.bind(this);
        init();
//动态注册广播
        if (broadcastForBaseActivity == null){
            broadcastForBaseActivity = new BroadcastForBaseActivity();
        }
        if (intentFilter == null){
            intentFilter = new IntentFilter("drc.xxx.yyy.baseActivity");
        }
        registerReceiver(broadcastForBaseActivity,intentFilter);
    }

    protected abstract int getLayoutRes();

    protected abstract void init();

    public class BroadcastForBaseActivity extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            //接收广播发送的广播内容
            int closeALL = intent.getIntExtra("closeAll",0);
            if (closeALL == 1){
                //要退出app了,销毁baseActivity
                finish();
            }
        }
    }


    public void exitHomeActivity(){
        Intent intent = new Intent("jason.broadcast.action");
        intent.putExtra("closeAll",1);
        sendBroadcast(intent);//发送广播
    }

    //退出
    protected void exit(){
        if (System.currentTimeMillis() - time > 2000){
            time = System.currentTimeMillis();
            print("再点击一次退出app");
        }else {
            Intent intent = new Intent("drc.xxx.yyy.baseActivity");
            intent.putExtra("closeAll",1);
            sendBroadcast(intent);
        }
    }

    //吐司
    public void print(String message) {
        if (myToast == null){
            myToast = Toast.makeText(this,message,Toast.LENGTH_SHORT);
        }else {
            myToast.setText(message);
        }
        myToast.show();
    }

    //申请相机权限
    public void canmeraPermissions(){
        int checkCameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        //拒绝
        if (checkCameraPermission == PackageManager.PERMISSION_DENIED){
            //申请权限
            ActivityCompat.requestPermissions(this,
                    new String []{Manifest.permission.CAMERA},102);

        }else if (checkCameraPermission == PackageManager.PERMISSION_GRANTED){
            cameraSuccess();
        }

    }


    //申请存储权限
    public void  StorePermission(){
        int checkPer = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (checkPer == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},103);
        }else if (checkPer == PackageManager.PERMISSION_GRANTED){
            Log.i("baseActivity","存储权限已经开启");
        }
    }

    /**
     * 申请权限返回值
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 102:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    System.out.print("相机权限申请成功");
                    cameraSuccess();
                }else {
                    cameraFail();
                }
                break;
            case 103:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    System.out.print("存储权限申请成功");
                }else {
                    System.out.print("存储权限申请失败");
                    storeFail();
                }
        }
    }

    //存储权限申请失败
    protected void storeFail() {

    }

    //相机权限申请成功
    protected void cameraFail() {

    }

    //相机权限打开成功
    protected void cameraSuccess() {

    }

    /**
     * loading加载圈
     */

}































