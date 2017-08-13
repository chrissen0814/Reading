package com.chrissen.reading.one.model;

import com.chrissen.reading.one.presenter.OnOneListListener;

/**
 * Created by Administrator on 2017/8/12.
 */

public interface OneModel {

    void loadOneList(OnOneListListener listener);

    void loadOneReadingList(OnOneListListener listener);

}
