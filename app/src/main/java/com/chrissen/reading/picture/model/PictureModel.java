package com.chrissen.reading.picture.model;

import com.chrissen.reading.picture.presenter.OnGankLoadListener;
import com.chrissen.reading.picture.presenter.OnUnsplashLoadMoreListener;
import com.chrissen.reading.picture.presenter.OnUnsplashLoadNowListener;

/**
 * Created by Administrator on 2017/8/2.
 */

public interface PictureModel {

    void loadUnsplash(int page , OnUnsplashLoadNowListener listener);

    void loadUnsplashMore(int page , OnUnsplashLoadMoreListener listener);

    void loadGank(int page , OnGankLoadListener listener);
}
