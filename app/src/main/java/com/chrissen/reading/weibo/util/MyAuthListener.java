package com.chrissen.reading.weibo.util;

import android.content.Context;
import android.widget.Toast;

import com.chrissen.reading.MyApplication;
import com.chrissen.reading.util.PreferenceHelper;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;

/**
 * Created by Administrator on 2017/8/11.
 */

public class MyAuthListener implements WbAuthListener {

    private Context context;

    public MyAuthListener(Context context){
        this.context = context;
    }

    @Override
    public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
        if (oauth2AccessToken.isSessionValid()) {
            AccessTokenKeeper.writeAccessToken(MyApplication.getContext(),oauth2AccessToken);
            PreferenceHelper.putData(PreferenceHelper.AUTH_TIME,System.currentTimeMillis());
            Toast.makeText(context, "授权成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void cancel() {
        Toast.makeText(context, "已取消授权", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
        Toast.makeText(context, "授权失败", Toast.LENGTH_SHORT).show();
    }
}
