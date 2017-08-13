package com.chrissen.reading.one.presenter;

import com.chrissen.reading.one.bean.Answer;
import com.chrissen.reading.one.bean.Essay;
import com.chrissen.reading.one.model.OneContentModel;
import com.chrissen.reading.one.model.OneContentModelImpl;
import com.chrissen.reading.one.view.OneContentView;

/**
 * Created by Administrator on 2017/8/12.
 */

public class OneContentPreImpl implements OneContentPresenter , OnOneContentListener , OnOneQuestionListener {
    private OneContentModel mOneContentModel;
    private OneContentView mOneContentView;

    public OneContentPreImpl(OneContentView view){
        mOneContentView = view;
        mOneContentModel = new OneContentModelImpl();
    }

    @Override
    public void loadContentSuccess(Essay essay) {
        mOneContentView.showContent(essay);
    }

    @Override
    public void onQuestionSuccess(Answer answer) {
        mOneContentView.showQuestion(answer);
    }

    @Override
    public void getContent(String itemId) {
        mOneContentModel.loadOneContent(itemId,this);
    }

    @Override
    public void getQuestion(String itemId) {
        mOneContentModel.loadOneQyestion(itemId,this);
    }
}
