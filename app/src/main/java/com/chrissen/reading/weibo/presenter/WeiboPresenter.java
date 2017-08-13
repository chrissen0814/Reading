package com.chrissen.reading.weibo.presenter;

import android.content.Context;

import com.sina.weibo.sdk.auth.sso.SsoHandler;

/**
 * Created by Administrator on 2017/8/7.
 */

public interface WeiboPresenter {

    SsoHandler loadAccessToken(Context context);

    void loadHomeLine();

    void loadMoreHomeLine(int page);

}
