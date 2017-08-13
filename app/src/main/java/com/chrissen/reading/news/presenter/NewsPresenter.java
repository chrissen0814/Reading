package com.chrissen.reading.news.presenter;

/**
 * Created by Administrator on 2017/8/3.
 */

public interface NewsPresenter {

    void loadNews(String channel , int start);

    void loadMoreNews(String channel , int start);

}
