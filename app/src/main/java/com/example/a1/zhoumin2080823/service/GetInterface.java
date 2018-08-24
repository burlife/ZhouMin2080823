package com.example.a1.zhoumin2080823.service;

import com.example.a1.zhoumin2080823.bean.DesignerBean;
import com.example.a1.zhoumin2080823.bean.LunBoBean;
import com.example.a1.zhoumin2080823.bean.NewsBean;
import com.example.a1.zhoumin2080823.bean.ShowBean;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface GetInterface {
    //轮播图接口
    //https://www.zhaoapi.cn/quarter/getAd
    @POST("quarter/getAd")
    Observable<LunBoBean> getLunBo();
    //设计师接口
    //https://app.tuozhe8.com/api.php/api/Lists/designer?token=&version=1.7
    @POST("api.php/api/Lists/designer")
    Observable<DesignerBean> getDesigner(@Query("token") String token, @Query("version") String version);
    //列表接口
    //https://www.zhaoapi.cn/product/getCatagory
    @GET("product/getCatagory")
    Observable<NewsBean> getNews();
    //图三接口
    //https://www.zhaoapi.cn/product/getProducts?pscid=1
    @POST("product/getProducts")
    Observable<ShowBean> getShow(@Query("pscid") int pscid);
}
