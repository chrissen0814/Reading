package com.chrissen.reading.weibo.util;

import com.chrissen.reading.weibo.bean.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/8.
 */

public class PicHelper {

    private static final String BMIDDLE = "bmiddle";
    private static final String ORIGINAL = "large";

    public static List<String> getBmiddlePicUrl(List<Status.PicUrl> picUrlList){
        List<String> bmiddleUrlList = new ArrayList<>();
        for(int i =0 ; i < picUrlList.size() ; i++){
            String thumbnailUrl = picUrlList.get(i).getThumbailPic();
            String bmiddleUrl = thumbnailUrl.replace("thumbnail",BMIDDLE);
            bmiddleUrlList.add(bmiddleUrl);
        }
        return bmiddleUrlList;
    }

    public static List<String> getLargePicUrl(List<Status.PicUrl> picUrlList){
        List<String> largeUrlList = new ArrayList<>();
        for(int i =0 ; i < picUrlList.size() ; i++){
            String thumbnailUrl = picUrlList.get(i).getThumbailPic();
            String[] urlParts = thumbnailUrl.split("/");
            String bmiddleUrl = urlParts[0] + "/" + ORIGINAL + "/" + urlParts[2];
            largeUrlList.add(bmiddleUrl);
        }
        return largeUrlList;
    }

}
