package com.chrissen.reading;

import android.app.Application;
import android.content.Context;

import com.umeng.analytics.MobclickAgent;

import org.litepal.LitePal;

/**
 * Created by Administrator on 2017/8/2.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        LitePal.initialize(this);
        context = getApplicationContext();
        MobclickAgent.openActivityDurationTrack(false);
    }


    public static Context getContext(){
        return context;
    }

}
