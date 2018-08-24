package com.example.a1.zhoumin2080823.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a1.zhoumin2080823.R;
import com.example.a1.zhoumin2080823.bean.ShowBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyLieBiaoAdapter extends RecyclerView.Adapter{
    private Context context;
    private List<ShowBean.DataBean> list;

    public MyLieBiaoAdapter(Context context, List<ShowBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_news_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder){
            ((ViewHolder) holder).homeNewsSdv.setImageURI(list.get(position).getImages().split("\\|")[0]);
            ((ViewHolder) holder).homeNewsTvTitle.setText(list.get(position).getTitle());
            ((ViewHolder) holder).homeNewsTvPrice.setText("¥"+list.get(position).getBargainPrice()+"");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.home_news_sdv)
        SimpleDraweeView homeNewsSdv;
        @BindView(R.id.home_news_tv_title)
        TextView homeNewsTvTitle;
        @BindView(R.id.home_news_tv_price)
        TextView homeNewsTvPrice;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
