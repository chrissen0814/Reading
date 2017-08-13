package com.chrissen.reading.weibo.bean;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/8/8.
 */

public class Status {

    @SerializedName("created_at")
    private String createdTime;
    private long id;
    @SerializedName("idstr")
    private String idStr;
    @SerializedName("text")
    private String content;
    private String source;
    private boolean favorited;
    @Nullable
    @SerializedName("pic_urls")
    private List<PicUrl> picurlList;
    @Nullable
    @SerializedName("thumbnail_pic")
    private String thumbnailPic;
    @Nullable
    @SerializedName("bmiddle_pic")
    private String bmiddlePic;
    @Nullable
    @SerializedName("original_pic")
    private String originalPic;
    @SerializedName("user")
    private com.chrissen.reading.weibo.bean.User user;
    @Nullable
    @SerializedName("retweeted_status")
    private com.chrissen.reading.weibo.bean.RetweetedStatus retweetedStatus;
    @SerializedName("reposts_count")
    private int repostsCount;
    @SerializedName("comments_count")
    private int commentsCount;
    @SerializedName("attitudes_count")
    private int attitudesCount;
    @SerializedName("visible")
    private Visible visible;

    @Nullable
    public String getBmiddlePic() {
        return bmiddlePic;
    }

    public void setBmiddlePic(@Nullable String bmiddlePic) {
        this.bmiddlePic = bmiddlePic;
    }

    public int getAttitudesCount() {
        return attitudesCount;
    }

    public void setAttitudesCount(int attitudesCount) {
        this.attitudesCount = attitudesCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    @Nullable
    public String getOriginalPic() {
        return originalPic;
    }

    public void setOriginalPic(@Nullable String originalPic) {
        this.originalPic = originalPic;
    }

    public int getRepostsCount() {
        return repostsCount;
    }

    public void setRepostsCount(int repostsCount) {
        this.repostsCount = repostsCount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Nullable
    public String getThumbnailPic() {
        return thumbnailPic;
    }

    public void setThumbnailPic(@Nullable String thumbnailPic) {
        this.thumbnailPic = thumbnailPic;
    }

    @Nullable
    public RetweetedStatus getRetweetedStatus() {
        return retweetedStatus;
    }

    public void setRetweetedStatus(@Nullable RetweetedStatus retweetedStatus) {
        this.retweetedStatus = retweetedStatus;
    }

    @Nullable
    public List<PicUrl> getPicurlList() {
        return picurlList;
    }

    public void setPicurlList(@Nullable List<PicUrl> picurlList) {
        this.picurlList = picurlList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Visible getVisible() {
        return visible;
    }

    public void setVisible(Visible visible) {
        this.visible = visible;
    }

    public class Visible{
        private String type;
        @SerializedName("list_id")
        private String listId;

        public String getListId() {
            return listId;
        }

        public void setListId(String listId) {
            this.listId = listId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public class PicUrl{
        @Nullable
        @SerializedName("thumbnail_pic")
        private String thumbailPic;

        @Nullable
        public String getThumbailPic() {
            return thumbailPic;
        }

        public void setThumbailPic(@Nullable String thumbailPic) {
            this.thumbailPic = thumbailPic;
        }
    }

}

