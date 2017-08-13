package com.chrissen.reading.rss.model;

import com.chrissen.reading.rss.presenter.OnStreamListener;

/**
 * Created by Administrator on 2017/8/10.
 */

public interface StreamModel {

    void getStream(String feedId , OnStreamListener listener);
}
