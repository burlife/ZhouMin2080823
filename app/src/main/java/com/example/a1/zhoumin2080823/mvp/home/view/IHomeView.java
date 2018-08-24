package com.example.a1.zhoumin2080823.mvp.home.view;

import com.example.a1.zhoumin2080823.bean.DesignerBean;
import com.example.a1.zhoumin2080823.bean.LunBoBean;
import com.example.a1.zhoumin2080823.bean.NewsBean;

public interface IHomeView {
    void onSucccessLunBo(LunBoBean lunBoBean);
    void onSuccessDesigner(DesignerBean designerBean);
    void onSuccessNews(NewsBean newsBean);


}
