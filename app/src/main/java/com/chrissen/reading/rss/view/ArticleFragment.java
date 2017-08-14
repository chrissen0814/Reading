package com.chrissen.reading.rss.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.chrissen.reading.R;
import com.chrissen.reading.rss.bean.Entry;
import com.umeng.analytics.MobclickAgent;
import com.zzhoujay.richtext.RichText;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/8/11.
 */

public class ArticleFragment extends Fragment {

    private static final String ARTICLE_ID = "article";

    private Entry articleEntry;
    private String articleId;
    private TextView titleTv;
    private Button linkBt;
    private TextView contentTv;

    public static ArticleFragment newInstance(String articleId){
        Bundle bundle = new Bundle();
        bundle.putString(ARTICLE_ID,articleId);
        ArticleFragment fragment = new ArticleFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        articleId = getArguments().getString(ARTICLE_ID);
        articleEntry = DataSupport.where("feedId=?",articleId).find(Entry.class).get(0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article,container,false);
        initLayout(view);
        return view;
    }

    private void initLayout(View view) {
        titleTv = (TextView) view.findViewById(R.id.article_title_tv);
        linkBt = (Button) view.findViewById(R.id.article_link_bt);
        contentTv = (TextView) view.findViewById(R.id.article_content_tv);
        titleTv.setText(articleEntry.getTitle());
        if(articleEntry.getContent() != null){
            RichText.from(articleEntry.getContent()).into(contentTv);
        }else{
            RichText.from(articleEntry.getSummary()).into(contentTv);
        }

        linkBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent linkIntent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(articleEntry.getOriginId());
                linkIntent.setData(uri);
                startActivity(linkIntent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("ArticleFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("ArticleFragment");
    }
}
