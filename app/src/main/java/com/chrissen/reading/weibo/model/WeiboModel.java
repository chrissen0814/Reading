package com.chrissen.reading.weibo.model;

import android.content.Context;

import com.chrissen.reading.weibo.presenter.OnHomeLineListener;
import com.chrissen.reading.weibo.presenter.OnLoadHomeLineMoreListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

/**
 * Created by Administrator on 2017/8/7.
 */

public interface WeiboModel {

    SsoHandler login(Context context);

    void loadHomeTimeLine(OnHomeLineListener listener);

    void loadMoreHomeLine(int page , OnLoadHomeLineMoreListener listener);

}
