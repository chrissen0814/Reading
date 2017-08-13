package com.chrissen.reading.one.presenter;

import com.chrissen.reading.one.bean.OneList;
import com.chrissen.reading.one.bean.ReadingList;

/**
 * Created by Administrator on 2017/8/12.
 */

public interface OnOneListListener {

    void loadOneListSuccess(OneList oneList);

    void loadOneReadingList(ReadingList readingList);

}
