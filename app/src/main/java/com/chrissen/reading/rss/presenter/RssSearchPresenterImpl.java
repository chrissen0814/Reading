package com.chrissen.reading.rss.presenter;

import com.chrissen.reading.rss.bean.Feed;
import com.chrissen.reading.rss.model.RssSearchModel;
import com.chrissen.reading.rss.model.RssSearchModelImpl;
import com.chrissen.reading.rss.view.RssSearchView;

import java.util.List;

/**
 * Created by Administrator on 2017/8/11.
 */

public class RssSearchPresenterImpl implements RssSearchPresenter , OnSearchListener {
    private RssSearchModel mRssSearchModel;
    private RssSearchView mRssSearchView;

    public RssSearchPresenterImpl(RssSearchView view){
        mRssSearchView = view;
        mRssSearchModel = new RssSearchModelImpl();
    }

    @Override
    public void lodSearch(String name) {
        mRssSearchModel.searchRss(name,this);
    }

    @Override
    public void onSearchedSuccess(List<Feed> feedList) {
        mRssSearchView.showSearchedRsses(feedList);
    }
}
