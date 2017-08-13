package com.chrissen.reading.rss.model;

import com.chrissen.reading.rss.bean.Rsses;
import com.chrissen.reading.rss.presenter.OnSearchListener;
import com.chrissen.reading.util.http.Api;
import com.chrissen.reading.util.http.RetrofitFactory;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/11.
 */

public class RssSearchModelImpl implements RssSearchModel {

    private static final String TAG = "RssSearchModelImpl";

    @Override
    public void searchRss(String name , final OnSearchListener listener) {
        new RetrofitFactory(Api.FEEDLY_URL).getApiInterface()
                .searchFeeds(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Rsses>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Rsses rsses) {
                        listener.onSearchedSuccess(rsses.getFeedList());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
