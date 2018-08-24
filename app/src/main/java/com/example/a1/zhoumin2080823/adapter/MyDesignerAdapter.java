package com.example.a1.zhoumin2080823.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a1.zhoumin2080823.R;
import com.example.a1.zhoumin2080823.bean.DesignerBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyDesignerAdapter  extends RecyclerView.Adapter{

    private Context context;
    private List<DesignerBean.DataBean.DisplayBean> list;

    public MyDesignerAdapter(Context context, List<DesignerBean.DataBean.DisplayBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_designer_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder){
            ((ViewHolder) holder).homeDesignerSdvItem.setImageURI(list.get(position).getAvatar());
            ((ViewHolder) holder).homeDesignerTvTitle.setText(list.get(position).getNick_name());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.home_designer_sdv_item)
        SimpleDraweeView homeDesignerSdvItem;
        @BindView(R.id.home_designer_tv_title)
        TextView homeDesignerTvTitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
