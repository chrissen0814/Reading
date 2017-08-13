package com.chrissen.reading.news.presenter;

import com.chrissen.reading.news.bean.News;
import com.chrissen.reading.news.model.NewsModel;
import com.chrissen.reading.news.model.NewsModelImpl;
import com.chrissen.reading.news.view.NewsView;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */

public class NewsImpl implements NewsPresenter, OnLoadListener , OnLoadMoreListener {

    private NewsModel mNewsModel;
    private NewsView mNewsView;

    public NewsImpl(NewsView newsView){
        mNewsView = newsView;
        mNewsModel = new NewsModelImpl();
    }

    @Override
    public void loadNews(String channel , int start) {
        mNewsModel.loadNews(channel,start,this);
    }

    @Override
    public void loadMoreNews(String channel, int start) {
        mNewsModel.loadMoreNews(channel,start,this);
    }


    @Override
    public void onLoadSuccess(List<News.Result.Info> infoList) {
        mNewsView.showNews(infoList);
    }

    @Override
    public void loadMoreSuccess(List<News.Result.Info> infoList) {
        mNewsView.showMoreNews(infoList);
    }
}
