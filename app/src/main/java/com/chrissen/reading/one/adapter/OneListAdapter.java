package com.chrissen.reading.one.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chrissen.reading.R;
import com.chrissen.reading.one.bean.ReadingList;
import com.chrissen.reading.one.view.OneContentFragment;
import com.chrissen.reading.util.fragmentHelper.FragmentTransitionHelper;

import jp.wasabeef.glide.transformations.CropSquareTransformation;

/**
 * Created by Administrator on 2017/8/13.
 */

public class OneListAdapter extends RecyclerView.Adapter<OneListAdapter.OneListViewHolder> {

    private Fragment fragment;
    private ReadingList readingList;

    public OneListAdapter(Fragment fragment , ReadingList readingList){
        this.fragment = fragment;
        this.readingList = readingList;
    }

    @Override
    public OneListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_one_reading_list,parent,false);
        return new OneListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final OneListViewHolder holder, int position) {
        final ReadingList.Reading reading = readingList.getReadingList().get(position);
        holder.titleTv.setText(reading.getTitle());
        holder.descTv.setText(reading.getForward());
        Glide.with(fragment)
                .load(reading.getImageUrl())
                .bitmapTransform(new CropSquareTransformation(fragment.getContext()))
                .into(holder.imageIv);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OneContentFragment ocf = OneContentFragment.newInstance(reading.getImageUrl(),reading.getItemId());
                FragmentTransitionHelper.startFargment(fragment,ocf);
            }
        });
    }

    @Override
    public int getItemCount() {
        return readingList.getReadingList().size();
    }

    class OneListViewHolder extends RecyclerView.ViewHolder {

        private View layout;
        private TextView titleTv;
        private TextView descTv;
        private ImageView imageIv;

        public OneListViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            titleTv = (TextView) itemView.findViewById(R.id.item_onelist_title);
            imageIv = (ImageView) itemView.findViewById(R.id.item_onelist_image_iv);
            descTv = (TextView) itemView.findViewById(R.id.item_onelist_desc_tv);
        }
    }

}
