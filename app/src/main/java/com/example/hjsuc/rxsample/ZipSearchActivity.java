package com.example.hjsuc.rxsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hjsuc.rxsample.adapter.NaverSearchAdapter;
import com.example.hjsuc.rxsample.data.DataManager;
import com.example.hjsuc.rxsample.data.model.NaverSearchItem;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hjsuc on 2017-05-09.
 */

public class ZipSearchActivity extends AppCompatActivity {

    private DataManager mApiManager;
    private RecyclerView mRecyclerView;
    private NaverSearchAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zip_search);
        mApiManager = new DataManager(this);
        initView();


        searchAll("맛집");
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mAdapter = new NaverSearchAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
    }


    private void searchAll(String text) {
        mApiManager.searhAll(text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchDatas -> {
                            fetchData(searchDatas);
                        }
                        , error -> {
                            L.d("error=" + error.getMessage());
                        });
    }


    private void fetchData(ArrayList<NaverSearchItem> datas) {
        mAdapter.setData(datas);
    }


}
