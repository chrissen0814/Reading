package com.chrissen.reading.rss.bean;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/8/10.
 */

public class Feed extends DataSupport{

    @SerializedName("feedId")
    private String feedId;
    private String title;
    private String website;
    private String description;
    @Nullable
    private Stream coverUrl;
    @Nullable
    private String iconUrl;
    @Nullable
    private String visualUrl;

    public Feed(String iconUrl, Stream coverUrl, String description, String feedId, String title, String visualUrl, String website) {
        this.iconUrl = iconUrl;
        this.coverUrl = coverUrl;
        this.description = description;
        this.feedId = feedId;
        this.title = title;
        this.visualUrl = visualUrl;
        this.website = website;
    }

    public Stream getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(Stream coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVisualUrl() {
        return visualUrl;
    }

    public void setVisualUrl(String visualUrl) {
        this.visualUrl = visualUrl;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

}
