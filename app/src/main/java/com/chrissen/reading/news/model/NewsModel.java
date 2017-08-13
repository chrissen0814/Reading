package com.chrissen.reading.news.model;

import com.chrissen.reading.news.presenter.OnLoadListener;
import com.chrissen.reading.news.presenter.OnLoadMoreListener;

/**
 * Created by Administrator on 2017/8/3.
 */

public interface NewsModel {

    void loadNews(String channel , int start ,OnLoadListener listener);

    void loadMoreNews(String channel , int start , OnLoadMoreListener listener);

}
