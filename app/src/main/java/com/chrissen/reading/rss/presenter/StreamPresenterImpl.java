package com.chrissen.reading.rss.presenter;

import com.chrissen.reading.rss.bean.Stream;
import com.chrissen.reading.rss.model.StreamModel;
import com.chrissen.reading.rss.model.StreamModelImpl;
import com.chrissen.reading.rss.view.StreamView;

/**
 * Created by Administrator on 2017/8/10.
 */

public class StreamPresenterImpl implements StreamPresenter, OnStreamListener {
    private StreamModel mStreamModel;
    private StreamView mStreamView;

    public StreamPresenterImpl(StreamView streamView){
        mStreamView = streamView;
        mStreamModel = new StreamModelImpl();
    }

    @Override
    public void loadStream(String feedId) {
        mStreamModel.getStream(feedId,this);
    }

    @Override
    public void onStreamSuccess() {
       mStreamView.showArticles();
    }
}
