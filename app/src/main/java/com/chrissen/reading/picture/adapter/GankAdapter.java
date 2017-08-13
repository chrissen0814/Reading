package com.chrissen.reading.picture.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chrissen.reading.R;
import com.chrissen.reading.picture.bean.Gank;

import java.util.List;

/**
 * Created by Administrator on 2017/8/13.
 */

public class GankAdapter extends RecyclerView.Adapter<GankAdapter.GankViewHolder> {

    private List<Gank.MeiZhi> meizhiList;
    private Fragment fragment;

    public GankAdapter(Fragment fragment ,List<Gank.MeiZhi> meiZhiList){
        this.fragment = fragment;
        this.meizhiList = meiZhiList;
    }

    @Override
    public GankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gank_meizhi,parent,false);
        return new GankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GankViewHolder holder, int position) {
        Glide.with(fragment).load(meizhiList.get(position).getUrl())
                .into(holder.gankIv);
    }

    @Override
    public int getItemCount() {
        return meizhiList.size();
    }

    class GankViewHolder extends RecyclerView.ViewHolder {
        private ImageView gankIv;
        public GankViewHolder(View itemView) {
            super(itemView);
            gankIv = (ImageView) itemView.findViewById(R.id.item_gank_meizhi_iv);
        }
    }

}
