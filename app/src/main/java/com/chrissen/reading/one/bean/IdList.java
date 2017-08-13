package com.chrissen.reading.one.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/8/12.
 */

public class IdList {
    @SerializedName("data")
    private List<String> datas;

    public List<String> getDatas() {
        return datas;
    }

    public void setDatas(List<String> datas) {
        this.datas = datas;
    }
}
