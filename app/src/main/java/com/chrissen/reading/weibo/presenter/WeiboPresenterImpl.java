package com.chrissen.reading.weibo.presenter;

import android.content.Context;

import com.chrissen.reading.weibo.bean.Status;
import com.chrissen.reading.weibo.model.WeiboModel;
import com.chrissen.reading.weibo.model.WeiboModelImpl;
import com.chrissen.reading.weibo.view.WeiboView;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import java.util.List;

/**
 * Created by Administrator on 2017/8/7.
 */

public class WeiboPresenterImpl implements WeiboPresenter , OnHomeLineListener , OnLoadHomeLineMoreListener {
    private WeiboView mWeiboView;
    private WeiboModel mWeiboModel;

    public WeiboPresenterImpl(WeiboView view){
        mWeiboView = view;
        mWeiboModel = new WeiboModelImpl();
    }

    @Override
    public SsoHandler loadAccessToken(Context context) {
        SsoHandler ssoHandler = mWeiboModel.login(context);
        return ssoHandler;
    }

    @Override
    public void loadHomeLine() {
        mWeiboModel.loadHomeTimeLine(this);
    }

    @Override
    public void loadMoreHomeLine(int page) {
        mWeiboModel.loadMoreHomeLine(page,this);
    }


    @Override
    public void onLoadHomeLine(List<Status> statusesList) {
        mWeiboView.showHomeLine(statusesList);
    }

    @Override
    public void loadMore(List<Status> statusList) {
        mWeiboView.showMoreHomeLine(statusList);
    }
}
