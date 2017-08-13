package com.chrissen.reading.weibo.util;

import com.chrissen.reading.MyApplication;
import com.chrissen.reading.util.PreferenceHelper;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;

/**
 * Created by Administrator on 2017/8/11.
 */

public class AccessTokenHelper {


    public static boolean isExpired(){
        long authTime = PreferenceHelper.getData(PreferenceHelper.AUTH_TIME,0);
        long expireTime = AccessTokenKeeper.readAccessToken(MyApplication.getContext()).getExpiresTime();
        long currentTime = System.currentTimeMillis();
        if(authTime + expireTime > currentTime){
            return true;
        }else {
            return false;
        }
    }

}
