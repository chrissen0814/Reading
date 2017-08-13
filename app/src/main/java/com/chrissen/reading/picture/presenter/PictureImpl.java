package com.chrissen.reading.picture.presenter;

import com.chrissen.reading.picture.model.UnsplasModel;
import com.chrissen.reading.picture.bean.Unsplash;
import com.chrissen.reading.picture.model.UnsplashImpl;
import com.chrissen.reading.picture.view.PictureView;

import java.util.List;

/**
 * Created by Administrator on 2017/8/2.
 */

public class PictureImpl implements PicturePresenter , OnLoadNowListener , OnLoadMoreListener  {

    private UnsplasModel mModel;
    private PictureView mView;

    public PictureImpl(PictureView view) {
        mView = view;
        mModel = new UnsplashImpl();
    }

    @Override
    public void loadImage(int page) {
        mModel.loadImage(page,this);
    }

    @Override
    public void loadMore(int page) {
        mModel.loadMore(page,this);
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
