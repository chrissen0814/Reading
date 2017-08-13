package com.chrissen.reading.one.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/8/12.
 */

public class ReadingList {

    @SerializedName("data")
    private List<Reading> readingList;

    public List<Reading> getReadingList() {
        return readingList;
    }

    public void setReadingList(List<Reading> readingList) {
        this.readingList = readingList;
    }

    public class Reading{
        @SerializedName("item_id")
        private String itemId;
        private String category;
        @SerializedName("display_category")
        private String displayCategory;
        private String title;
        private String forward;
        @SerializedName("img_url")
        private String imageUrl;
        @SerializedName("post_date")
        private String postDate;

        public String getPostDate() {
            return postDate;
        }

        public void setPostDate(String postDate) {
            this.postDate = postDate;
        }

        public String getDisplayCategory() {
            return displayCategory;
        }

        public void setDisplayCategory(String displayCategory) {
            this.displayCategory = displayCategory;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getForward() {
            return forward;
        }

        public void setForward(String forward) {
            this.forward = forward;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

}
