package com.chrissen.reading.one.model;

import com.chrissen.reading.one.presenter.OnOneContentListener;
import com.chrissen.reading.one.presenter.OnOneQuestionListener;

/**
 * Created by Administrator on 2017/8/12.
 */

public interface OneContentModel {

    void loadOneContent(String itemId , OnOneContentListener listener);
    void loadOneQyestion(String itemId , OnOneQuestionListener listener);

}
