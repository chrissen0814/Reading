package com.chrissen.reading.news.view;

import com.chrissen.reading.news.bean.News;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */

public interface NewsView {

    void showNews(List<News.Result.Info> infoList);

    void showMoreNews(List<News.Result.Info> infoList);

}
