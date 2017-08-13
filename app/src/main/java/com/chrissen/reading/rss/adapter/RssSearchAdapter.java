package com.chrissen.reading.rss.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chrissen.reading.R;
import com.chrissen.reading.rss.bean.Feed;

import org.litepal.crud.DataSupport;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2017/8/10.
 */

public class RssSearchAdapter extends RecyclerView.Adapter<RssSearchAdapter.RssSearchViewHolder> {

    private Context context;
    private List<Feed> feedList;

    public RssSearchAdapter(Context context ,List<Feed> feedList){
        this.feedList = feedList;
        this.context = context;
    }

    @Override
    public RssSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_rss,parent,false);
        return new RssSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RssSearchViewHolder holder, int position) {
        final Feed feed = feedList.get(position);
        if (feed.getVisualUrl() != null) {
            Glide.with(context)
                    .load(feed.getVisualUrl())
                    .bitmapTransform(new CropCircleTransformation(context))
                    .into(holder.iconIv);
        }else {
            Glide.clear(holder.iconIv);
            holder.iconIv.setImageDrawable(null);
        }
        holder.titleTv.setText(feed.getTitle());
        holder.websiteTv.setText(feed.getWebsite());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Feed> feedList = DataSupport.findAll(Feed.class);
                if (feedList.size()>0) {
                    for(Feed savedFeed : feedList){
                        if(savedFeed.getFeedId().equals(feed.getFeedId())){
                            Toast.makeText(context, "已经订阅该Feed了", Toast.LENGTH_SHORT).show();
                            break;
                        }else{
                            feed.save();
                            Toast.makeText(context, "订阅成功", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                }else{
                    feed.save();
                    Toast.makeText(context, "订阅成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    class RssSearchViewHolder extends RecyclerView.ViewHolder{
        private View layout;
        private ImageView iconIv;
        private TextView titleTv;
        private TextView websiteTv;

        public RssSearchViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            iconIv = (ImageView) itemView.findViewById(R.id.item_search_rss_icon_iv);
            titleTv = (TextView) itemView.findViewById(R.id.item_search_rss_title_tv);
            websiteTv = (TextView) itemView.findViewById(R.id.item_search_rss_website_tv);
        }
    }

}
