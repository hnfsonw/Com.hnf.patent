package com.hnf.guet.comhnfpatent.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/21.
 * activity管理类
 */

public class ActivityManagerUtils {
    private static ArrayList<Activity> activityStacks;  //activity管理栈
    private static ActivityManagerUtils instance;  //单例

    private ActivityManagerUtils(){}//无参构造器

    //静态构造函数，一个类只能有一个
    public static ActivityManagerUtils getAppManager(){
        if (instance == null){
            instance = new ActivityManagerUtils();
        }
        return instance;
    }

    /*
    *添加activity到栈里面
     */
    public void addActivityToSatck(Activity activity){
        if (activityStacks == null){
            activityStacks = new ArrayList<>();
        }
        activityStacks.add(activity);
    }

    /**
     * 获取当前activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity(){
        Activity activity = activityStacks.get(activityStacks.size()-1);
        System.out.print("当前的activity:"+activity);
        return activity;
    }

    /**
     * 结束当前activity（堆栈中最后一个）
     */

    public void finshActivity(){
        Activity activity = activityStacks.get(activityStacks.size() - 1);
        if (activity != null){
            activity.finish();
            activity = null;
        }
    }

    /***
     *结束指定的activity
     */
    public void finshActivity(Activity activity){
        if (activity != null){
            activityStacks.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的activity
     */
    public void finshActivity(Class<?> clas){
        for(Activity activity:activityStacks){
            if (activity.getClass().equals(clas)){
                finshActivity(activity);
            }
        }

    }

    /**
     * 结束所有activity
     */
    public void  finshAllActivity(){
        for (int i=0;i<activityStacks.size();i++){
            if (activityStacks.get(i) != null){
                activityStacks.get(i).finish();
            }
        }
        activityStacks.clear();
    }

    /**
     * 退出APP
     */
    public void appExit(Context context){
        finshAllActivity();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.restartPackage(context.getPackageName());
        System.exit(0);
    }
}
