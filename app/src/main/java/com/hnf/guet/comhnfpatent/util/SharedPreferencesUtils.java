package com.hnf.guet.comhnfpatent.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2018/3/24.
 */

public class SharedPreferencesUtils {

    /**
     * 保存手机里面的文件名
     */
    private static final String FILE_NAME = "globalfile";

    /**
     * 拿到数据的具体类型，然后根据类型调用不同的保存方法
     */
    public static void setParam(Context context,String key,Object object){
        String type = object.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (type.equals("String")){
            editor.putString(key,(String)object);
        }else if (type.equals("Integer")){
            editor.putInt(key,(Integer)object);
        }else if (type.equals("Boolean")){
            editor.putBoolean(key,(Boolean)object);
        }else if (type.equals("Float")){
            editor.putFloat(key,(Float)object);
        }else if (type.equals("Long")){
            editor.putLong(key,(Long)object);
        }
        editor.commit();
    }



    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object getParam(Context context , String key, Object defaultObject){
        String type = defaultObject.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        if("String".equals(type)){
            return sp.getString(key, (String)defaultObject);
        }
        else if("Integer".equals(type)){
            return sp.getInt(key, (Integer)defaultObject);
        }
        else if("Boolean".equals(type)){
            return sp.getBoolean(key, (Boolean)defaultObject);
        }
        else if("Float".equals(type)){
            return sp.getFloat(key, (Float)defaultObject);
        }
        else if("Long".equals(type)){
            return sp.getLong(key, (Long)defaultObject);
        }

        return null;
    }

}
