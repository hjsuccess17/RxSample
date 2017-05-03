package com.example.hjsuc.rxsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hjsuc.rxsample.data.DataManager;

public class MainActivity extends AppCompatActivity {

    private DataManager mApiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApiManager = new DataManager(this);

        mApiManager.search("맛집");
    }
}
