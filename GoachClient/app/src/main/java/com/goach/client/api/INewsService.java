package com.goach.client.api;

import com.goach.client.model.News;
import com.goach.client.util.BaseResp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Yuriy on 2016/6/4 0004.
 */
public interface INewsService {
    @GET("NewsDataServlet")
    @Headers("Cache-Control: public, max-age=30")
    Call<BaseResp<News>> userNews(@Query("userId") String userId);
}
