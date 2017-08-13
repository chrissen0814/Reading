package com.chrissen.reading.one.presenter;

import com.chrissen.reading.one.bean.OneList;
import com.chrissen.reading.one.bean.ReadingList;
import com.chrissen.reading.one.model.OneModel;
import com.chrissen.reading.one.model.OneModelImpl;
import com.chrissen.reading.one.view.OneListView;

/**
 * Created by Administrator on 2017/8/12.
 */

public class OneListPreImpl implements OneListPresenter , OnOneListListener {
    private OneModel mOneModel;
    private OneListView mOneListView;

    public OneListPreImpl(OneListView oneListView){
        mOneListView = oneListView;
        mOneModel = new OneModelImpl();
    }

    @Override
    public void getOneList() {
        mOneModel.loadOneList(this);
    }

    @Override
    public void getReadingList() {
        mOneModel.loadOneReadingList(this);
    }

    @Override
    public void loadOneListSuccess(OneList oneList) {
        mOneListView.showOneList(oneList);
    }

    @Override
    public void loadOneReadingList(ReadingList readingList) {
        mOneListView.showReadingList(readingList);
    }

}
