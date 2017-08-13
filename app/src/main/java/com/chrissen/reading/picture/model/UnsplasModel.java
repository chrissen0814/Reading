package com.chrissen.reading.picture.model;

import com.chrissen.reading.picture.presenter.OnLoadMoreListener;
import com.chrissen.reading.picture.presenter.OnLoadNowListener;

/**
 * Created by Administrator on 2017/8/2.
 */

public interface UnsplasModel {

    void loadImage(int page , OnLoadNowListener listener);

    void loadMore(int page , OnLoadMoreListener listener);

}
