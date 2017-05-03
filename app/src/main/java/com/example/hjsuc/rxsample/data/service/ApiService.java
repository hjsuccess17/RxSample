package com.example.hjsuc.rxsample.data.service;

import com.example.hjsuc.rxsample.data.DataManager;
import com.example.hjsuc.rxsample.data.model.Search;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by hjsuc on 2017-05-03.
 */

public interface ApiService {


    @GET("/v1/search/blog")
    Call<Search> getSearch(@Query("query") String text);


}
