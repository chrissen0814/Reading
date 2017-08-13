package com.chrissen.reading.picture.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/8/13.
 */

public class Gank {

    private String error;
    @SerializedName("results")
    private List<MeiZhi> meizhiList;

    public List<MeiZhi> getMeizhiList() {
        return meizhiList;
    }

    public void setMeizhiList(List<MeiZhi> meizhiList) {
        this.meizhiList = meizhiList;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public class MeiZhi{
        @SerializedName("_id")
        private String id;
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

}
