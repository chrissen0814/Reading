package com.chrissen.reading.weibo.model;

import android.app.Activity;
import android.content.Context;

import com.chrissen.reading.MyApplication;
import com.chrissen.reading.util.http.Api;
import com.chrissen.reading.util.http.RetrofitFactory;
import com.chrissen.reading.weibo.bean.HomeLine;
import com.chrissen.reading.weibo.presenter.OnHomeLineListener;
import com.chrissen.reading.weibo.presenter.OnLoadHomeLineMoreListener;
import com.chrissen.reading.weibo.util.AccessTokenHelper;
import com.chrissen.reading.weibo.util.MyAuthListener;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/7.
 */

public class WeiboModelImpl implements WeiboModel {
    private static final String TAG = "WeiboModelImpl";

    @Override
    public SsoHandler login(Context context) {
        if (AccessTokenKeeper.readAccessToken(context).isSessionValid() && AccessTokenHelper.isExpired()) {
            return null;
        }else {
            AuthInfo authInfo = new AuthInfo(context,Api.WEIBO_KEY,Api.WEIBO_REDIRECT_URl,Api.WEIBO_SCOPE);
            WbSdk.install(context,authInfo);
            SsoHandler ssoHandler = new SsoHandler((Activity) context);
            ssoHandler.authorize(new MyAuthListener(context));
            return ssoHandler;
        }
    }

    @Override
    public void loadHomeTimeLine(final OnHomeLineListener listener) {
        String accessToken = AccessTokenKeeper.readAccessToken(MyApplication.getContext()).getToken() ;
        new RetrofitFactory(Api.WEIBO_URL).getApiInterface()
                .getWeiboHomeLine(accessToken , 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeLine>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HomeLine homeLine) {
                        listener.onLoadHomeLine(homeLine.getStatusesList());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadMoreHomeLine(int page, final OnLoadHomeLineMoreListener listener) {
       String accessToken = null;
        new RetrofitFactory(Api.WEIBO_URL).getApiInterface()
                .getWeiboHomeLine(accessToken ,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeLine>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HomeLine homeLine) {
                        listener.loadMore(homeLine.getStatusesList());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
