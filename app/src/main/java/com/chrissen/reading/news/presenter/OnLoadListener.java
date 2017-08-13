package com.chrissen.reading.news.presenter;

import com.chrissen.reading.news.bean.News;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */

public interface OnLoadListener {

    void onLoadSuccess(List<News.Result.Info> infoList);

}
