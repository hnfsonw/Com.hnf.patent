package com.hnf.guet.comhnfpatent.util;

import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2018/3/23.
 */

public class FileUtils {

    public static final String ROOT_DIR = "Android/data/" + UIUtils.getPackageName();
    public static final String DOWNLOAD_DIR = "download";
    public static final String CACHE_DIR = "cache";
    public static final String ICON_DIR = "icon";

    /**
     * 判断sd是否挂载
     */
    public static boolean isSDcardAvaliable(){
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 获取下载的目录
     */
    public static String getDownLoadDir(){
        return getDir(DOWNLOAD_DIR);
    }


    /**
     * 获取缓存的目录
     */
    public static String getCacheDir(){
        return getDir(CACHE_DIR);
    }

    /**
     * 获取icon的目录
     */
    public static String getIconDir(){
        return getDir(ICON_DIR);
    }


    /**
     *  获取应用目录，当SD卡存在时，获取SD卡上的目录，当SD卡不存在时，获取应用的cache目录
     */

    public static  String getDir(String dir){
        StringBuilder sb = new StringBuilder();
        if (isSDcardAvaliable()){
            sb.append(getExternalStorageStatePath());
        }else {
            sb.append(getCachechPath());
        }
        sb.append(dir);
        sb.append(File.separator);
        String path = sb.toString();
        //如果路径创建成功,返回创建成功的路径
        if (createDir(path)){
            return path;
        }else {
            return null;
        }

    }


    /**
     * 创建文件夹
     * @param path
     * @return
     */
    private static boolean createDir(String path) {
        File file = new File(path);
        if (!file.exists()||!file.isDirectory()){
            return file.mkdirs();
        }
        return true;
    }

    /**
     * 获取缓存的目录
     * @return 路径
     */
    private static String getCachechPath() {
        File file = UIUtils.getContext().getCacheDir();
        if (file == null){
            return null;
        }else {
            return file.getAbsolutePath() + "/";
        }
    }

    /**
     * 获取sd卡下的应用的目录
     * @return 路径
     */
    private static String getExternalStorageStatePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append(File.separator);
        sb.append(ROOT_DIR);
        sb.append(File.separator);
        return sb.toString();
    }


    /**
     * 清楚应用缓存
     */
    public static void cleanInternal(){
        File file = new File(getExternalStorageStatePath());
        delectFile(file);
    }

    /**
     * 清除缓存文件
     */
    public static void cleanInternalCache(){
        delectFileByDirectory(getCacheDir());
    }

    /**
     * 值删除文件，传入的是文件路径的话将不做处理
     * @param cacheDir
     */
    public static void delectFileByDirectory(String cacheDir) {
        File file = new File(cacheDir);
        if (file != null&&file.exists()&&file.isDirectory()){
            for (File item:file.listFiles()){
                item.delete();
            }
        }
    }

    public static void delectFile(File file) {
        if (file == null||!file.exists()||!file.isDirectory()){
            return;
        }
        for (File myFile:file.listFiles()){
            if (myFile.isFile()){
                myFile.delete();
            }else if (myFile.isDirectory()){
                //文件是路径，以递归的方式删除文件
                delectFile(myFile);
            }
        }
    }


}
