package com.chrissen.reading.util.http;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.chrissen.reading.MyApplication;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by Administrator on 2017/8/2.
 */

public class NetworkUtil {

    public static boolean isNetworkConnected(){
        ConnectivityManager cm = (ConnectivityManager) MyApplication.getContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.isAvailable();
        }
        return false;
    }

}
