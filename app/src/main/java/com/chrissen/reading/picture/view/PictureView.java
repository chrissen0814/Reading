package com.chrissen.reading.picture.view;

import com.chrissen.reading.picture.bean.Unsplash;

import java.util.List;

/**
 * Created by Administrator on 2017/8/2.
 */

public interface PictureView {

    void showImage(List<Unsplash> unsplashList);

    void loadMoreImage(List<Unsplash> unsplashList);

}
