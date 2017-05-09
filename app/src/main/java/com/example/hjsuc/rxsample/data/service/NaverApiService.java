package com.example.hjsuc.rxsample.data.service;

import com.example.hjsuc.rxsample.data.model.naver.SearchBlogList;
import com.example.hjsuc.rxsample.data.model.naver.SearchKinList;
import com.example.hjsuc.rxsample.data.model.naver.SearchNewsList;
import com.fernandocejas.frodo.annotation.RxLogObservable;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by hjsuc on 2017-05-03.
 */

public interface NaverApiService {

    /**
     * 블로그 검색
     */
    @GET("/v1/search/blog")
    Observable<SearchBlogList> searchBlog(@Query("query") String text);

    /**
     * 뉴스 검색
     */
    @GET("/v1/search/news")
    Observable<SearchNewsList> searchNews(@Query("query") String text);

    /**
     * 지식인 검색
     */
    @GET("/v1/search/kin")
    Observable<SearchKinList> searchKin(@Query("query") String text);
}
