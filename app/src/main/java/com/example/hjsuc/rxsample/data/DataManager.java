package com.example.hjsuc.rxsample.data;

import android.content.Context;


import com.example.hjsuc.rxsample.R;
import com.example.hjsuc.rxsample.data.model.Search;
import com.example.hjsuc.rxsample.data.service.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hjsuc on 2017-05-03.
 */

public class DataManager {
    public static final int SOCKET_TIME_OUT_SEC = 30; //30sec
    public static final String NAVER_DOMAIN = "https://openapi.naver.com"; //https://openapi.naver.com/v1/search/blog

    private Context mContext;
    private static OkHttpClient mClient;
    private ApiService mApi;
    private ApiService mNaverApi;


    public static String clientId;
    public static String clientSecret;


    public DataManager(Context context) {
        mContext = context;
        if (mClient == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            mClient = new OkHttpClient.Builder().
                    addInterceptor(interceptor)
                    .connectTimeout(SOCKET_TIME_OUT_SEC, TimeUnit.SECONDS)
                    .readTimeout(SOCKET_TIME_OUT_SEC, TimeUnit.SECONDS)
                    .writeTimeout(SOCKET_TIME_OUT_SEC, TimeUnit.SECONDS)
                    .build();
        }


        createNaverApi();
    }

    private void createNaverApi() {
        clientId = mContext.getString(R.string.clientid);
        clientSecret = mContext.getString(R.string.clientsecret);
        mNaverApi = new Retrofit.Builder()
                .baseUrl(NAVER_DOMAIN)
                .client(mClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class);
    }

    public void search(String search) {
        Call<Search> getProductListCodeCall = mNaverApi.getSearch(search);
        getProductListCodeCall.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {

            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {

            }
        });
    }
}
