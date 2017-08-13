package com.chrissen.reading.weibo.presenter;

import com.chrissen.reading.weibo.bean.Status;

import java.util.List;

/**
 * Created by Administrator on 2017/8/8.
 */

public interface OnHomeLineListener {

    void onLoadHomeLine(List<Status> statusesList);

}
