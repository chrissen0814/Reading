package com.chrissen.reading.rss.presenter;

import com.chrissen.reading.rss.bean.Feed;

import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */

public interface OnSearchListener {

    void onSearchedSuccess(List<Feed> feedList);

}
