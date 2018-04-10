package com.hnf.guet.comhnfpatent.download;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2018/3/24.
 */

public interface DownloadAPI {
    /**
     * 下载apk
     */

    @GET
    Call<ResponseBody> downLoadApkByUrl(@Url String url);
}
