package com.example.hjsuc.rxsample.data;

import android.content.Context;


import com.example.hjsuc.rxsample.R;
import com.example.hjsuc.rxsample.data.model.NaverSearchItem;
import com.example.hjsuc.rxsample.data.model.naver.BaseItem;
import com.example.hjsuc.rxsample.data.model.naver.SearchBlogList;
import com.example.hjsuc.rxsample.data.model.naver.SearchKinList;
import com.example.hjsuc.rxsample.data.model.naver.SearchNewsList;
import com.example.hjsuc.rxsample.data.model.naver.SearchTitleItem;
import com.example.hjsuc.rxsample.data.service.NaverApiService;
import com.fernandocejas.frodo.annotation.RxLogObservable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func3;

/**
 * Created by hjsuc on 2017-05-03.
 */

public class DataManager {
    public static final int SOCKET_TIME_OUT_SEC = 30; //30sec
    public static final String NAVER_DOMAIN = "https://openapi.naver.com"; //https://openapi.naver.com/v1/search/blog

    private Context mContext;
    private static OkHttpClient mClient;
    private NaverApiService mApi;
    private NaverApiService mNaverApi;


    public static String clientId = "";
    public static String clientSecret = "";


    public DataManager(Context context) {
        mContext = context;
        clientId = mContext.getString(R.string.clientid);
        clientSecret = mContext.getString(R.string.clientsecret);
        if (mClient == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            mClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            //setting naver api key
                            Request request = chain.request();
                            request = request.newBuilder()
                                    .addHeader("X-Naver-Client-Id", clientId)
                                    .addHeader("X-Naver-Client-Secret", clientSecret)
                                    .build();
                            return chain.proceed(request);
                        }
                    })
                    .connectTimeout(SOCKET_TIME_OUT_SEC, TimeUnit.SECONDS)
                    .readTimeout(SOCKET_TIME_OUT_SEC, TimeUnit.SECONDS)
                    .writeTimeout(SOCKET_TIME_OUT_SEC, TimeUnit.SECONDS)
                    .build();
        }


        createNaverApi();
    }

    private void createNaverApi() {
        mNaverApi = new Retrofit.Builder()
                .baseUrl(NAVER_DOMAIN)
                .client(mClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(NaverApiService.class);
    }

    @RxLogObservable
    private Observable<SearchBlogList> getBlogObservable(String text) {
        return mNaverApi.searchBlog(text);
    }

    @RxLogObservable
    private Observable<SearchNewsList> getNewObservable(String text) {
        return mNaverApi.searchNews(text);
    }

    @RxLogObservable
    private Observable<SearchKinList> getKinObservable(String text) {
        return mNaverApi.searchKin(text);
    }

    @RxLogObservable
    public Observable<ArrayList<NaverSearchItem>> searhAll(String text) {
        return Observable.zip(getBlogObservable(text), getNewObservable(text), getKinObservable(text), new Func3<SearchBlogList, SearchNewsList, SearchKinList, ArrayList<NaverSearchItem>>() {
            @Override
            public ArrayList<NaverSearchItem> call(SearchBlogList searchBlogList, SearchNewsList searchNewsList, SearchKinList searchKinList) {
                ArrayList<NaverSearchItem> datas = new ArrayList();
                //blog
                datas.add(makeTitleItem(searchBlogList));
                datas.addAll(searchBlogList.getItems());
                //news
                datas.add(makeTitleItem(searchNewsList));
                datas.addAll(searchNewsList.getItems());
                //kin
                datas.add(makeTitleItem(searchKinList));
                datas.addAll(searchKinList.getItems());
                return datas;
            }
        });
    }


    private SearchTitleItem makeTitleItem(BaseItem data) {
        if (data instanceof SearchBlogList) {
            return new SearchTitleItem("블로그");
        } else if (data instanceof SearchNewsList) {
            return new SearchTitleItem("뉴스");
        } else if (data instanceof SearchKinList) {
            return new SearchTitleItem("지식인");
        }
        return new SearchTitleItem("");
    }
}
