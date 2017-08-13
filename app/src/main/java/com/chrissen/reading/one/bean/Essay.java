package com.chrissen.reading.one.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/8/12.
 */

public class Essay {

    @SerializedName("data")
    private EssayInfo essayInfo;

    public EssayInfo getEssayInfo() {
        return essayInfo;
    }

    public void setEssayInfo(EssayInfo essayInfo) {
        this.essayInfo = essayInfo;
    }

    public class EssayInfo{
        @SerializedName("hp_title")
        private String title;
        @SerializedName("hp_author")
        private String author;
        @SerializedName("auth_it")
        private String authDesc;
        @SerializedName("hp_content")
        private String content;
        @SerializedName("web_url")
        private String webUrl;
        @SerializedName("guide_word")
        private String guideWord;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthDesc() {
            return authDesc;
        }

        public void setAuthDesc(String authDesc) {
            this.authDesc = authDesc;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getGuideWord() {
            return guideWord;
        }

        public void setGuideWord(String guideWord) {
            this.guideWord = guideWord;
        }

        public String getWebUrl() {
            return webUrl;
        }

        public void setWebUrl(String webUrl) {
            this.webUrl = webUrl;
        }
    }


}
