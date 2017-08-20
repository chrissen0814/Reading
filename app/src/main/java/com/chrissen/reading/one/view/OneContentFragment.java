package com.chrissen.reading.one.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chrissen.reading.R;
import com.chrissen.reading.one.bean.Answer;
import com.chrissen.reading.one.bean.Essay;
import com.chrissen.reading.one.presenter.OneContentPreImpl;
import com.chrissen.reading.one.presenter.OneContentPresenter;
import com.chrissen.reading.util.ScreenUtil;
import com.umeng.analytics.MobclickAgent;
import com.zzhoujay.richtext.RichText;

/**
 * Created by Administrator on 2017/8/12.
 */

public class OneContentFragment extends Fragment implements OneContentView {
    private static final String IMAGE_URL = "image";
    private static final String ITEM_ID = "item_id";

    private String imageUrl;
    private String itemId;

    private OneContentPresenter presenter;

    private ImageView headerIv;
    private TextView titleTv;
    private TextView contentTv;
    private Button linkBt;
    private AppBarLayout appBarLayout;

    public static OneContentFragment newInstance(String imageUrl , String itemId){
        Bundle bundle = new Bundle();
        bundle.putString(IMAGE_URL,imageUrl);
        bundle.putString(ITEM_ID,itemId);
        OneContentFragment fragment = new OneContentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new OneContentPreImpl(this);
        imageUrl = getArguments().getString(IMAGE_URL);
        itemId = getArguments().getString(ITEM_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one_content,container,false);
        headerIv = (ImageView) view.findViewById(R.id.one_content_image_iv);
        titleTv = (TextView) view.findViewById(R.id.one_content_title_tv);
        contentTv = (TextView) view.findViewById(R.id.one_content_content_tv);
        linkBt = (Button) view.findViewById(R.id.one_content_link_bt);
        appBarLayout = (AppBarLayout) view.findViewById(R.id.one_content_appbarlayout);
        initLayout();
        return view;
    }

    private void initLayout() {
        appBarLayout.getLayoutParams().height = ScreenUtil.getScreenWidth(getActivity())*9/16;
        Glide.with(this).load(imageUrl).centerCrop().into(headerIv);
        presenter.getContent(itemId);
    }

    @Override
    public void showContent(final Essay essay) {
        if(essay != null){
            titleTv.setText(essay.getEssayInfo().getTitle());
            RichText.from(essay.getEssayInfo().getContent()).into(contentTv);
            linkBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent contentIt = new Intent(Intent.ACTION_VIEW);
                    Uri contentUri = Uri.parse(essay.getEssayInfo().getWebUrl());
                    contentIt.setData(contentUri);
                    startActivity(contentIt);
                }
            });
        }
    }

    @Override
    public void showQuestion(final Answer answer) {
        if(answer != null){
            titleTv.setText(answer.getAnswerInfo().getTitle());
            RichText.from(answer.getAnswerInfo().getAnswerContent()).into(contentTv);
            linkBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent contentIt = new Intent(Intent.ACTION_VIEW);
                    Uri contentUri = Uri.parse(answer.getAnswerInfo().getWebUrl());
                    contentIt.setData(contentUri);
                    startActivity(contentIt);
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("OneContentFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("OneContentFragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().findViewById(R.id.main_bnv).setVisibility(View.VISIBLE);
    }
}
