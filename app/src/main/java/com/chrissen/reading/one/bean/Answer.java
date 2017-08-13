package com.chrissen.reading.one.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/8/12.
 */

public class Answer {

    @SerializedName("data")
    private AnswerInfo answerInfo;

    public AnswerInfo getAnswerInfo() {
        return answerInfo;
    }

    public void setAnswerInfo(AnswerInfo answerInfo) {
        this.answerInfo = answerInfo;
    }

    public class AnswerInfo{
        @SerializedName("question_title")
        private String title;
        @SerializedName("question_content")
        private String questionContent;
        @SerializedName("answer_content")
        private String answerContent;
        @SerializedName("guide_word")
        private String guideWord;
        @SerializedName("web_url")
        private String webUrl;

        public String getWebUrl() {
            return webUrl;
        }

        public void setWebUrl(String webUrl) {
            this.webUrl = webUrl;
        }

        public String getQuestionContent() {
            return questionContent;
        }

        public void setQuestionContent(String questionContent) {
            this.questionContent = questionContent;
        }

        public String getAnswerContent() {
            return answerContent;
        }

        public void setAnswerContent(String answerContent) {
            this.answerContent = answerContent;
        }

        public String getGuideWord() {
            return guideWord;
        }

        public void setGuideWord(String guideWord) {
            this.guideWord = guideWord;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }


}
