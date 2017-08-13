package com.chrissen.reading.weibo.util;

import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by Administrator on 2017/8/8.
 */

public class WeiBoClickableSpan extends ClickableSpan {

    private Context context;

    public WeiBoClickableSpan(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View widget) {

    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(Color.RED);
        ds.setUnderlineText(false);
    }
}
