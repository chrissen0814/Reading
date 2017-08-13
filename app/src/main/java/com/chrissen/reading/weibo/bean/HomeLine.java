package com.chrissen.reading.weibo.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/8/7.
 */

public class HomeLine {

    @SerializedName("statuses")
    @Expose
    private List<Status> statusList;

    public List<Status> getStatusesList() {
        return statusList;
    }

    public void setStatusesList(List<Status> statusList) {
        this.statusList = statusList;
    }

}
