package com.chrissen.reading.rss.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chrissen.reading.R;
import com.chrissen.reading.rss.bean.Entry;
import com.chrissen.reading.rss.view.ArticleFragment;
import com.chrissen.reading.util.TimerHelper;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2017/8/11.
 */

public class StreamAdapter extends RecyclerView.Adapter<StreamAdapter.StreamViewHolder> {

    private Fragment fragment;
    private List<Entry> entyrList;

    public StreamAdapter(Fragment fragment , String source){
        this.fragment = fragment;
        entyrList = DataSupport.where("source = ?",source).find(Entry.class);
    }


    @Override
    public StreamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(fragment.getContext()).inflate(R.layout.item_stream,parent,false);
        return new StreamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final StreamViewHolder holder, final int position) {
        final Entry entry = entyrList.get(position);
        holder.streamTitleTv.setText(entry.getTitle());
        holder.timeTv.setText(TimerHelper.getTime(entry.getPublishedTime()/1000));
        holder.authorTv.setText(entry.getAuthor());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container_fl, ArticleFragment.newInstance(entry.getFeedId()))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return entyrList.size();
    }

    class StreamViewHolder extends RecyclerView.ViewHolder {
        private View layout;
        private TextView streamTitleTv;
        private TextView timeTv;
        private TextView authorTv;
        public StreamViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            streamTitleTv = (TextView) itemView.findViewById(R.id.item_stream_title_tv);
            timeTv = (TextView) itemView.findViewById(R.id.item_stream_time_tv);
            authorTv = (TextView) itemView.findViewById(R.id.item_stream_author_tv);
        }
    }

}
