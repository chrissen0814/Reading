package com.chrissen.reading.picture.model;

import com.chrissen.reading.picture.bean.Unsplash;
import com.chrissen.reading.picture.presenter.OnUnsplashLoadMoreListener;
import com.chrissen.reading.picture.presenter.OnUnsplashLoadNowListener;
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

public class PictureModelImpl implements PictureModel {
    private static final String TAG = "PictureModelImpl";

    @Override
    public void loadUnsplash(int page , final OnUnsplashLoadNowListener listener) {
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
    public void loadUnsplashMore(int page, final OnUnsplashLoadMoreListener listener) {
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
