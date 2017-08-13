package com.chrissen.reading.weibo.view;

import com.chrissen.reading.weibo.bean.Status;

import java.util.List;

/**
 * Created by Administrator on 2017/8/7.
 */

public interface WeiboView {

    void showHomeLine(List<Status> statusesList);

    void showMoreHomeLine(List<Status> statusList);

}
