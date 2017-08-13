package com.chrissen.reading.rss.model;

import com.chrissen.reading.rss.presenter.OnSearchListener;

/**
 * Created by Administrator on 2017/8/11.
 */

public interface RssSearchModel {
    void searchRss(String name , OnSearchListener listener);
}
