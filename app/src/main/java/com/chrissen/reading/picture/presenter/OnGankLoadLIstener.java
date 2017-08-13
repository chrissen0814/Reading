package com.chrissen.reading.picture.presenter;

import com.chrissen.reading.picture.bean.Gank;

import java.util.List;

/**
 * Created by Administrator on 2017/8/13.
 */

public interface OnGankLoadListener {

    void onLoadGankSuccess(List<Gank.MeiZhi> meiZhiList);

}
