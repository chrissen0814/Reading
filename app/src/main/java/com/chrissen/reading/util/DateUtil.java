package com.chrissen.reading.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/2.
 */

public class DateUtil {

    public static Date strToDate(String strData){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strDate = format.parse(strData,pos);
        return strDate;
    }

}
