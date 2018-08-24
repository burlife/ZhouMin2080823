package com.example.a1.zhoumin2080823;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a1.zhoumin2080823.activity.ShowActivity;
import com.example.a1.zhoumin2080823.activity.WebActivity;
import com.example.a1.zhoumin2080823.adapter.MyDesignerAdapter;
import com.example.a1.zhoumin2080823.adapter.MyNewsAdapter;
import com.example.a1.zhoumin2080823.bean.DesignerBean;
import com.example.a1.zhoumin2080823.bean.LunBoBean;
import com.example.a1.zhoumin2080823.bean.NewsBean;
import com.example.a1.zhoumin2080823.mvp.home.presenter.HomePresenter;
import com.example.a1.zhoumin2080823.mvp.home.view.IHomeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IHomeView, OnBannerListener {
    @BindView(R.id.main_banner)
    Banner mainBanner;
    @BindView(R.id.main_rv_designer)
    RecyclerView mainRvDesigner;
    @BindView(R.id.main_xrv_liebiao)
    XRecyclerView mainXrvLiebiao;
    @BindView(R.id.main_tv_liebiao)
    TextView mainTvLiebiao;
    private HomePresenter homePresenter;
    private List<String> list_img;
    private List<String> list_title;
    private String token = "";
    private String version = "1.7";
    private MyDesignerAdapter designerAdapter;
    private MyNewsAdapter myNewsAdapter;
    private List<LunBoBean.DataBean> list;
     private TextView t_image;
    public String path = Environment.getExternalStorageDirectory() + "/photo.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initTou();
    }

    private void initTou() {
        t_image=findViewById(R.id.t_tou);

        t_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setPositiveButton("打开相机", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        //将图片放到SD card
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
                        startActivityForResult(intent, 100);
                    }
                });
                builder.setNegativeButton("打开相册", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent, 100);
                    }
                });
                builder.show();
            }
        });
    }

    private void initData() {
        mainRvDesigner.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        mainXrvLiebiao.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        list_img = new ArrayList<>();
        list_title = new ArrayList<>();
        homePresenter = new HomePresenter(this);
        homePresenter.setLunBo();
        homePresenter.setDesigner(token, version);
        homePresenter.setNews();
    }

    @Override
    public void onSucccessLunBo(LunBoBean lunBoBean) {
        list = lunBoBean.getData();
        for (int i = 0; i < list.size(); i++) {
            list_title.add(list.get(i).getTitle());
            list_img.add(list.get(i).getIcon());
        }
        mainBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mainBanner.setImageLoader(new MyLoad());
        mainBanner.setImages(list_img);
        mainBanner.setBannerTitles(list_title);
        mainBanner.setDelayTime(3000);
        mainBanner.isAutoPlay(true);
        mainBanner.setIndicatorGravity(BannerConfig.CENTER)
                .setOnBannerListener(this)
                .start();


    }

    @Override
    public void onSuccessDesigner(DesignerBean designerBean) {
        designerAdapter = new MyDesignerAdapter(MainActivity.this, designerBean.getData().getDisplay());
        mainRvDesigner.setAdapter(designerAdapter);
    }

    @Override
    public void onSuccessNews(NewsBean newsBean) {
        myNewsAdapter = new MyNewsAdapter(MainActivity.this, newsBean.getData());
        mainXrvLiebiao.setAdapter(myNewsAdapter);
    }

    @Override
    public void OnBannerClick(int position) {
        Intent intent = new Intent(MainActivity.this, WebActivity.class);
        intent.putExtra("url", list.get(position).getUrl());
        startActivity(intent);
        //finish();
    }

    @OnClick(R.id.main_tv_liebiao)
    public void onViewClicked() {
        startActivity(new Intent(MainActivity.this,ShowActivity.class));
    }

    public class MyLoad extends ImageLoader {


        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(MainActivity.this).load(path).into(imageView);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        homePresenter.onDestroys();
    }
}
