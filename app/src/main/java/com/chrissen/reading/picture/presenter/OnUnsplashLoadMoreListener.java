package com.chrissen.reading.picture.presenter;

import com.chrissen.reading.picture.bean.Unsplash;

import java.util.List;

/**
 * Created by Administrator on 2017/8/2.
 */

public interface OnUnsplashLoadMoreListener {

    void onLoadMore(List<Unsplash> unsplashList);

}
