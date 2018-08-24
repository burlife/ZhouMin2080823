package com.example.a1.zhoumin2080823.mvp.home.model;

import android.util.Log;

import com.example.a1.zhoumin2080823.bean.DesignerBean;
import com.example.a1.zhoumin2080823.bean.LunBoBean;
import com.example.a1.zhoumin2080823.bean.NewsBean;
import com.example.a1.zhoumin2080823.util.RetrofitUtils;
import com.example.a1.zhoumin2080823.util.RetrofitUtils2;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeModel {
    private static final String TAG =HomeModel.class.getSimpleName() ;

    public void getLunBo(final IHomeModel iHomeModel){
        Observable<LunBoBean> observable = RetrofitUtils.getInstance().getInterface().getLunBo();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LunBoBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,e.getMessage());
                    }

                    @Override
                    public void onNext(LunBoBean lunBoBean) {
                        iHomeModel.onSucccessLunBo(lunBoBean);
                    }
                });
    }

    public void getDesigner(String token, String version, final IHomeModel iHomeModel){
        Observable<DesignerBean> observable = RetrofitUtils2.getInstance().getInterface().getDesigner(token, version);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DesignerBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DesignerBean designerBean) {
                        iHomeModel.onSuccessDesigner(designerBean);
                    }
                });
    }


    public void getNews(final IHomeModel iHomeModel){
        Observable<NewsBean> observable = RetrofitUtils.getInstance().getInterface().getNews();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        iHomeModel.onSuccessNews(newsBean);
                    }
                });


    }

}
