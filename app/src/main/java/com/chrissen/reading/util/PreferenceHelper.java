package com.chrissen.reading.util;

import android.preference.PreferenceManager;

import com.chrissen.reading.MyApplication;

/**
 * Created by Administrator on 2017/8/4.
 */

public class PreferenceHelper {

    public static final String AUTH_TIME = "auth_time";
    public static final String ONE_DATA = "one";
    public static final String IS_FIRST = "isFirst";

    public static void putData(String name , long value){
        PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext())
                .edit().putLong(name,value)
                .apply();
    }

    public static long getData(String name , long defaultValue){
        return PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext())
                .getLong(name,defaultValue);
    }

    public static boolean getBoolean(String name , boolean defaultValue){
        return PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext())
                .getBoolean(name,defaultValue);
    }

    public static void putBoolean(String name , boolean value){
        PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext())
                .edit()
                .putBoolean(name,value)
                .apply();
    }

}
