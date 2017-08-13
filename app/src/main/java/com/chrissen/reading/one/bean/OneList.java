package com.chrissen.reading.one.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/8/12.
 */

public class OneList {

    @SerializedName("data")
    private OneListInfo date;

    public OneListInfo getDate() {
        return date;
    }

    public void setDate(OneListInfo date) {
        this.date = date;
    }

    public class OneListInfo{
        private String date;
        @SerializedName("content_list")
        private List<Content> contenList;


        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<Content> getContenList() {
            return contenList;
        }

        public void setContenList(List<Content> contenList) {
            this.contenList = contenList;
        }
    }



    public class Content{
        @SerializedName("item_id")
        private String itemId;
        private String title;
        private String forward;
        @SerializedName("img_url")
        private String imageUrl;
        private Author author;
        @SerializedName("pic_info")
        private String picInfo;
        @SerializedName("words_info")
        private String wordsInfo;

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        public String getForward() {
            return forward;
        }

        public void setForward(String forward) {
            this.forward = forward;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getPicInfo() {
            return picInfo;
        }

        public void setPicInfo(String picInfo) {
            this.picInfo = picInfo;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getWordsInfo() {
            return wordsInfo;
        }

        public void setWordsInfo(String wordsInfo) {
            this.wordsInfo = wordsInfo;
        }

        public class Author{
            @SerializedName("user_name")
            private String authorName;
            private String desc;
            private String summary;

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getAuthorName() {
                return authorName;
            }

            public void setAuthorName(String authorName) {
                this.authorName = authorName;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }
        }

    }


}
