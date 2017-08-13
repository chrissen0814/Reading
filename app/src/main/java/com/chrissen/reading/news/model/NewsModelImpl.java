package com.chrissen.reading.news.model;

import com.chrissen.reading.news.bean.News;
import com.chrissen.reading.news.presenter.OnLoadListener;
import com.chrissen.reading.news.presenter.OnLoadMoreListener;
import com.chrissen.reading.util.http.Api;
import com.chrissen.reading.util.http.RetrofitFactory;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/3.
 */

public class NewsModelImpl implements NewsModel {


    @Override
    public void loadNews(String channel, int start, final OnLoadListener listener) {
        new RetrofitFactory(Api.NEWS_URL).getApiInterface()
                .getNews(channel,start,Api.NEWS_COUNT,Api.NEWS_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<News>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull News news) {
                        if (news.getMsg().equals("ok")) {
                            listener.onLoadSuccess(news.getResult().getInfoList());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadMoreNews(String channel, int start, final OnLoadMoreListener listener) {

        new RetrofitFactory(Api.NEWS_URL).getApiInterface()
                .getNews(channel,start,Api.NEWS_COUNT,Api.NEWS_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<News>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull News news) {
                        if (news.getMsg().equals("ok")) {
                            listener.loadMoreSuccess(news.getResult().getInfoList());
                        }
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
