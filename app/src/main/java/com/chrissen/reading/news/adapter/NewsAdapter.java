package com.chrissen.reading.news.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chrissen.reading.MyApplication;
import com.chrissen.reading.R;
import com.chrissen.reading.news.bean.News;
import com.chrissen.reading.news.view.NewsDetailFragment;
import com.chrissen.reading.util.ScreenUtil;
import com.chrissen.reading.util.fragmentHelper.FragmentTransitionHelper;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "NewsAdapter";

    private static final  int NEWS_WITH_IMAGE = 0;
    private static final int NEWS_NO_IMAGE = 1;

    private List<News.Result.Info> infoList;
    private Fragment fragment;

    public NewsAdapter(Fragment fragment , List<News.Result.Info> infoList){
        this.fragment = fragment;
        this.infoList = infoList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == NEWS_WITH_IMAGE) {
            View view = LayoutInflater.from(fragment.getActivity()).inflate(R.layout.item_news,parent,false);
            return new ArticleViewHolder(view);
        }else {
            View view = LayoutInflater.from(fragment.getActivity()).inflate(R.layout.item_news_no_image,parent,false);
            return new ArticleNoImageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final News.Result.Info info = infoList.get(position);
        if(holder instanceof ArticleViewHolder){

            Glide.with(fragment.getActivity()).load(info.getPic())
                    .into(((ArticleViewHolder)holder).imageIv);
            ((ArticleViewHolder)holder).titleTv.setText(info.getTitle());
            ((ArticleViewHolder)holder).sourceTv.setText(info.getSrc());
            String fullTime = info.getTime();
            String[] times = fullTime.split(" ");
            ((ArticleViewHolder)holder).timeTv.setText(times[1]);
            ((ArticleViewHolder)holder).layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String transitionName = info.getTitle() + position;
                    ViewCompat.setTransitionName(((ArticleViewHolder)holder).imageIv,transitionName);
                    NewsDetailFragment adf = NewsDetailFragment.newInstance(info,transitionName);
                    FragmentTransitionHelper.startFargment(MyApplication.getContext(),fragment,adf,((ArticleViewHolder)holder).imageIv,transitionName);
                }
            });
        }else{
            ((ArticleNoImageViewHolder)holder).titleTv.setText(info.getTitle());
            ((ArticleNoImageViewHolder)holder).sourceTv.setText(info.getSrc());
            String fullTime = info.getTime();
            String[] times = fullTime.split(" ");
            ((ArticleNoImageViewHolder)holder).timeTv.setText(times[1]);
            ((ArticleNoImageViewHolder)holder).layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String transitionName = info.getTitle() + position;
                    ViewCompat.setTransitionName(((ArticleNoImageViewHolder)holder).titleTv,transitionName);
                    NewsDetailFragment adf = NewsDetailFragment.newInstance(info,transitionName);
                    FragmentTransitionHelper.startFargment(MyApplication.getContext(),fragment,adf,((ArticleNoImageViewHolder)holder).titleTv,transitionName);
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(infoList.get(position).getPic().equals("")){
            return NEWS_NO_IMAGE;
        }else {
            return NEWS_WITH_IMAGE;
        }
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {
        private View layout;
        private TextView sourceTv;
        private TextView titleTv;
        private TextView timeTv;
        private ImageView imageIv;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            sourceTv = (TextView) itemView.findViewById(R.id.item_news_source_tv);
            titleTv = (TextView) itemView.findViewById(R.id.item_news_title_tv);
            imageIv = (ImageView) itemView.findViewById(R.id.news_image_iv);
            timeTv = (TextView) itemView.findViewById(R.id.item_news_time_tv);
            imageIv.getLayoutParams().height = ScreenUtil.getScreenWidth(fragment.getContext()) * 9 / 16;
        }

    }

    class ArticleNoImageViewHolder extends RecyclerView.ViewHolder {

        private View layout;
        private TextView titleTv , sourceTv , timeTv;
        public ArticleNoImageViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            titleTv = (TextView) itemView.findViewById(R.id.item_news_no_image_title_tv);
            sourceTv = (TextView) itemView.findViewById(R.id.item_news_no_image_source_tv);
            timeTv = (TextView) itemView.findViewById(R.id.item_news_no_image_time_tv);
        }
    }


}
