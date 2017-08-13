package com.chrissen.reading.util;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/8/11.
 */

public class TimerHelper {

    public static String getTime(long timeStamp) {
        String time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(timeStamp * 1000);
        String[] split = date.split("\\s");
        if ( split.length > 1 ){
            time = split[1];
        }
        return time;
    }

}
