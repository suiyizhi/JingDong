package com.example.jingdong.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jingdong.R;
import com.example.jingdong.bean.AdBean;
import com.example.jingdong.bean.CatagoryBean;

import java.util.List;

public class RvSecKillAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<AdBean.MiaoshaBean.ListBeanX> list;
    private Context context;
    private final LayoutInflater layoutInflater;
    private RvsecKillViewHolder rvsecKillViewHolder;

    public RvSecKillAdapter(List<AdBean.MiaoshaBean.ListBeanX> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.rvseckill_item, parent, false);
        rvsecKillViewHolder = new RvsecKillViewHolder(view);
        return rvsecKillViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        rvsecKillViewHolder= (RvsecKillViewHolder) holder;

        String images = list.get(position).getImages();

        String[] split = images.split("\\|");

        Glide.with(context).load(split[0]).into(rvsecKillViewHolder.img_miao);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class RvsecKillViewHolder extends RecyclerView.ViewHolder{

        private final ImageView img_miao;

        public RvsecKillViewHolder(View itemView) {
            super(itemView);
            img_miao = itemView.findViewById(R.id.img_miao);
        }
    }
}
