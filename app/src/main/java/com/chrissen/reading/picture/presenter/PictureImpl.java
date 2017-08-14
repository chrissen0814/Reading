package com.chrissen.reading.picture.presenter;

import com.chrissen.reading.picture.model.PictureModel;
import com.chrissen.reading.picture.bean.Unsplash;
import com.chrissen.reading.picture.model.PictureModelImpl;
import com.chrissen.reading.picture.view.PictureView;

import java.util.List;

/**
 * Created by Administrator on 2017/8/2.
 */

public class PictureImpl implements PicturePresenter , OnUnsplashLoadNowListener, OnUnsplashLoadMoreListener{

    private PictureModel mModel;
    private PictureView mView;

    public PictureImpl(PictureView view) {
        mView = view;
        mModel = new PictureModelImpl();
    }

    @Override
    public void loadUnsplashImage(int page) {
        mModel.loadUnsplash(page,this);
    }

    @Override
    public void loadUnsplashMore(int page) {
        mModel.loadUnsplashMore(page,this);
    }


    @Override
    public void onLoadMore(List<Unsplash> unsplashList) {
        mView.loadMoreImage(unsplashList);
    }

    @Override
    public void onLoadNow(List<Unsplash> unsplashList) {
        mView.showImage(unsplashList);
    }

}
