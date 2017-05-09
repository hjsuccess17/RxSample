package com.example.hjsuc.rxsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.zip_search)
    Button mZipSearchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.zip_search})
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.zip_search) {
            startActivity(new Intent(this, ZipSearchActivity.class));
        }
    }
}
