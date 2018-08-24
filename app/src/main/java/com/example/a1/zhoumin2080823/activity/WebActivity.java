package com.example.a1.zhoumin2080823.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.a1.zhoumin2080823.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity  extends AppCompatActivity{
    @BindView(R.id.web_web)
    WebView webWeb;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        webWeb.loadUrl(url);
    }
}
