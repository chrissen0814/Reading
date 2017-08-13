package com.chrissen.reading.picture.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chrissen.reading.MyApplication;
import com.chrissen.reading.R;
import com.chrissen.reading.picture.bean.Unsplash;
import com.chrissen.reading.picture.view.PictureDetailFragment;
import com.chrissen.reading.util.ScreenUtil;
import com.chrissen.reading.util.fragmentHelper.FragmentTransitionHelper;

import java.util.List;


/**
 * Created by Administrator on 2017/8/2.
 */

public class UnsplashAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "UnsplashAdapter";

    private Fragment mFragment;
    private List<Unsplash> mUnsplashList;

    public UnsplashAdapter(Fragment fragment , List<Unsplash> unsplashList){
        mFragment = fragment;
        mUnsplashList = unsplashList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final Unsplash unsplash = mUnsplashList.get(position);
        Glide.with(mFragment)
                .load(unsplash.getUrls().getSmall())
                .centerCrop()
                .crossFade(800)
                .into(((ContentViewHolder)holder).pictureIv);
        ((ContentViewHolder) holder).layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageUrl = unsplash.getUrls().getRegular();
                String transitionName = imageUrl + position;
                ViewCompat.setTransitionName(((ContentViewHolder) holder).pictureIv,transitionName);
                PictureDetailFragment pdf = PictureDetailFragment.newInstance(unsplash,transitionName);
                FragmentTransitionHelper.startFargment(MyApplication.getContext(),mFragment,pdf,((ContentViewHolder) holder).pictureIv,transitionName);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUnsplashList.size();
    }

    class ContentViewHolder extends RecyclerView.ViewHolder {

        private ImageView pictureIv;
        private View layout;

        public ContentViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            pictureIv = (ImageView) itemView.findViewById(R.id.item_picture_iv);
            pictureIv.getLayoutParams().height = ScreenUtil.getScreenWidth(mFragment.getContext())*9/16;
        }

    }


}
