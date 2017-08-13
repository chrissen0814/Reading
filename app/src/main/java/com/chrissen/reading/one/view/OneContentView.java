package com.chrissen.reading.one.view;

import com.chrissen.reading.one.bean.Answer;
import com.chrissen.reading.one.bean.Essay;

/**
 * Created by Administrator on 2017/8/12.
 */

public interface OneContentView {
    void showContent(Essay essay);
    void showQuestion(Answer answer);
}
