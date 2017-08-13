package com.chrissen.reading.weibo.util;

import android.content.Context;
import android.content.res.Resources;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chrissen.reading.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/8/8.
 */

public class WeiBoTextHelper {

    private static final String AT = "(@[\u4e00-\u9fa5\\w]+)";// @人
    private static final String TOPIC = "(#[\u4e00-\u9fa5\\w]+#)";// ##话题
    private static final String EMOJI = "(\\[[\u4e00-\u9fa5\\w]+\\])";// 表情
    private static final String URL = "(http://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])";// url

    private static final String REGEX = AT + "|" + TOPIC + "|" + EMOJI + "|" + URL;

    public static SpannableString getWeiBoContent(final Context context , String text , TextView textView){
        Resources resources = context.getResources();
        SpannableString spannableString = new SpannableString(text);
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(spannableString);
        if (matcher.find()) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            matcher.reset();
        }
        while (matcher.find()){
            final String at = matcher.group(1);
            final String topic = matcher.group(2);
            final String emoji = matcher.group(3);
            final String url = matcher.group(4);
            if (at != null) {
                int start = matcher.start(1);
                int end = start + at.length();
                WeiBoClickableSpan clickableSpan = new WeiBoClickableSpan(context){
                    @Override
                    public void onClick(View widget) {
                        Toast.makeText(context, "Clicked " + at, Toast.LENGTH_SHORT).show();
                    }
                };
                spannableString.setSpan(clickableSpan,start,end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(new ForegroundColorSpan(resources.getColor(R.color.weibo_underline)),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            if(topic != null){
                int start = matcher.start(2);
                int end = start + topic.length();
                WeiBoClickableSpan clickSpan = new WeiBoClickableSpan(context){
                    @Override
                    public void onClick(View widget) {
                        Toast.makeText(context, "Click " + topic, Toast.LENGTH_SHORT).show();
                    }
                };
                spannableString.setSpan(clickSpan,start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(new ForegroundColorSpan(resources.getColor(R.color.weibo_underline)),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            if(emoji != null){
                int start = matcher.start(3);
                int end = start + emoji.length();
                WeiBoClickableSpan clickSpan = new WeiBoClickableSpan(context){
                    @Override
                    public void onClick(View widget) {
                        Toast.makeText(context, "Click " + emoji, Toast.LENGTH_SHORT).show();
                    }
                };
                spannableString.setSpan(clickSpan,start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(new ForegroundColorSpan(resources.getColor(R.color.weibo_underline)),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            if(url != null){
                int start = matcher.start(4);
                int end = start + url.length();
                WeiBoClickableSpan clickSpan = new WeiBoClickableSpan(context){
                    @Override
                    public void onClick(View widget) {
                        Toast.makeText(context, "Click " + url, Toast.LENGTH_SHORT).show();
                    }
                };
                spannableString.setSpan(clickSpan,start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(new ForegroundColorSpan(resources.getColor(R.color.weibo_underline)),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

        }
        return spannableString;
    }


}
