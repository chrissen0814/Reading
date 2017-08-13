package com.chrissen.reading.one.presenter;

import com.chrissen.reading.one.bean.OneList;
import com.chrissen.reading.one.model.OneModel;
import com.chrissen.reading.one.model.OneModelImpl;
import com.chrissen.reading.one.view.OneView;

/**
 * Created by Administrator on 2017/8/12.
 */

public class OneListPreImpl implements OneListPresenter , OnOneListListener {
    private OneModel mOneModel;
    private OneView mOneView;

    public OneListPreImpl(OneView oneView){
        mOneView = oneView;
        mOneModel = new OneModelImpl();
    }

    @Override
    public void getOneList() {
        mOneModel.loadOneList(this);
    }

    @Override
    public void loadOneListSuccess(OneList oneList) {
        mOneView.showOneList(oneList);
    }

}
