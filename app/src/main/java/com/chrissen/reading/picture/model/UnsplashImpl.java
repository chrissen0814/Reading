package com.chrissen.reading.picture.model;

import com.chrissen.reading.picture.bean.Unsplash;
import com.chrissen.reading.picture.presenter.OnLoadMoreListener;
import com.chrissen.reading.picture.presenter.OnLoadNowListener;
import com.chrissen.reading.util.http.Api;
import com.chrissen.reading.util.http.RetrofitFactory;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/2.
 */

public class UnsplashImpl implements UnsplasModel {

    @Override
    public void loadImage(int page , final OnLoadNowListener listener) {
        new RetrofitFactory(Api.UNSPLASH_URL).getApiInterface()
                .getUnsplash(page,15)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Unsplash>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Unsplash> unsplashes) {
                        listener.onLoadNow(unsplashes);
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
    public void loadMore(int page, final OnLoadMoreListener listener) {
        new RetrofitFactory(Api.UNSPLASH_URL).getApiInterface()
                .getUnsplash(page,15)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Unsplash>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Unsplash> unsplashes) {
                        listener.onLoadMore(unsplashes);
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
